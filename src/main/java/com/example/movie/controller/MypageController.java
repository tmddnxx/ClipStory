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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("*.mypage")
@Log4j2
public class MypageController extends HttpServlet {
    MyPageService myPageService = new MyPageService();
    MemberService memberService = new MemberService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("mypage doget");
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if (action == null){
            action = "list";
        }
        log.info(action);

        switch (action) {
            case "list": // mypage 메인
                try {
                    memberService.getWithMemberId(req);
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

}
