package com.example.movie.controller;

import com.example.movie.model.dto.MemberDTO;
import com.example.movie.service.MemberService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
@Log4j2
public class LoginController extends HttpServlet {
    private final MemberService memberService = MemberService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Login GET...");
        req.getRequestDispatcher("/WEB-INF/member/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Login POST...");

        try {
            MemberDTO memberDTO = memberService.login(req);
            log.info(memberDTO);
            if(memberDTO != null) {
                HttpSession session = req.getSession();
                session.setAttribute("loginInfo", memberDTO);
                session.setAttribute("sessionId", memberDTO.getMemberId()); // board에서 id값만 들고올려고 추가함
                session.setAttribute("nickName", memberDTO.getNickName()); // 닉네임
                log.info(memberDTO);
                resp.sendRedirect("list.movie?action=list");
                return;
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            resp.sendRedirect("/login?result=error");
            return;
        }
        resp.sendRedirect("/login?result=error");

    }
}
