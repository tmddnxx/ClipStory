package com.example.movie.controller;

import com.example.movie.model.dto.BoardDTO;
import com.example.movie.service.MemberService;
import com.example.movie.service.MyPageService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("*.mypage")
@Log4j2
public class MypageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MyPageService myPageService = MyPageService.INSTANCE;
        log.info("doget");
        String action = req.getParameter("action");
        if (action == null){
            action = "list";
        }
        log.info(action);
        switch (action) {
            case "list": // mypage 메인
                try {
                    myPageService.getMyBoard(req);
                    myPageService.getMyComments(req);
                    myPageService.getMyReviews(req);
                    myPageService.getMyZZimMovies(req);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                req.getRequestDispatcher("/WEB-INF/mypage/myPage.jsp").forward(req,resp);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MemberService memberService = MemberService.INSTANCE;
        log.info("dopost");

        String action = req.getParameter("action");
        if (action == null){
            action = "list";
        }
        log.info(action);
        switch (action){
            case "modify":
                try {
                    memberService.modifyMember(req);
                } catch (Exception e) {
                    log.info("수정 post 컨트롤러 이상 : " + e.getMessage());
                }
                resp.sendRedirect("/list.movie?action=list");
                break;
            case "remove":
                resp.sendRedirect("/list.movie?action=list");
                break;
        }
    }
}
