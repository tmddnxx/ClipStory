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

@WebServlet("*.member")
@Log4j2
public class MemberController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MemberService memberService = MemberService.INSTANCE;
        log.info("doget");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");
        String action = req.getParameter("action");
        if (action == null){
            action = "list";
        }
        log.info(action);
        switch (action){
            case "register":
                req.getRequestDispatcher("/WEB-INF/member/register.jsp").forward(req,resp);
                break;
            case "modify":
                MemberDTO memberDTO = null;
                try {
                    memberDTO = memberService.getWithMemberId(req.getParameter("memberId"));
                } catch (Exception e) {
                    log.info("수정 get 컨트롤러 이상 : " + e.getMessage());
                }

                HttpSession session = req.getSession();
                session.setAttribute("loginInfo", memberDTO);
                req.getRequestDispatcher("/WEB-INF/member/modify.jsp").forward(req,resp);
                break;
            case "remove":
                try {
                    memberService.removeMember(req);
                } catch (Exception e) {
                    log.info("삭제 get컨트롤러 이상 : " + e.getMessage());

                }
                resp.sendRedirect("/logout");
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MemberService memberService = MemberService.INSTANCE;
        log.info("dopost");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");

        String action = req.getParameter("action");
        if (action == null){
            action = "list";
        }
        log.info(action);
        switch (action){
            case "register":
                try {
                    memberService.addMember(req);
                } catch (Exception e) {
                    log.info("회원등록 post 컨트롤러 이상 : " + e.getMessage());
                }
                resp.sendRedirect("/login");
                break;
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
