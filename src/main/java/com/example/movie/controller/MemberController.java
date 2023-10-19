package com.example.movie.controller;

import com.example.movie.service.MemberService;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("*.member")
@Log4j2
public class MemberController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MemberService memberService = MemberService.INSTANCE;
        log.info("doget");
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
                try {
                    memberService.getWithMemberId(req);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
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
                resp.sendRedirect("/list.mypage?action=list");
                break;
            case "remove":
                resp.sendRedirect("/main.movie?action=main");
                break;
            case "idCheck":
                try {
                    JSONObject jsonObject = new JSONObject();

                    if (memberService.idCheck(req)) {
                        jsonObject.put("result", "true");
                    } else {
                        jsonObject.put("result", "false");
                    }
                    resp.getWriter().println(jsonObject.toJSONString());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "nameCheck":
                try {
                    JSONObject jsonObject = new JSONObject();

                    if (memberService.nameCheck(req)) {
                        jsonObject.put("result", "true");
                    } else {
                        jsonObject.put("result", "false");
                    }
                    resp.getWriter().println(jsonObject.toJSONString());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "nickCheck":
                try {
                    JSONObject jsonObject = new JSONObject();

                    if (memberService.nickCheck(req)) {
                        jsonObject.put("result", "true");
                    } else {
                        jsonObject.put("result", "false");
                    }
                    resp.getWriter().println(jsonObject.toJSONString());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }
}
