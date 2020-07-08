package com.yingjianren.yingjianren.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.yingjianren.yingjianren.entity.Movie;
import com.yingjianren.yingjianren.entity.MovieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class SearchController {
    public final static int pageSize = 20;
    @Autowired
    MovieRepository movieR;

    //@GetMapping("/search")
    //public ModelAndView SelfSpace() {
    //    ModelAndView modelAndView = new ModelAndView();
    //    modelAndView.setViewName("search");
    //    return modelAndView;
    //}
    @GetMapping("/search")
    public ModelAndView Search(@RequestParam(value="keywords",required=false) String keywords,
    @RequestParam(value="order",required=false) String order,
    @RequestParam(value="duration",required=false) String duration,
    @RequestParam(value="year",required=false) String year,
    @RequestParam(value="language",required=false) String language,
    @RequestParam(value="genre",required=false) String genre,
    @RequestParam(value="pageIndex",required=false) String page,
    @RequestParam(value="size",required=false) String size,
    HttpServletRequest request,
    Model model){
        //String keywords = request.getParameter("keywords");
        
        int pageNum = 0;
        int sizeNum = pageSize;
        int orderNum = 0;
        int durationUp = 999;
        int durationDown = 0;
        int yearUp = 3000;
        int yearDown = 0;
        boolean isGeneral = true;
        if(keywords==null||keywords.isEmpty()){
            keywords = "";
        }
        if(language==null||language.isEmpty()){
            language = "";
        }
        else{
            isGeneral = false;
        }
        if(genre==null||genre.isEmpty()){
            genre = "";
        }
        else{
            isGeneral = false;
        }
        if(order!=null&&!order.isEmpty()){
            isGeneral = false;
            try{
                orderNum = Integer.parseInt(order);
            }
            catch(Exception e){
                orderNum = 0;
            }
        }
        if(duration!=null&&!duration.isEmpty()){
            String[] strs = duration.split("t");
            isGeneral = false;
            try{
                
                durationDown = Integer.parseInt(strs[0]);
                durationUp = Integer.parseInt(strs[1]);
            }
            catch(Exception e){
                durationUp = 999;
                durationDown = 0;
            }
        }
        if(year!=null&&!year.isEmpty()){
            String[] strs = year.split("t");
            isGeneral = false;
            try{
                
                yearDown = Integer.parseInt(strs[0]);
                yearUp = Integer.parseInt(strs[1]);
            }
            catch(Exception e){
                yearUp = 3000;
                yearDown = 0;
            }
        }
        if(page!=null&&!page.isEmpty()){
            
            try{
                pageNum = Integer.parseInt(page)-1;
            }
            catch(Exception e){
                pageNum = 0;
            }
        }
        if(size!=null&&!size.isEmpty()){
            try{
                sizeNum = Integer.parseInt(size);
            }
            catch(Exception e){
                sizeNum = pageSize;
            }
        }

        Pageable pageable;
        Page<Movie> moviePage;
        switch(orderNum){
            case 1:
            case 4:
                pageable = PageRequest.of(pageNum, sizeNum,Sort.Direction.DESC,"vote");
                break;
            case 2:
                pageable = PageRequest.of(pageNum, sizeNum,Sort.Direction.DESC,"score");
                break;
            case 3:
                pageable = PageRequest.of(pageNum, sizeNum,Sort.Direction.DESC,"year");
                break;
            default:
                pageable = PageRequest.of(pageNum, sizeNum);
                break;
        }
        
        
        if(isGeneral){
            moviePage = movieR.findMovieByKeywords(keywords,pageable);
        }
        else{
            // List<Long> minuteList = movieR.findIdByMinute(durationDown, durationUp);
            // List<Long> genresList = movieR.findIdByGenres(genre);
            // List<Long> yearList = movieR.findIdByYear(yearDown, yearUp);
            // List<Long> languageList = movieR.findIdByLanguage(language);
            // List<Long> nameList = movieR.findMovieByName(keywords);
            moviePage = movieR.findMovieByAttr(durationDown,durationUp, yearDown,yearUp,genre, language,keywords, pageable);
        }
        
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("search");
        modelAndView.addObject("Movies", moviePage.getContent());
        model.addAttribute("isLogin", request.getSession().getAttribute("userId") != null);
        model.addAttribute("totalPages", moviePage.getTotalPages());
        model.addAttribute("pageIndex", pageNum+1);
        model.addAttribute("size", sizeNum);
        model.addAttribute("keywords", keywords);
        model.addAttribute("order", order);
        model.addAttribute("duration", duration);
        model.addAttribute("year", year);
        model.addAttribute("language", language);
        model.addAttribute("genre", genre);
        return modelAndView;
    }
    // 搜索功能
    // @PostMapping(SEARCH_MOVIE_URL)
    // @ResponseBody
    // public List<Movie> searchMovie(){
    // List<Long> minuteList = new ArrayList<Long>();
    // minuteList = movieR.findIdbyMinute(0, 60);
    // List<Movie> movieList = movieR.findOrderByScore(minuteList);
    // for(Movie m:movieList){
    // System.out.println(m.getMoiveName());
    // }
    // return movieList;
    // }

}

