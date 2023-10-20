package com.example.movie.service;


import com.example.movie.model.dao.MyPageDAO;
import com.example.movie.model.dto.*;

import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

@Log4j2
public class MyPageService {

    private final MyPageDAO myPageDAO;

    public MyPageService() {
        myPageDAO = new MyPageDAO();
    }

    public void getMyBoard(HttpServletRequest request) throws Exception { //  내 게시물 보기
        List<BoardDTO> boardDTOList = myPageDAO.viewMyContent((String)request.getSession().getAttribute("sessionId"));
        request.setAttribute("boardDTOList", boardDTOList);

        log.info(boardDTOList);

    }

    public void getMyComments(HttpServletRequest request) throws Exception {
        // 내 댓글 보기
        List<CommentDTO> commentDTOList = myPageDAO.viewMyComment((String)request.getSession().getAttribute("sessionId"));
        request.setAttribute("commentDTOList", commentDTOList);

        log.info(commentDTOList);

    }

    public void getMyReviews(HttpServletRequest request) throws Exception {
        // 내 리뷰 보기

        List<ReviewDTO> reviewDTOList = myPageDAO.viewMyReview((String)request.getSession().getAttribute("sessionId"));
        request.setAttribute("reviewDTOList", reviewDTOList);

        log.info(reviewDTOList);

    }

    public void getMyZZimMovies(HttpServletRequest request) throws Exception {
        // 내 찜영화 보기
        List<MovieDTO> zzimMovieList = myPageDAO.viewMyZZim((String)request.getSession().getAttribute("sessionId"));
        request.setAttribute("zzimMovieList", zzimMovieList);

        log.info(zzimMovieList);

    }



}