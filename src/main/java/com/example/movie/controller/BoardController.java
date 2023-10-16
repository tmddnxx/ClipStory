package com.example.movie.controller;

import com.example.movie.model.dto.BoardDTO;
import com.example.movie.service.AdminService;
import com.example.movie.service.BoardService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@WebServlet("*.board")
public class BoardController extends HttpServlet {
    BoardService boardService = new BoardService();
    AdminService adminService = new AdminService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        if(action == null){
            action = "list";
        }

        switch (action) {
            case "list" : // 게시물 목록뷰
                try {
                    boardService.listBoard(req);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                req.getRequestDispatcher("/WEB-INF/board/boardList.jsp").forward(req, resp);
                break;

            case "add" : // 게시물 추가 뷰
                req.getRequestDispatcher("/WEB-INF/board/boardAdd.jsp").forward(req, resp);
                break;

            case "modify" : // 게시물 수정 뷰
                try{
                    boardService.getBoard(req);
                    req.getRequestDispatcher("/WEB-INF/board/boardModify.jsp").forward(req, resp);
                } catch (Exception e){
                    log.error(e.getMessage());
                    throw new ServletException("read error");
                }
                break;

            case "remove" : // 삭제
                boardService.removeBoard(req);
                resp.sendRedirect("list.board?action=list");
                break;

            case "get" : // 상세뷰
                boardService.getBoard(req);
                boardService.increaseHit(req);
                req.getRequestDispatcher("/WEB-INF/board/boardGet.jsp").forward(req, resp);
                break;

            case "noticeList" : // 공지사항 목록
                try {
                    adminService.adminListNotice(req);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                req.getRequestDispatcher("/WEB-INF/board/noticeList.jsp").forward(req, resp);
                break;

            case "getNotice" : // 공지사항 상세뷰
                adminService.adminGetNotice(req);
                req.getRequestDispatcher("/WEB-INF/board/noticeGet.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BoardService boardService = new BoardService();

        String action = req.getParameter("action");
        if(action == null){
            action = "list";
        }

        switch (action) {
            case "list" : // 게시물 목록
                try {
                    boardService.listBoard(req);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                req.getRequestDispatcher("/WEB-INF/board/boardList.jsp").forward(req, resp);
                break;

            case "add" : // 게시물 추가
                boardService.addBoard(req);
                resp.sendRedirect("list.board?action=list");
                break;

            case "modify" : // 게시물 수정
                boardService.modifyBoard(req);
                resp.sendRedirect("list.board?action=list");
        }
    }
}
