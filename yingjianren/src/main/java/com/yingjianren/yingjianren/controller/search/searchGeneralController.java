package com.yingjianren.yingjianren.controller.search;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.yingjianren.yingjianren.entity.Movie;
import com.yingjianren.yingjianren.entity.MovieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * serchGeneralController
 */
// 范搜索控制类
@Controller
public class SearchGeneralController {

    @Autowired
    MovieRepository movieR;

    @GetMapping("/Search")
    public ModelAndView Search() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("search");
        return modelAndView;
    }
    //@ResponseBody
    //@GetMapping(SEARCH_GENERAL_URL)
    //public List<Movie> searchGeneral(){
    //    //String keywords = request.getParameter("keywords");
    //    List<Movie> movieList = movieR.findMovieByKeywords("喜剧");
    //    return movieList;
    //}
}