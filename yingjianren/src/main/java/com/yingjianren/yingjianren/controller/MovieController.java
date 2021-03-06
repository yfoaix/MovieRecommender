package com.yingjianren.yingjianren.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.yingjianren.yingjianren.entity.Comment;
import com.yingjianren.yingjianren.entity.CommentRepository;
import com.yingjianren.yingjianren.entity.History;
import com.yingjianren.yingjianren.entity.HistoryRepository;
import com.yingjianren.yingjianren.entity.LikeMovie;
import com.yingjianren.yingjianren.entity.LikeMovieRepository;
import com.yingjianren.yingjianren.entity.Movie;
import com.yingjianren.yingjianren.entity.MovieRepository;
import com.yingjianren.yingjianren.entity.Score;
import com.yingjianren.yingjianren.entity.ScoreRepository;
import com.yingjianren.yingjianren.entity.UserRepository;
import com.yingjianren.yingjianren.model.CommentArea;
import com.yingjianren.yingjianren.model.ScoreObject;

import com.yingjianren.yingjianren.service.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MovieController {
    private static final String MOVIE_INFO_ID_URL = "/movieinfo/{movieId}";
    private static final String MOVIE_LIKE_ID_URL = "/movieinfo/like/{movieId}";
    private static final String MOVIE_SCORE_ID_URL = "/movieinfo/score/{movieId}";

    @Autowired
    MovieRepository movieR;

    @Autowired
    UserRepository userR;

    @Autowired
    CommentRepository commentR;

    @Autowired
    HistoryRepository historyR;

    @Autowired
    LikeMovieRepository likeMovieR;

    @Autowired
    ScoreRepository scoreR;

    @GetMapping(MOVIE_INFO_ID_URL)
    // @ResponseBody
    public ModelAndView getMoviePage(@PathVariable(value = "movieId") Long movieId, HttpServletRequest req,Model model) {
        // 先查找电影id是否存在
        if (movieR.findIfExistByMovieId(movieId) == 0L) {
            ModelAndView movieInfo = new ModelAndView("error");
            return movieInfo;
        }
        ModelAndView movieInfo = new ModelAndView("movieInfo");
        // 获取用户id
        Long userId = (Long) req.getSession().getAttribute("userId");


        // 电影的详情信息
        Movie movie = movieR.findMovieById(movieId);
        // 电影的评论信息
        List<CommentArea> commentAreaList = new ArrayList<>();


        // 分页查询查找非用户本人的评论
        String sortType = "comment_id";
        int page = 0;
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.ASC, sortType);

        // 如果用户登录了
        if (userId != null) {
            List<Comment> commentList = commentR.findOtherCommentByUserID(userId, movieId, pageable);

            // 查询用户本人的评论
            Comment commentSelf = commentR.findOneCommentByUserIdAndMovieId(userId, movieId);

            // 将所有评论存入List, 自己的评论放在首位
            if (commentSelf != null) {
                commentList.add(0, commentSelf);
            }
            if (commentList != null) {
                for (Comment c : commentList) {
                    commentAreaList.add(new CommentArea(c.getUser().getUserName(), c.getContent(), c.getCreatedAt(),
                            c.getUser().getImgUrl(), c.getUser().getUserId()));
                }
            }

            // 添加/更新用户的浏览记录
            Date now = new Date(System.currentTimeMillis());
            int isExistHistory = historyR.findIfExistByUserIdAndMovieId(movieId, userId);
            History hty = new History();
            // 判断之前是否浏览过
            if (isExistHistory > 0) {
                hty = historyR.findRecentHistoryByUserIDAndMovieId(userId, movieId);
                hty.setCreatedAt(now);
            } else {
                hty.setCreatedAt(now);
                hty.setMovie(movieR.findMovieById(movieId));
                hty.setUser(userR.findUserById(userId));
            }
            historyR.save(hty);
        }
        // 用户未登录
        else {
            // 查询所有评论
            List<Comment> commentList = commentR.findAllCommentByMovieID(movieId, pageable);
            if (commentList != null) {
                for (Comment c : commentList) {
                    commentAreaList.add(new CommentArea(c.getUser().getUserName(), c.getContent(), c.getCreatedAt(),
                            c.getUser().getImgUrl(), c.getUser().getUserId()));
                }
            }
        }
        // 判断用户是否喜欢该电影,以及是否评过分
        if (userId != null) {
            int isLike = likeMovieR.findIfExistLike(userId, movieId);
            int score = 2;
            if (scoreR.findIfExistScore(userId, movieId) > 0) {
                score = scoreR.fingScoreByUserIdAndMovieId(movieId, userId);
            }
            movieInfo.addObject("isLike", isLike);
            movieInfo.addObject("myscore", score / 2);
        } else {
            movieInfo.addObject("isLike", 0);
            movieInfo.addObject("myscore", 1);
        }

        movieInfo.addObject("movie", movie);
        movieInfo.addObject("commentAreaList", commentAreaList);
        movieInfo.addObject("isLogin", req.getSession().getAttribute("userId") != null);
        if (req.getSession().getAttribute("userId") != null) {
            movieInfo.addObject("user", userR.findUserById(((Long) req.getSession().getAttribute("userId"))));
        }
        List<Movie> movies=movieR.findMovieByScore();//随机找出高分电影
        Random r=new Random();
        String url = "http://10.236.11.10:5000/sametype";
        String param = "id="+movieId+"&size=10";
        try{
            doAlgorithm1(url, param, model,"type1");
        } catch (IOException e) {
            List<Movie> recommend=new ArrayList<Movie>();
            for(int i=0;i<3;i++){
                recommend.add(movies.get(r.nextInt(1665)));
            }
            model.addAttribute("type1",recommend);
        }
        url = "http://10.236.11.10:5000/other";
        try{
            doAlgorithm2(url, param, model,"type2");
        } catch (IOException e) {
            List<Movie> recommend=new ArrayList<Movie>();
            for(int i=0;i<3;i++){
                recommend.add(movies.get(r.nextInt(1665)));
            }
            model.addAttribute("type2",recommend);
        }
        return movieInfo;
    }

    //调用算法
    private void doAlgorithm1(String url, String param, Model model, String type) throws IOException {
        System.out.println(url+"?"+param);
        String stringList= Algorithm.sendGet(url, param.toString());
        stringList= stringList.substring(1,stringList.length()-1);
        String[] strArr = stringList.split(", ");
        List<Movie> recommend=new ArrayList<Movie>();
        for(String id :strArr){
            recommend.add(movieR.findMovieById( Long.parseLong(id)));
        }
        recommend.remove(0);
        recommend.sort(new Comparator<Movie>() {
            @Override
            public int compare(Movie arg0, Movie arg1) {
                int mark = 1;
                try {
                    float i0=arg0.getScore();
                    float i1=arg1.getScore();
                    if (i0 > i1) {
                        mark = -1;
                    }
                    if (i0==i1) {
                        mark = 0;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return mark;
            } // compare
        });
        List<Movie> result=new ArrayList<Movie>();
        result.add(recommend.get(0));
        result.add(recommend.get(1));
        result.add(recommend.get(2));
        model.addAttribute(type,result);
    }

    private void doAlgorithm2(String url, String param, Model model, String type) throws IOException {
        System.out.println(url+"?"+param);
        String stringList= Algorithm.sendGet(url, param.toString());
        stringList= stringList.substring(1,stringList.length()-1);
        String[] strArr = stringList.split(", ");
        List<Movie> recommend=new ArrayList<Movie>();
        for(String id :strArr){
            recommend.add(movieR.findMovieById( Long.parseLong(id)));
        }
        recommend.remove(0);
        recommend.sort(new Comparator<Movie>() {
            @Override
            public int compare(Movie arg0, Movie arg1) {
                int mark = 1;
                try {
                    float i0=arg0.getScore();
                    float i1=arg1.getScore();
                    if (i0 > i1) {
                        mark = -1;
                    }
                    if (i0==i1) {
                        mark = 0;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return mark;
            } // compare
        });
        List<Movie> result=new ArrayList<Movie>();
        result.add(recommend.get(3));
        result.add(recommend.get(4));
        result.add(recommend.get(5));
        model.addAttribute(type,result);
    }


    @PostMapping(MOVIE_INFO_ID_URL)
    @ResponseBody
    public List<CommentArea> sendComment(@PathVariable(value = "movieId") Long movieId, String content,
            HttpServletRequest req) {
        // 获取用户id
        Long userId = (Long) req.getSession().getAttribute("userId");

        // 搜索用户是否评论过该电影
        Comment comment = commentR.findOneCommentByUserIdAndMovieId(userId, movieId);
        // 获取当前时间以及设置格式
        Date date = new Date(System.currentTimeMillis());

        // 评论过
        if (comment != null) {
            comment.setContent(content);
            comment.setCreatedAt(date);
            comment.setRating(0);
            System.out.println("ping" + comment);
        }
        // 未评论过
        else {
            comment = new Comment();
            comment.setContent(content);
            comment.setCreatedAt(date);
            comment.setRating(0);
            comment.setUser(userR.findUserById(userId));
            comment.setMovie(movieR.findMovieById(movieId));
            System.out.println("noping" + comment);
        }
        // 更新或插入新评论
        commentR.save(comment);

        // 电影的评论信息列表
        List<CommentArea> commentAreaList = new ArrayList<>();

        // 分页查询查找非用户本人的评论
        String sortType = "comment_id";
        int page = 0;
        int pageSize = 5;
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.ASC, sortType);

        List<Comment> commentList = commentR.findOtherCommentByUserID(userId, movieId, pageable);

        // 查询用户本人的评论
        Comment commentSelf = commentR.findOneCommentByUserIdAndMovieId(userId, movieId);

        // 将所有评论存入List, 自己的评论放在首位
        if (commentSelf != null) {
            commentList.add(0, commentSelf);
        }
        if (commentList != null) {
            for (Comment c : commentList) {
                commentAreaList.add(new CommentArea(c.getUser().getUserName(), c.getContent(), c.getCreatedAt(),
                        c.getUser().getImgUrl(), c.getUser().getUserId()));
            }
        }

        // return REDIRECT_MOVIE_INFO_URL + movieId;
        return commentAreaList;
    }

    @PostMapping(MOVIE_LIKE_ID_URL)
    @ResponseBody
    public String changeLike(@PathVariable Long movieId, HttpServletRequest req) {
        // 获取用户id
        Long userId = (Long) req.getSession().getAttribute("userId");
        LikeMovie lm = new LikeMovie();
        int isLike = likeMovieR.findIfExistLike(userId, movieId);
        if (isLike == 0) {
            lm.setMovie(movieR.findMovieById(movieId));
            lm.setUser(userR.findUserById(userId));
            likeMovieR.save(lm);
            return "添加喜欢成功！";
        } else {
            likeMovieR.deleteLikeByUserIdAndMovieId(movieId, userId);
            return "取消喜欢成功！";
        }
    }

    @PostMapping(MOVIE_SCORE_ID_URL)
    @ResponseBody
    public ScoreObject scoreMovie(@PathVariable Long movieId, int score, HttpServletRequest req, Model model) {
        // 获取用户id
        Long userId = (Long) req.getSession().getAttribute("userId");
        int isScore = scoreR.findIfExistScore(userId, movieId);
        System.out.println(score);
        if (isScore == 0) {
            Score scoreObj = new Score();
            scoreObj.setScore(score * 2);
            scoreObj.setMovie(movieR.findMovieById(movieId));
            scoreObj.setUser(userR.findUserById(userId));
            scoreR.save(scoreObj);
            model.addAttribute("myscore", score);
            return new ScoreObject(1, score);
        } else {
            return new ScoreObject(0, scoreR.fingScoreByUserIdAndMovieId(movieId, userId) / 2);
        }
    }
}