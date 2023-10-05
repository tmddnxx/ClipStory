package com.example.movie.model.dao;

import com.example.movie.model.dto.BoardDTO;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Log4j2
public class BoardDAO {
    public void insertBoard(BoardDTO boardDTO) throws Exception {
        /* 게시물 추가 */
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        String sql = "insert into board (title, content, nickName, addDate, memberId) values(?, ?, ?, now(), ?)";
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, boardDTO.getTitle());
        preparedStatement.setString(2, boardDTO.getContent());
        preparedStatement.setString(3, boardDTO.getNickName());
        preparedStatement.setString(4, boardDTO.getMemberId());
        preparedStatement.executeUpdate();
    }

    public int getListCount(String items, String text) throws Exception {
        /*board 테이블의 전체 레코드 갯수 반환 */

        int cnt = 0;

        String sql;
        if(items == null || text == null) { // 검색어가 없는 경우
            sql = "select count(*) from board";
        }
        else {
            sql = "select count(*) from board where " + items + " like '%" + text + "%'";
        }
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()) { // 데이터가 있는 경우
            cnt = resultSet.getInt(1);
        }
        return cnt;
    }

    public List<BoardDTO> selectAll(int page, int limit, String items, String text) throws Exception{
        /* 게시물 전체 가져오기 */
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();

        int start = (page - 1) * limit;
        String sql;

        if(items == null || text == null) {
            sql = "select * from board order by contentNo desc";
        }
        else {
            sql = "select * from board where " + items + " like '%" + text + "%' order by contentNo desc";
        }
        sql += " limit " + start + ", " + limit;

        List<BoardDTO> boardList = new ArrayList<>();

        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            BoardDTO boardDTO = new BoardDTO();
            boardDTO.setContentNo(resultSet.getInt("contentNo"));
            boardDTO.setTitle(resultSet.getString("title"));
            boardDTO.setContent(resultSet.getString("content"));
            boardDTO.setNickName(resultSet.getString("nickName"));
            boardDTO.setAddDate(resultSet.getTimestamp("addDate"));
            boardDTO.setHit(resultSet.getInt("hit"));
            boardDTO.setMemberId(resultSet.getString("memberId"));
            boardDTO.setCnt(resultSet.getInt("cnt"));
            boardList.add(boardDTO);
        }
        return boardList;
    }

    public BoardDTO selectOne(int contentNo) throws Exception {
        // 개별 선택
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        BoardDTO boardDTO = null;
        String sql = "select * from board where contentNo = ?";
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, contentNo);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()){
            boardDTO = new BoardDTO();
            boardDTO.setContentNo(resultSet.getInt("contentNo"));
            boardDTO.setTitle(resultSet.getString("title"));
            boardDTO.setContent(resultSet.getString("content"));
            boardDTO.setNickName(resultSet.getString("nickName"));
            boardDTO.setAddDate(resultSet.getTimestamp("addDate"));
            boardDTO.setHit(resultSet.getInt("hit"));
            boardDTO.setMemberId(resultSet.getString("memberId"));
            boardDTO.setCnt(resultSet.getInt("cnt"));
        }
        return boardDTO;
    }

    public void deleteBoard(int contentNo) throws Exception {
        // 게시물 삭제
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        String sql = "delete from board where contentNo = ?";
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, contentNo);

        if(preparedStatement.executeUpdate() == 0) { // 삭제할 게시물이 없는 경우
            throw new SQLException("삭제할 게시물이 없습니다");
        }
    }
    public void modifyBoard(BoardDTO boardDTO) throws Exception {
        // 게시물 수정
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        String sql = "update board set title =?, content =? where contentNo = ?";
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, boardDTO.getTitle());
        preparedStatement.setString(2, boardDTO.getContent());
        preparedStatement.setInt(3, boardDTO.getContentNo());
        preparedStatement.executeUpdate();
    }
    public void plusHit(int contentNo) throws Exception{
        // 조회수 증가
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        String sql = "update board set hit = board.hit + 1 where contentNo = ?";
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, contentNo);
        preparedStatement.executeUpdate();
    }

    public void commentCount(int contentNo) throws Exception{
        // 댓글 수 증가
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        String sql = "update board set cnt = board.cnt + 1 where contentNo = ?";
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, contentNo);
        preparedStatement.executeUpdate();

    }
}
