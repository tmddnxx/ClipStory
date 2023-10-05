package com.example.movie.service;

import com.example.movie.model.dao.MovieDAO;
import com.example.movie.model.dao.ReviewDAO;
import com.example.movie.model.dto.MemberDTO;
import com.example.movie.model.dto.ReviewDTO;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

@Log4j2
public class ReviewService {
    private static ReviewService instance;
    private final MovieDAO movieDAO;
    private ReviewService() {
        movieDAO = new MovieDAO();
    }

    public static ReviewService getInstance() {
        if (instance == null) {
            instance = new ReviewService();
        }
        return instance;
    }

    public boolean addReview(HttpServletRequest request) throws Exception {
        log.info("addReview() ...");
        ReviewDAO reviewDAO = ReviewDAO.getInstance();
        log.info((String) request.getSession().getAttribute("memberId"));
        ReviewDTO reviewDTO = ReviewDTO.builder()
                .movieNo(Integer.valueOf(request.getParameter("num")))
//                .memberId((String) request.getSession().getAttribute("memberId"))
                // 컨트롤러에서 login할때 세션값을 불러오면 위의 코드를 쓰고 아니면 밑의 코드를 적용
                .memberId(((MemberDTO) request.getSession().getAttribute("loginInfo")).getMemberId())
//                .nickName(request.getParameter("nickName"))
                .nickName((String) request.getSession().getAttribute("nickName"))
                .review(request.getParameter("review"))
                .score(request.getParameter("score"))
                .build();
        log.info(reviewDTO.getMemberId());
        boolean result = reviewDAO.insertReview(reviewDTO);
        int movieNo = reviewDTO.getMovieNo();
        float avgScore = reviewDAO.avgScore(Integer.valueOf(request.getParameter("num")));
        movieDAO.updateAvgScore(movieNo, avgScore);
        return result;
//        return reviewDAO.insertReview(reviewDTO);
    }


    public List<ReviewDTO> getReviews(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        log.info("getReview() ...");
        ReviewDAO reviewDAO = ReviewDAO.getInstance();
        int num = Integer.parseInt(request.getParameter("num"));

        List<ReviewDTO> reviewDTOS = reviewDAO.selectReviews(num);

        // 댓글 중 로그인한 사용자가 작성한 댓글이면 isLogin 값을 true로 변경
        for (ReviewDTO reviewDTO : reviewDTOS) {
            log.info(reviewDTO.getMemberId());
            log.info(request.getSession().getAttribute("memberId"));
            // 댓글 작성자와 로그인한 사용자가 같은 경우.
            if (reviewDTO.getMemberId().equals(request.getSession().getAttribute("sessionId"))) {
                reviewDTO.setLogin(true);
            }
        }

        return reviewDTOS;
    }


    public boolean removeReview(HttpServletRequest request) throws Exception {
        log.info("removeReview()...");
        ReviewDAO reviewDAO = ReviewDAO.getInstance();
        int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
        ReviewDTO reviewDTO = reviewDAO.selectReviewOne(reviewNo);
        log.info(reviewDTO);
        boolean result = reviewDAO.deleteReview(reviewNo);
        log.info("--------result : "+result);
        int movieNo = reviewDTO.getMovieNo();
        float avgScore = reviewDAO.avgScore(movieNo);
        movieDAO.updateAvgScore(movieNo, avgScore);
        return result;
    }
}
