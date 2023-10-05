package com.example.movie.controller;

import com.example.movie.service.MemberService;
import com.example.movie.service.MovieService;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("*.movie")
@Log4j2
// 뉴스 이미지 파일 업로드 처리를 위해 @MultipartConfig를 추가
// 최대 파일 크기와 저장 위치를 지정
@MultipartConfig(maxFileSize = 2 * 1024 * 1024, location = "c:/upload")
public class MovieController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MovieService movieService = new MovieService();

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");

        String action = req.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "main" :
                movieService.listMovie(req);
                movieService.listOtt(req);
                req.getRequestDispatcher("/WEB-INF/movie/movieMain.jsp").forward(req, resp);
                break;
            case "list" : // 리스트를 들고오는 코드
                movieService.movieListAll(req);
                req.getRequestDispatcher("/WEB-INF/movie/movieList.jsp").forward(req, resp);
                break;
            case "remove":
                movieService.removeMovieDTO(req);
                resp.sendRedirect("list.movie?action=list");
                break;
            case "view":
                movieService.getMovieDTO(req);
                req.getRequestDispatcher("/WEB-INF/movie/movieView.jsp").forward(req, resp);
                break;
            case "zzimAdd":
                try {
                    JSONObject jsonObject = new JSONObject(); // json 정보를 담기 위해 객체 생성
                    // 성공, 실패의 결과를 json에 저장
                    if (movieService.insertMovieLike(req)) {
                        jsonObject.put("result", "true");
                    } else {
                        jsonObject.put("result", "false");
                    }
                    resp.getWriter().println(jsonObject.toJSONString());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "zzimRemove":
                try {
                    JSONObject jsonObject = new JSONObject(); // json 정보를 담기 위해 객체 생성
                    // 성공, 실패의 결과를 json에 저장
                    if (movieService.removeMovieLike(req)) {
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
