package com.example.movie.controller;

import com.example.movie.service.MovieService;
import com.example.movie.service.SearchService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("*.search")
public class SearchController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SearchService searchService = new SearchService();
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=utf-8");

        String action = req.getParameter("action");
        if(action == null){
            req.getRequestDispatcher("/WEB-INF/movie/search.jsp").forward(req, resp);
        }

        switch (action){
            case "list" : // 검색목록 출력
                try {
                    searchService.SearchList(req);
                    searchService.SearchListM(req);
                    searchService.SearchListO(req);
                }catch (Exception e){
                    throw new RuntimeException(e);
                }
                req.getRequestDispatcher("/WEB-INF/movie/searchList.jsp").forward(req, resp);
                break;
        }
    }
}
