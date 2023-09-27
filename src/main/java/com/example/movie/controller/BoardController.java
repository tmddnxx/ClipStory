package com.example.movie.controller;

import com.example.movie.model.dto.BoardDTO;
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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BoardService boardService = new BoardService();
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=utf-8");

        String action = req.getParameter("action");
        if(action == null){
            action = "list";
        }

        switch (action) {
            case "list" :
                try {
                    boardService.listBoard(req);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                req.getRequestDispatcher("/WEB-INF/board/boardList.jsp").forward(req, resp);
                break;
            case "add" :
                req.getRequestDispatcher("/WEB-INF/board/boardAdd.jsp").forward(req, resp);
                break;
            case "modify" :
                try{
                    boardService.getBoard(req);
                    req.getRequestDispatcher("/WEB-INF/board/boardModify.jsp").forward(req, resp);
                } catch (Exception e){
                    log.error(e.getMessage());
                    throw new ServletException("read error");
                }
            case "get" :
                boardService.getBoard(req);
                boardService.increaseHit(req);
                req.getRequestDispatcher("/WEB-INF/board/boardGet.jsp").forward(req, resp);
                break;


        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BoardService boardService = new BoardService();
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=utf-8");

        String action = req.getParameter("action");
        if(action == null){
            action = "list";
        }

        switch (action) {
            case "list" :
                try {
                    boardService.listBoard(req);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                req.getRequestDispatcher("/WEB-INF/board/boardList.jsp").forward(req, resp);
                break;

            case "add" :
                boardService.addBoard(req);
                resp.sendRedirect("list.board?action=list");
                break;

            case "remove" :
                boardService.removeBoard(req);
                resp.sendRedirect("list.board?action=list");
                break;

            case "modify" :
                boardService.modifyBoard(req);
                resp.sendRedirect("list.board?action=list");
        }
    }
}
