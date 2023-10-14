package com.example.movie.model.dao;

import com.example.movie.model.dto.ReviewDTO;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.checkerframework.checker.units.qual.C;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class ReviewDAO {
    private static ReviewDAO instance;

    private ReviewDAO() {

    }

    public static ReviewDAO getInstance() {
        if (instance == null) {
            instance = new ReviewDAO();
        }
        return instance;
    }

    // 리뷰 추가하기 위한 메소드
    public boolean insertReview(ReviewDTO reviewDTO) throws SQLException, ClassNotFoundException {
        log.info("insertReview() ...");

        String sql = "INSERT INTO review VALUES (null, ?, ?, now(), ?, ?, ?)";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, reviewDTO.getMovieNo());
        preparedStatement.setString(2, reviewDTO.getNickName());
        preparedStatement.setString(3, reviewDTO.getReview());
        preparedStatement.setString(4, reviewDTO.getScore());
        preparedStatement.setString(5, reviewDTO.getMemberId());
        log.info(reviewDTO.getMemberId());
        return preparedStatement.executeUpdate() == 1;
    }


    // 리뷰 전체목록 출력하기 위한 메소드
    public List<ReviewDTO> selectReviews(int movieNo) throws SQLException, ClassNotFoundException {
        log.info("selectReview()...");

        List<ReviewDTO> reviewDTOS = new ArrayList<>();
        String sql = "SELECT * FROM `review` WHERE `movieNo` = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, movieNo);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            ReviewDTO reviewDTO = ReviewDTO.builder()
                    .reviewNo(resultSet.getInt("reviewNo"))
                    .memberId(resultSet.getString("memberId"))
                    .nickName(resultSet.getString("nickName"))
                    .review(resultSet.getString("review"))
                    .addDate(resultSet.getString("addDate"))
                    .score(resultSet.getString("score"))
                    .build();
            reviewDTOS.add(reviewDTO);
        }
        return reviewDTOS;
    }

    // 리뷰 삭제하기 위한 메소드
    public boolean deleteReview(int reviewNo) throws SQLException, ClassNotFoundException {
        log.info("deleteReview()...");
        String sql = "DELETE FROM `review` WHERE `reviewNo` = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, reviewNo);

        return preparedStatement.executeUpdate() == 1;
    }

    // 리뷰 한개 선택 하기 위한 메소드 (movie 테이블에 평점 넣어 주기 위해 작성한 메소드)
    public ReviewDTO selectReviewOne(int reviewNo) throws SQLException, ClassNotFoundException {
        log.info("selectReview()...");
        String sql = "select * from `review` where `reviewNo` = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, reviewNo);
        ResultSet resultSet = preparedStatement.executeQuery();
        ReviewDTO reviewDTO = new ReviewDTO();
        if(resultSet.next()) {
            reviewDTO = ReviewDTO.builder()
                    .movieNo(resultSet.getInt("movieNo"))
                    .reviewNo(resultSet.getInt("reviewNo"))
                    .memberId(resultSet.getString("memberId"))
                    .nickName(resultSet.getString("nickName"))
                    .review(resultSet.getString("review"))
                    .addDate(resultSet.getString("addDate"))
                    .score(resultSet.getString("score"))
                    .build();
        }
        return reviewDTO;
    }


    // 리뷰평점 평균내기 위한 메소드
    public float avgScore(int movieNo) throws SQLException, ClassNotFoundException {
        String sql = "SELECT (ROUND(AVG(score), 2)) as avgScore from review where movieNo = ?;";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, movieNo);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getFloat("avgScore");
    }

    // 세션에 등록된 아이디가 리뷰를 적었는 지 확인 하기 위한 메소드
    public boolean isWrite(String memberId, int movieNo) throws SQLException {
        String sql = "select * from review where memberId = ? and movieNo = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, memberId);
        preparedStatement.setInt(2, movieNo);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }
}
