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
    private final MemberDTO memberDTO;
    private ReviewService() {
        movieDAO = new MovieDAO();
        memberDTO = new MemberDTO();
    }

    public static ReviewService getInstance() {
        if (instance == null) {
            instance = new ReviewService();
        }
        return instance;
    }

    // 리뷰 추가 메소드
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
                .nickName(((MemberDTO) request.getSession().getAttribute("loginInfo")).getNickName())
                .review(request.getParameter("review"))
                .score(request.getParameter("score"))
                .build();
        log.info(reviewDTO.getMemberId());

        // 리뷰 등록할 때 평점 평균값 계산해서 movie테이블로 넣어주는 코드
        boolean result = reviewDAO.insertReview(reviewDTO); // 위에서 받은 reviewDTO 값이 review테이블에 있는지 확인하기 위한 boolean형 변수 선언
        int movieNo = reviewDTO.getMovieNo(); // 리뷰를 입력한 영화의 PK를 가져와서 담아준다.
        float avgScore = reviewDAO.avgScore(Integer.valueOf(request.getParameter("num"))); // 리뷰테이블에 해당 movieNo의 모든 평점의 평균을 계산해서 변수에 넣어준다.
        movieDAO.updateAvgScore(movieNo, avgScore); // 해당영화의 movieNo와 평균낸 평점을 movie테이블에 넣어준다.
        return result; // 리뷰작성 여부 확인 결과를 반환한다.
//        return reviewDAO.insertReview(reviewDTO);
    }


    // 전체리뷰목록 불러와서 request로 전해주는 메소드
    public List<ReviewDTO> getReviews(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        log.info("getReviews() ...");
        ReviewDAO reviewDAO = ReviewDAO.getInstance();
        int num;
        // movieNo 파라미터가 하나로 통일되어 있지 않아서 movieNo와 num 모두 확인해서 movieNo로 인식하기 위한 if문
        if(request.getParameter("movieNo")!=null){
            num = Integer.parseInt(request.getParameter("movieNo"));
        }
        else {
            num = Integer.parseInt(request.getParameter("num"));
        }
        List<ReviewDTO> reviewDTOS = reviewDAO.selectReviews(num);

        // 댓글 중 로그인한 사용자가 작성한 댓글이면 isLogin 값을 true로 변경
        for (ReviewDTO reviewDTO : reviewDTOS) {
            log.info(reviewDTO.getMemberId());
            log.info(request.getSession().getAttribute("sessionId"));
            // 댓글 작성자와 로그인한 사용자가 같은 경우.
            if (reviewDTO.getMemberId().equals(request.getSession().getAttribute("sessionId"))) {
                reviewDTO.setLogin(true);
            }
        }
        boolean isWrite = reviewDAO.isWrite((String) request.getSession().getAttribute("sessionId"),num);
        log.info("--------------------------------"+isWrite);
        request.setAttribute("isWrite",isWrite);
        return reviewDTOS;
    }


    // 리뷰 삭제 메소드
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
        // 리뷰 삭제할 때 무비테이블에 리뷰평점도 업데이트해준다.
        movieDAO.updateAvgScore(movieNo, avgScore);
        return result;
    }
    
    // 마이 페이지 리뷰 선택삭제 메서드
    public boolean removeMyReviews(HttpServletRequest request) throws Exception {
        log.info("removeMyReviews()...");
        ReviewDAO reviewDAO = ReviewDAO.getInstance();

        String[] reviewNumbers = request.getParameterValues("reviewNo"); // 리뷰 번호의 배열을 얻습니다

        for (String reviewNumber : reviewNumbers) {
            int reviewNo = Integer.parseInt(reviewNumber);
            ReviewDTO reviewDTO = reviewDAO.selectReviewOne(reviewNo);
            log.info(reviewDTO);
            boolean result = reviewDAO.deleteReview(reviewNo);
            log.info("--------resultMy : " + result);

            int movieNo = reviewDTO.getMovieNo();
            float avgScore = reviewDAO.avgScore(movieNo);

            // 리뷰 삭제할 때 무비테이블에 리뷰평점도 업데이트해줍니다.
            movieDAO.updateAvgScore(movieNo, avgScore);
        }

        return true; // 필요에 따라 반환 값을 수정할 수 있습니다.
    }

}
