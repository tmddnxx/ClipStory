package com.example.movie.model.dao;

import com.example.movie.model.dto.*;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class MyPageDAO {

    private MemberDAO MemberDTO;


    public MemberDAO viewProfile(String memberId) throws Exception { // 프로필 보기
        String sql = "select * FROM `member` WHERE `memberId` = ?";

        MemberDTO memberDTO = null;

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,memberId);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()){
            memberDTO = memberDTO.builder()
                    .memberId(resultSet.getString("memberId"))
                    .name(resultSet.getString("name"))
                    .nickName(resultSet.getString("nickName"))
                    .zzimCnt(resultSet.getInt("zzim"))
                    .joinDate(String.valueOf(resultSet.getTimestamp("joinDate")))
                    .build();
        }
        return MemberDTO;
    }
    public List<BoardDTO> viewMyContent(String memberId) throws Exception { // 내 작성글 
        String sql = "select * FROM `board` WHERE `memberId` = ?";

        List<BoardDTO> boardList = new ArrayList<>();

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,memberId);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            BoardDTO boardDTO = com.example.movie.model.dto.BoardDTO.builder()
                    .contentNo(resultSet.getInt("contentNo"))
                    .title(resultSet.getString("title"))
                    .content(resultSet.getString("content"))
                    .nickName(resultSet.getString("nickName"))
                    .addDate(resultSet.getTimestamp("addDate"))
                    .hit(resultSet.getInt("hit"))
                    .memberId(resultSet.getString("memberId"))
                    .build();
            boardList.add(boardDTO);
        }
        return boardList;
    }



    public List<CommentDTO> viewMyComment(String memberId) throws Exception { // 내 댓글
        String sql = "select * FROM `comment` WHERE `memberId` = ?";

        List<CommentDTO> commentList = new ArrayList<>();

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,memberId);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            CommentDTO commentDTO = CommentDTO.builder()
                    .commentNo(resultSet.getInt("commentNo"))
                    .nickName(resultSet.getString("nickName"))
                    .comment(resultSet.getString("comment"))
                    .addDate(String.valueOf(resultSet.getTimestamp("addDate")))
                    .memberId(resultSet.getString("memberId"))
                    .contentNo(resultSet.getInt("contentNo"))
                    .build();
            commentList.add(commentDTO);
        }
        return commentList;
    }

    public List<ReviewDTO> viewMyReview(String memberId) throws Exception { // 내 리뷰
        String sql = "select * FROM `review` WHERE `memberId` = ?";

        List<ReviewDTO> reviewList = new ArrayList<>();

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,memberId);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            ReviewDTO reviewDTO = ReviewDTO.builder()
                    .reviewNo(resultSet.getInt("reviewNo"))
                    .movieNo(resultSet.getInt("movieNo"))
                    .nickName(resultSet.getString("nickName"))
                    .addDate(String.valueOf(resultSet.getTimestamp("addDate")))
                    .review(resultSet.getString("review"))
                    .score(String.valueOf(resultSet.getInt("score")))
                    .build();
            reviewList.add(reviewDTO);
        }
        return reviewList;
    }
    public List<MovieDTO> viewMyZZim(String memberId) throws Exception { // 내 찜
        String sql = "select m.* from zzim z join movie m on m.movieNo = z.movieNo where z.memberId = ?";
        List<MovieDTO> myZZimList = new ArrayList<>();

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,memberId);

        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            MovieDTO movieDTO = MovieDTO.builder()
                    .movieNo(resultSet.getInt("movieNo"))
                    .movieName(resultSet.getString("movieName"))
                    .director(resultSet.getString("director"))
                    .actor(resultSet.getString("actor"))
                    .releaseDate(resultSet.getString("releaseDate"))
                    .region(resultSet.getString("region"))
                    .genre(resultSet.getString("genre"))
                    .audience(resultSet.getInt("audience"))
                    .ranking(resultSet.getInt("ranking"))
                    .runningtime(resultSet.getString("runningtime"))
                    .outline(resultSet.getString("outline"))
                    .poster(resultSet.getString("poster"))
                    .mo(resultSet.getString("mo"))
                    .avgScore(resultSet.getFloat("avgScore"))
                    .build();
            myZZimList.add(movieDTO);
        }
        log.info("마이리스트" + myZZimList);
        return myZZimList;
    }
}
