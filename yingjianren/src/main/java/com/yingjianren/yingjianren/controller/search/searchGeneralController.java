package com.yingjianren.yingjianren.controller.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yingjianren.yingjianren.entity.Movie;
import com.yingjianren.yingjianren.entity.MovieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * serchGeneralController
 */
// 范搜索控制类
@Controller
public class searchGeneralController {
    private static final String SEARCH_GENERAL_URL = "/Search";

    @Autowired
    MovieRepository movieR;

    @ResponseBody
    @GetMapping(SEARCH_GENERAL_URL+"/{keywords}")
    public List<Movie> searchGeneral(@PathVariable String keywords){
        //String keywords = request.getParameter("keywords");
        System.out.println(keywords);
        List<Movie> movieList = movieR.findMovieByKeywords(keywords);
        for (Movie m : movieList) {
            System.out.println(m.getMoiveName());
        }
        return movieList;
    }
}