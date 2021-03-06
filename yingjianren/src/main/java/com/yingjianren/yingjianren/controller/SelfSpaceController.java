package com.yingjianren.yingjianren.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.yingjianren.yingjianren.entity.Comment;
import com.yingjianren.yingjianren.entity.CommentRepository;
import com.yingjianren.yingjianren.entity.History;
import com.yingjianren.yingjianren.entity.HistoryRepository;
import com.yingjianren.yingjianren.entity.LikeMovie;
import com.yingjianren.yingjianren.entity.LikeMovieRepository;
import com.yingjianren.yingjianren.entity.User;
import com.yingjianren.yingjianren.entity.UserRepository;
import com.yingjianren.yingjianren.model.DeleteObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping(value = "/selfspace")
public class SelfSpaceController {
    private static final String DELETE_HISTORY_URL = "/delete/history";
    private static final String DELETE_COMMENT_URL = "/delete/comment";
    private static final String UPDATE_USERNAME_URL = "/username";
    private static final String UPDATE_BIOGRAPHY_URL = "biography";
    private static final String UPDATE_IDENTITY_URL = "/indentity/v";
    //private static final String SELFSPACE_URL = "/history/{movieId}";

    @Autowired
    HistoryRepository historyR;

    @Autowired
    CommentRepository commentR;

    @Autowired
    UserRepository userR;

    @Autowired
    LikeMovieRepository likeMovieR;

    @GetMapping({""})
    public ModelAndView getAllPersonalInfo(HttpServletRequest req){
        // 分页
        int page = 0;
        int pageSize = 20;
        Pageable page_comment = PageRequest.of(page, pageSize, Sort.Direction.DESC, "created_at");
        Pageable page_history = PageRequest.of(page, pageSize, Sort.Direction.DESC, "created_at");
        Pageable page_like = PageRequest.of(page, pageSize, Sort.Direction.DESC, "movie_id");

        ModelAndView view = new ModelAndView("selfSpace");

        // 获取用户id
        Long userId= (Long) req.getSession().getAttribute("userId");

        // 获取用户的评论记录表单
        List<Comment> commentList = commentR.findAllCommentByUserIDPage(userId, page_comment);

        // 获取用户的浏览记录列表
        List<History> historyList = historyR.findHistoryByUserId(userId, page_history);

        // 获取用户的喜欢列表
        List<LikeMovie> likeMovieList = likeMovieR.findLikeByUserIdPage(userId, page_like);

        // 设置上下文变量
        view.addObject("user", userR.findUserById(userId));
        view.addObject("commentList", commentList);
        view.addObject("historyList", historyList);
        view.addObject("likeMovieList",likeMovieList);
        view.addObject("isLogin", req.getSession().getAttribute("userId") != null);
        if(req.getSession().getAttribute("userId")!=null){
            view.addObject("user",userR.findUserById(((Long) req.getSession().getAttribute("userId"))));
        }
        return view;
    }

    @PostMapping(DELETE_HISTORY_URL)
    @ResponseBody
    public DeleteObject deleteHistory(String historyIdStr) {
        // 字符串转整型
        char []idChars = historyIdStr.toCharArray();
        Long historyId = 0L;
        for(int i=0; i<idChars.length; i++){
            historyId = historyId*10+(idChars[i] - '0');
        }
        int deleteLine = historyR.deleteHistoryById(historyId);
        return new DeleteObject(historyId, deleteLine);
    }

    @PostMapping(DELETE_COMMENT_URL)
    @ResponseBody
    public DeleteObject deleteComment(String commentIdStr) {
        System.out.println(commentIdStr);
        // 字符串转整型
        char []idChars = commentIdStr.toCharArray();
        Long commentId = 0L;
        for(int i=0; i<idChars.length; i++){
            commentId = commentId*10+(idChars[i] - '0');
        }
        System.out.println(commentId);
        int deleteLine = commentR.deleteCommentById(commentId);
        return new DeleteObject(commentId, deleteLine);
    }

    // 改用户名
    @PostMapping(UPDATE_USERNAME_URL)
    @ResponseBody
    public int updateUserName(String userName, HttpServletRequest req) {
        // 获取用户id
        Long userId = (Long) req.getSession().getAttribute("userId");
        User user = userR.findUserById(userId);
        user.setUserName(userName);
        try{
            userR.save(user);
            return 1;
        }
        catch(Exception e){
            return 0;
        }
    }

    // 改简介
    @PostMapping(UPDATE_BIOGRAPHY_URL)
    @ResponseBody
    public int updateBiography(String biography, HttpServletRequest req) {
        // 获取用户id
        Long userId = (Long) req.getSession().getAttribute("userId");
        User user = userR.findUserById(userId);
        user.setBiography(biography);
        try{
            userR.save(user);
            return 1;
        }
        catch(Exception e){
            return 0;
        }
    }


    // 大V认证
    @PostMapping(UPDATE_IDENTITY_URL)
    @ResponseBody
    public int identityV(HttpServletRequest req) {
        // 获取用户id
        Long userId = (Long) req.getSession().getAttribute("userId");
        User user = userR.findUserById(userId);
        user.setAuthentication(true);
        try{
            userR.save(user);
            return 1;
        }
        catch(Exception e){
            return 0;
        }
    }

}