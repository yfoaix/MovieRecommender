package com.yingjianren.yingjianren.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

import com.yingjianren.yingjianren.entity.UserRepository;
import com.yingjianren.yingjianren.entity.Comment;
import com.yingjianren.yingjianren.entity.CommentRepository;
import com.yingjianren.yingjianren.entity.Movie;
import com.yingjianren.yingjianren.entity.MovieRepository;
import com.yingjianren.yingjianren.entity.User;
import com.yingjianren.yingjianren.model.CommentArea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MovieController {
    private static final String MOVIE_INFO_ID_URL = "/movieinfo/{movieId}";
    private static final String MOVIE_INFO_URL = "/movieinfo/";
    private static final String REDIRECT_MOVIE_INFO_URL = "redirect:"+MOVIE_INFO_URL;

    @Autowired
    MovieRepository movieR;

    @Autowired
    UserRepository userR;

    @Autowired
    CommentRepository commentR;

    @GetMapping(MOVIE_INFO_ID_URL)
    //@ResponseBody
    public ModelAndView getMoviePage(@PathVariable(value="movieId") Long movieId, HttpServletRequest req){
        ModelAndView movieInfo = new ModelAndView("movieInfo");
        // 获取用户id
        Long userId= (Long) req.getSession().getAttribute("userId");
        //Long userId = user.getUserId();
         //Long userId = 145335L;

        // 电影的详情信息
        Movie movie = movieR.findMovieById(movieId);
        // 电影的评论信息
        List<CommentArea> commentAreaList = new ArrayList<>();

        // 分页查询查找非用户本人的评论
        String sortType = "comment_id";
        int page = 0;
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.ASC, sortType);
        List<Comment> commentList = commentR.findOtherCommentByUserID(userId, movieId, pageable);

        // 查询用户本人的评论
        Comment commentSelf = commentR.findOneCommentByUserIdAndMovieId(userId, movieId);

        // 将所有评论存入List, 自己的评论放在首位
        if(commentSelf!=null){
            commentList.add(0, commentSelf);
        }
        if(commentList!=null){
            for(Comment c: commentList){
                commentAreaList.add(new CommentArea(c.getUser().getUserName(), c.getContent(), c.getCreatedAt()));
            }
        }
        
        movieInfo.addObject("movie", movie);
        movieInfo.addObject("commentAreaList", commentAreaList);
        movieInfo.addObject("movieId", movieId);
        //System.out.println(movieId);
        //return commentAreaList;
        return movieInfo;
    }

    @PostMapping(MOVIE_INFO_ID_URL)
    public String sendComment(@PathVariable(value="movieId") Long movieId, String content, HttpServletRequest req){
        // 获取用户id
        Long userId= (Long) req.getSession().getAttribute("userId");
        System.out.println(userId);
        //User user = (User)req.getSession().getAttribute("user");
        //Long userId = user.getUserId();
        //Long userId = 145335L;

        // 搜索用户是否评论过该电影
        Comment comment = commentR.findOneCommentByUserIdAndMovieId(userId, movieId);
        // 获取当前时间以及设置格式
        Date date = new Date(System.currentTimeMillis());
        
        // 评论过
        if(comment!=null){
            comment.setContent(content);
            comment.setCreatedAt(date);
            comment.setRating(0);
            System.out.println("ping"+comment);
        }
        // 未评论过
        else{
            comment = new Comment();
            comment.setContent(content);
            comment.setCreatedAt(date);
            comment.setRating(0);
            comment.setUser(userR.findUserById(userId));
            comment.setMovie(movieR.findMovieById(movieId));
            System.out.println("noping"+comment);
        }
        // 更新或插入
        commentR.save(comment);
        return REDIRECT_MOVIE_INFO_URL+movieId;
    }
}