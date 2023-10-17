package com.example.movie.controller;

import com.example.movie.model.dto.ReviewDTO;
import com.example.movie.service.ReviewService;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@WebServlet("/review/*")
public class ReviewController extends HttpServlet {
    ReviewService reviewService = ReviewService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String RequestURI = req.getRequestURI();
        String contextPath = req.getContextPath();
        String command = RequestURI.substring(contextPath.length());
        resp.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        log.info("command : " + command);

        switch (command) {
            case "/review/add":
                try {
                    JSONObject jsonObject = new JSONObject(); // json 정보를 담기 위해 객체 생성
                    // 성공, 실패의 결과를 json에 저장
                    if (reviewService.addReview(req)) {
                        //
                        jsonObject.put("result", "true");
                    } else {
                        jsonObject.put("result", "false");
                    }
                    resp.getWriter().println(jsonObject.toJSONString());
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "/review/get":
                try {
                    List<ReviewDTO> reviewDTOS = reviewService.getReviews(req);
                    // collection List를 json으로 변환.
                    JSONArray jsonArray = new JSONArray(); // 목록을 저장해야 되서 JSONArray 사용.
                    for (ReviewDTO reviewDTO : reviewDTOS) {
                        log.info("리뷰 : " + reviewDTO);
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("reviewNo", reviewDTO.getReviewNo());
                        jsonObject.put("nickName", reviewDTO.getNickName());
                        jsonObject.put("review", reviewDTO.getReview());
                        jsonObject.put("addDate", reviewDTO.getAddDate());
                        jsonObject.put("score", reviewDTO.getScore());
                        jsonObject.put("memberId", reviewDTO.getMemberId());
                        jsonObject.put("isLogin", reviewDTO.isLogin());
                        jsonArray.add(jsonObject);
                    }
                    resp.getWriter().print(jsonArray.toJSONString());
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "/review/remove":
                try {
                    JSONObject jsonObject = new JSONObject();
                    if (reviewService.removeReview(req)) {
                        jsonObject.put("result", "true");
                    } else {
                        jsonObject.put("result", "false");
                    }

                    resp.getWriter().println(jsonObject.toJSONString());
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;

            case "/review/myRemove":
                try {
                    // 댓글을 삭제하고 삭제된 댓글 목록을 얻기 위해 removeMyComment 메서드를 호출합니다
                    reviewService.removeMyReviews(req);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                resp.sendRedirect("/list.mypage");
                break;






        }

    }


}
