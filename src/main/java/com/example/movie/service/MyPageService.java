package com.example.movie.service;

import com.example.movie.model.dao.CommentDAO;
import com.example.movie.model.dao.MemberDAO;
import com.example.movie.model.dao.MyPageDAO;
import com.example.movie.model.dto.BoardDTO;
import com.example.movie.model.dto.CommentDTO;
import com.example.movie.model.dto.MemberDTO;

import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

@Log4j2
public enum MyPageService {
    INSTANCE, BoardDTO;

    private MyPageDAO myPageDAO;
    private CommentDAO commentDAO;

    MyPageService() {
        myPageDAO = new MyPageDAO();
    }

    public void getMyBoard(HttpServletRequest request) throws Exception { //  내 게시물 보기
        List<BoardDTO> boardDTOList = myPageDAO.viewMyContent((String)request.getSession().getAttribute("sessionId"));
        request.setAttribute("boardDTOList", boardDTOList);

        log.info(boardDTOList);

//        try {
//            myPageDAO.viewMyContent(boardDTOList);
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        }
//        return boardDTOList;
    }

    public void getMyComments(HttpServletRequest request) throws Exception {
        // 내 댓글 보기
        List<CommentDTO> commentDTOList = myPageDAO.viewMyComment((String)request.getSession().getAttribute("sessionId"));
        request.setAttribute("commentDTOList", commentDTOList);

        log.info(commentDTOList);

    }
}