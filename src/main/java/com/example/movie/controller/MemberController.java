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
        MemberService memberService = new MemberService();
        log.info("doget");
        String action = req.getParameter("action");

        if (action == null){
            action = "list";
        }
        log.info(action);
        switch (action){
            case "register": // 회원가입 페이지
                req.getRequestDispatcher("/WEB-INF/member/register.jsp").forward(req,resp);
                break;
            case "modify": // 수정 페이지
                try {
                    memberService.getWithMemberId(req); // 회원정보 가져오기
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                req.getRequestDispatcher("/WEB-INF/member/modify.jsp").forward(req,resp);
                break;
            case "remove": // 회원 탈퇴
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
        MemberService memberService = new MemberService();
        log.info("dopost");

        String action = req.getParameter("action");
        if (action == null){
            action = "list";
        }
        log.info(action);
        switch (action){
            case "register": // 회원정보 등록
                try {
                    memberService.addMember(req);
                } catch (Exception e) {
                    log.info("회원등록 post 컨트롤러 이상 : " + e.getMessage());
                }
                resp.sendRedirect("/login");
                break;
            case "modify": // 회원 정보 수정
                try {
                    memberService.modifyMember(req);
                } catch (Exception e) {
                    log.info("수정 post 컨트롤러 이상 : " + e.getMessage());
                }
                resp.sendRedirect("/list.mypage?action=list");
                break;
            case "idCheck": // 중복 아이디 체크
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
            case "nameCheck": // 중복 이름 체크
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
            case "nickCheck": // 중복 닉네임 체크
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
