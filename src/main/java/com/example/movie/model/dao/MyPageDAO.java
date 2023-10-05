package com.example.movie.model.dao;

import com.example.movie.model.dto.BoardDTO;
import com.example.movie.model.dto.CommentDTO;
import com.example.movie.model.dto.MemberDTO;
import lombok.Cleanup;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MyPageDAO {

    private MemberDAO MemberDTO;
    private CommentDAO commentDTO;
    private List<com.example.movie.model.dto.BoardDTO> BoardDTO;

    public MemberDAO viewProfile(HttpServletRequest req, String memberId) throws Exception {
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
                    .zzim(resultSet.getString("zzim"))
                    .joinDate(resultSet.getString("joinDate"))
                    .build();
        }
        return MemberDTO;
    }
    public List<BoardDTO> viewMyContent(String memberId) throws Exception {
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



    public List<CommentDTO> viewMyComment(String memberId) throws Exception {
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

}
