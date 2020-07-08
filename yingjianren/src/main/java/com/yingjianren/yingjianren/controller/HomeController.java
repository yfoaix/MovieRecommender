package com.yingjianren.yingjianren.controller;

import com.yingjianren.yingjianren.entity.*;
import com.yingjianren.yingjianren.service.Algorithm;
import org.apache.ibatis.reflection.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.management.relation.RelationNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
public class HomeController {


    @Autowired
    UserRepository userR;

    @Autowired
    MovieRepository movieR;

    @Autowired
    HistoryRepository historyR;

    @Autowired
    LikeMovieRepository likeMovieR;

    @Autowired
    CommentRepository commentR;

    // 首页
    @GetMapping(value = {"/","/index"} )
    public ModelAndView Index(HttpServletRequest request,Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        List<Movie> movies=movieR.findMovieByScore();//随机找出高分电影
        Random r = new Random();
        if(request.getSession().getAttribute("userId")!=null){//已经登录了
            model.addAttribute("isLogin",true);
            model.addAttribute("user",userR.findUserById(((Long) request.getSession().getAttribute("userId"))));
            List<History> histories=historyR.findHistoryByUserId((Long) request.getSession().getAttribute("userId"));
            List<LikeMovie> likeMovies=likeMovieR.findByUser((Long) request.getSession().getAttribute("userId"));
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println(histories.size());
            System.out.println(likeMovies.size());
            if(histories.size()==0&&likeMovies.size()==0){
                //随机推荐
                recommendRandom(movies,model, r);
            }else if(histories.size()!=0&&likeMovies.size()==0){
                //使用历史信息推荐
                String url = "http://10.236.11.10:5000/history";
                StringBuilder param = new StringBuilder("historyids=");
                if(histories.size()>1) {
                    histories.sort(new Comparator<History>() {
                        @Override
                        public int compare(History arg0, History arg1) {
                            int mark = 1;
                            try {
                                Date date0 = arg0.getCreatedAt();
                                Date date1 = arg1.getCreatedAt();
                                if (date0.getTime() > date1.getTime()) {
                                    mark = -1;
                                }
                                if (date0.equals(date1)) {
                                    mark = 0;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return mark;
                        } // compare
                    });
                }

                int value=Math.min(histories.size(),5);

                doHistory(value,param,histories,r);

                //调用服务器算法返回值
                try{
                    doAlgorithm(url,param,model);
                } catch (IOException e) {
                    recommendRandom(movies,model, r);
                }



            }else if(histories.size() == 0){
                String url = "http://10.236.11.10:5000/like";
                StringBuilder param = new StringBuilder("likeids=");
                int value=Math.min(likeMovies.size(),5);
                param=doLike(value,param,likeMovies,r);

                //调用服务器算法返回值
                try{
                    doAlgorithm(url,param,model);
                } catch (IOException e) {
                    recommendRandom(movies,model, r);
                }

            }else {
                String url = "http://10.236.11.10:5000/both";
                StringBuilder param1 = new StringBuilder("likeids=");
                StringBuilder param2=new StringBuilder("&historyids=");

                int value1=Math.min(likeMovies.size(),3);
                int value2=Math.min(histories.size(),3);

                param1=doLike(value1,param1,likeMovies,r);

                param2=doHistory(value2,param2,histories,r);

                param1.append(param2);
                try{
                    doAlgorithm(url,param1,model);
                } catch (IOException e) {
                    recommendRandom(movies,model, r);
                }


            }
        }else{
            model.addAttribute("isLogin",false);

            recommendRandom(movies,model, r);


        }



        model.addAttribute("todayRecommend",movies.get(r.nextInt(1665)));
        goodComment(model);
        return modelAndView;
    }

    //随机推荐
    private void recommendRandom(List<Movie> movies, Model model, Random r){
        List<Movie> recommend=new ArrayList<Movie>();

        for(int i=0;i<10;i++){
            recommend.add(movies.get(r.nextInt(1665)));
        }
        model.addAttribute("recommendMovie",recommend);

    }

    //调用服务器算法返回值
    private void doAlgorithm(String url,StringBuilder param,Model model) throws IOException {
        System.out.println(url+"?"+param);
        String stringList= Algorithm.sendGet(url, param.toString());
        stringList= stringList.substring(1,stringList.length()-1);
        String[] strArr = stringList.split(", ");
        List<Movie> recommend=new ArrayList<Movie>();
        for(String id :strArr){
            recommend.add(movieR.findMovieById( Long.parseLong(id)));
        }
        model.addAttribute("recommendMovie",recommend);
    }

    //likeMovie
    private StringBuilder doLike(int value,StringBuilder param,List<LikeMovie> likeMovies,Random r){
        if(value==1){
            param.append(likeMovies.get(0).getMovie().getMovieId());
        } else {

            for(int i=0;i<value-1;i++){
                param.append(likeMovies.get(r.nextInt(likeMovies.size())).getMovie().getMovieId()).append(",");
            }
            param.append(likeMovies.get(r.nextInt(likeMovies.size())).getMovie().getMovieId());
        }
        return param;
    }

    private StringBuilder doHistory(int value,StringBuilder param,List<History> histories,Random r){
        if(value==1){
            System.out.println(histories.get(0).getMovie().getMovieId());
            param.append(histories.get(0).getMovie().getMovieId());
        }else {
            for(int i=0;i<value-1;i++){
                param.append(histories.get(i).getMovie().getMovieId()).append(",");
            }
            param.append(histories.get(value - 1).getMovie().getMovieId());
        }
        return param;
    }

    //优质评论
    private void goodComment(Model model){
        List<User> users=userR.findUserByAuth();
        if(users==null||users.size()==0){
            model.addAttribute("hasAuth",false);
        }else{
            if(users.size()==1){
                List<Comment> comments=commentR.findCommentByUserId(users.get(0).getUserId());
                if(comments.size()==0){
                    model.addAttribute("hasAuth",false);
                    return;
                }else{
                    int value=Math.min(comments.size(),4);
                    List<Comment> result=new ArrayList<Comment>();
                    List<Movie> movies=new ArrayList<Movie>();
                    for(int j=0;j<value;j++){
                        result.add(comments.get(j));
                        movies.add(movieR.findMovieById(comments.get(j).getMovie().getMovieId()));
                    }
                    model.addAttribute("Comments",result);
                    model.addAttribute("ToMovie",movies);
                    model.addAttribute("Author",users.get(0));
                }
            }else{
                Random r=new Random();
                int i=r.nextInt(users.size());
                List<Comment> comments=commentR.findCommentByUserId(users.get(i).getUserId());
                if(comments.size()==0){
                    model.addAttribute("hasAuth",false);
                    return;
                }else{
                    int value=Math.min(comments.size(),4);
                    List<Comment> result=new ArrayList<Comment>();
                    List<Movie> movies=new ArrayList<Movie>();
                    for(int j=0;j<value;j++){
                        result.add(comments.get(j));
                        movies.add(movieR.findMovieById(comments.get(j).getMovie().getMovieId()));
                    }
                    model.addAttribute("Comments",result);
                    model.addAttribute("ToMovie",movies);
                    model.addAttribute("Author",users.get(i));
                }
            }
            model.addAttribute("hasAuth",true);
        }
    }
    //@GetMapping("/selfspace")
    //public ModelAndView SelfSpace(HttpServletRequest request,Model model) {
    //    ModelAndView modelAndView = new ModelAndView();
    //    modelAndView.setViewName("selfspace");
    //    model.addAttribute("isLogin", request.getSession().getAttribute("userId") != null);
    //    return modelAndView;
    //}
    @GetMapping("/movieinfo")
    public ModelAndView MovieInfo(HttpServletRequest request,Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("movieinfo");
        if(request.getSession().getAttribute("userId")!=null){
            model.addAttribute("isLogin",true);
            model.addAttribute("user",userR.findUserById(((Long) request.getSession().getAttribute("userId"))));
        }else{
            model.addAttribute("isLogin",false);
        }
        return modelAndView;
    }

    @GetMapping("/list")
    public ModelAndView list(HttpServletRequest request,Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("list");
        if(request.getSession().getAttribute("userId")!=null){
            model.addAttribute("isLogin",true);
            model.addAttribute("user",userR.findUserById(((Long) request.getSession().getAttribute("userId"))));
        }else{
            model.addAttribute("isLogin",false);
        }
        return modelAndView;
    }

    @GetMapping("/help")
    public ModelAndView help(HttpServletRequest request,Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("help");
        if(request.getSession().getAttribute("userId")!=null){
            model.addAttribute("isLogin",true);
            model.addAttribute("user",userR.findUserById(((Long) request.getSession().getAttribute("userId"))));
        }else{
            model.addAttribute("isLogin",false);
        }
        return modelAndView;
    }
}