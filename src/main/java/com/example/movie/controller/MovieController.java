package com.example.movie.controller;

import com.example.movie.service.MovieService;
import com.example.movie.service.ReviewService;
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
@MultipartConfig(maxFileSize = 2 * 1024 * 1024)
public class MovieController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MovieService movieService = new MovieService();
        ReviewService reviewService = new ReviewService();

        String action = req.getParameter("action");
        if (action == null) {
            action = "main";
        }

        switch (action) {
            case "main" : // 메인 페이지
                movieService.listMovie(req);
                movieService.listOtt(req);
                req.getRequestDispatcher("/WEB-INF/movie/movieMain.jsp").forward(req, resp);
                break;
            case "list" : // 리스트를 들고오는 코드
                movieService.listMovie(req);
                movieService.listOtt(req);
                req.getRequestDispatcher("/WEB-INF/movie/movieList.jsp").forward(req, resp);
                break;
            case "view": // 영화 상세 페이지
                movieService.getMovie(req);
                try {
                    reviewService.getReviews(req);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                req.getRequestDispatcher("/WEB-INF/movie/movieView.jsp").forward(req, resp);
                break;
            case "zzimAdd": // 찜 등록
                try {
                    JSONObject jsonObject = new JSONObject();
                    if (movieService.insertMovieLike(req)) {
                        jsonObject.put("result", "true");
                        log.info(true);
                    } else {
                        jsonObject.put("result", "false");
                        log.info(false);
                    }
                    resp.getWriter().println(jsonObject.toJSONString());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "zzimRemove": // 찜 해제
                try {
                    JSONObject jsonObject = new JSONObject(); // json 정보를 담기 위해 객체 생성
                    // 성공, 실패의 결과를 json에 저장
                    if (movieService.removeMovieLike(req)) {
                        jsonObject.put("result", "true");
                        log.info(true);
                    } else {
                        jsonObject.put("result", "false");
                        log.info(false);
                    }
                    resp.getWriter().println(jsonObject.toJSONString());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "myZZimRemove": // 마이 페이지 찜 삭제
                try {
                    JSONObject jsonObject = new JSONObject();
                    boolean result = movieService.removeMovieLike(req);
                    jsonObject.put("result", result ? "true" : "false");
                    resp.getWriter().println(jsonObject.toJSONString());
                } catch (Exception e) {
                    throw new RuntimeException(e);

                }
                resp.sendRedirect("/list.mypage");
                break;
        }

    }
}
