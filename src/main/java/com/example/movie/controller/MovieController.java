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
@MultipartConfig(maxFileSize = 2 * 1024 * 1024, location = "/upload")
public class MovieController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MovieService movieService = new MovieService();

        ReviewService reviewService = ReviewService.getInstance();

        String action = req.getParameter("action");
        if (action == null) {
            action = "main";
        }

        switch (action) {
            case "main" :
                movieService.listMovie(req);
                movieService.listOtt(req);
                req.getRequestDispatcher("/WEB-INF/movie/movieMain.jsp").forward(req, resp);
                break;
            case "list" : // 리스트를 들고오는 코드
                movieService.listMovie(req);
                movieService.listOtt(req);
                req.getRequestDispatcher("/WEB-INF/movie/movieList.jsp").forward(req, resp);
                break;
            case "view":
                movieService.getMovie(req);
                try {
                    reviewService.getReviews(req);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                req.getRequestDispatcher("/WEB-INF/movie/movieView.jsp").forward(req, resp);
                break;
            case "zzimAdd":
                try {
                    JSONObject jsonObject = new JSONObject(); // json 정보를 담기 위해 객체 생성
                    // 성공, 실패의 결과를 json에 저장
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
            case "zzimRemove":
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
                log.info(1); // 된다
                try {
                    log.info(5); //된다
                    JSONObject jsonObject = new JSONObject();
                    log.info(6); // 된다.
                    boolean result = movieService.removeMovieLike(req); // 오류 발생 지점
                    jsonObject.put("result", result ? "true" : "false");
                    log.info(3);
                    resp.getWriter().println(jsonObject.toJSONString());
                } catch (Exception e) {
                    throw new RuntimeException(e);

                }
                resp.sendRedirect("/list.mypage");
                break;
        }

    }
}
