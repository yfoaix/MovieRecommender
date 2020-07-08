package com.yingjianren.yingjianren.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.yingjianren.yingjianren.entity.Comment;
import com.yingjianren.yingjianren.entity.CommentRepository;
import com.yingjianren.yingjianren.entity.LikeMovie;
import com.yingjianren.yingjianren.entity.LikeMovieRepository;
import com.yingjianren.yingjianren.entity.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OtherSpaceController {
    private static final String OTHER_SPACE_URL = "/otherspace/{userId}";

    @Autowired
    CommentRepository commentR;

    @Autowired
    UserRepository userR;

    @Autowired
    LikeMovieRepository likeMovieR;

    @GetMapping(OTHER_SPACE_URL)
    public ModelAndView viewOtherSpace(HttpServletRequest req){
        // 分页
        int page = 0;
        int pageSize = 20;
        Pageable page_comment = PageRequest.of(page, pageSize, Sort.Direction.DESC, "created_at");
        Pageable page_like = PageRequest.of(page, pageSize, Sort.Direction.DESC, "movie_id");

        ModelAndView view = new ModelAndView("selfSpace");

        // 获取用户id
        Long userId= (Long) req.getSession().getAttribute("userId");

        // 获取用户的评论记录表单
        List<Comment> commentList = commentR.findAllCommentByUserIDPage(userId, page_comment);

        // 获取用户的喜欢列表
        List<LikeMovie> likeMovieList = likeMovieR.findLikeByUserIdPage(userId, page_like);

        // 设置上下文变量
        view.addObject("user", userR.findUserById(userId));
        view.addObject("commentList", commentList);
        view.addObject("likeMovieList",likeMovieList);
        view.addObject("isLogin", req.getSession().getAttribute("userId") != null);
        if(req.getSession().getAttribute("userId")!=null){
            view.addObject("user",userR.findUserById(((Long) req.getSession().getAttribute("userId"))));
        }
         return view;
    }
}