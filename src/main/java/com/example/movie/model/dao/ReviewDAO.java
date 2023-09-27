package com.example.movie.model.dao;

import com.example.movie.model.dto.ReviewDTO;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class ReviewDAO {


    public void insert(ReviewDTO reviewDTO) throws Exception {
        String sql = "INSERT INTO review (nickName, addDate, review, score, movieName, memberId) " +
                "VALUES (?, now(), ?, ?, ?, ?)";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, reviewDTO.getNickName());
        preparedStatement.setString(2, reviewDTO.getReview());
        preparedStatement.setInt(3, reviewDTO.getScore());
        preparedStatement.setString(4, reviewDTO.getMovieName());
        preparedStatement.setString(5, reviewDTO.getMemberId());
        preparedStatement.executeUpdate();
    }

    public List<ReviewDTO> selectAll() throws Exception { // 목록을 저장할 컬렉션 객체
        String sql = "SELECT * FROM review";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        List<ReviewDTO> list = new ArrayList<>();
        while(resultSet.next()) {
            ReviewDTO reviewDTO = ReviewDTO.builder()
                    .reviewNo(resultSet.getInt("reviewNo"))
                    .nickName(resultSet.getString("nickName"))
                    .addDate(resultSet.getString("addDate"))
                    .review(resultSet.getString("review"))
                    .score(resultSet.getInt("score"))
                    .movieName(resultSet.getString("movieName"))
                    .memberId(resultSet.getString("memberId"))
                    .build();
            list.add(reviewDTO);
        }
        return list;
    }

    public void deleteOne(int reviewNo) throws Exception {
        // movieNo를 받아서 해당 번호의 데이터를 삭제
        String sql = "DELETE FROM review WHERE reviewNo = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, reviewNo);
        preparedStatement.executeUpdate();
    }
}
