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

    //搜索页面
    @ResponseBody
    @PostMapping("/Search")
    public Page<Movie> Search(@RequestParam(value="keywords",required=false) String keywords,
    @RequestParam(value="page",required=false) String page) {
       int page = 0;
       int pageSize = 20;
       Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "score");
       List<Long> minuteList = movieR.findIdByMinute(1, 100);
       List<Long> genresList = movieR.findIdByGenres("动画");
       List<Long> yearList = movieR.findIdByYear(1990, 2010);
       List<Long> languageList = movieR.findIdByLanguage("法语");
       Page<Movie> moviePage = movieR.findMovieByOrder(minuteList, genresList, yearList, languageList, pageable);
       int i = 0;
       for (Movie m : moviePage.getContent()) {
           System.out.println((++i) + " " + m.getMoiveName() + " " + m.getLanguage() + " " + m.getMinute() + " "
                   + m.getGenres() + " " + m.getYear());
       }
       return moviePage;
    }
    //@GetMapping("/search")
    //public ModelAndView SelfSpace() {
    //    ModelAndView modelAndView = new ModelAndView();
    //    modelAndView.setViewName("search");
    //    return modelAndView;
    //}
    @GetMapping("/search")
    public ModelAndView SearchGeneral(@RequestParam(value="keywords",required=false) String keywords,
    @RequestParam(value="page",required=false) String page,
    HttpServletRequest request,
    Model model){
        //String keywords = request.getParameter("keywords");
        
        System.out.println(keywords);
        if(keywords==null){
            keywords = "";
        }
        int pageNum = 0;
        if(page!=null){
            try{
                pageNum = Integer.parseInt(page);
            }
            finally{
                pageNum = 0;
            }
        }
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<Movie> moviePage = movieR.findMovieByKeywords(keywords,pageable);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("search");
        modelAndView.addObject("Movies", moviePage.getContent());
        model.addAttribute("isLogin", request.getSession().getAttribute("userId") != null);
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

