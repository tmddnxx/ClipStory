package com.example.movie.model.dao;

import com.example.movie.model.dto.BoardDTO;
import com.example.movie.model.dto.CommentDTO;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class CommentDAO {

    public boolean insertComment(CommentDTO commentDTO) throws Exception {
        /* 댓글달기 */
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        String sql = "insert into `comment` (nickName, comment, addDate, memberId, contentNo) values(?, ?, now(), ?, ?)";
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, commentDTO.getNickName());
        preparedStatement.setString(2, commentDTO.getComment());
        preparedStatement.setString(3, commentDTO.getMemberId());
        preparedStatement.setInt(4, commentDTO.getContentNo());
        return preparedStatement.executeUpdate() == 1;
    }

    public boolean insertCommentRe(CommentDTO commentDTO) throws Exception {
        /* 대댓글달기 */
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        String sql = "insert into `comment` (nickName, comment, addDate, memberId, contentNo, parentNo) values(?, ?, now(), ?, ?, ?)";
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, commentDTO.getNickName());
        preparedStatement.setString(2, commentDTO.getComment());
        preparedStatement.setString(3, commentDTO.getMemberId());
        preparedStatement.setInt(4, commentDTO.getContentNo());
        preparedStatement.setInt(5, commentDTO.getParentNo());
        return preparedStatement.executeUpdate() == 1;
    }

    // 댓글이 있는 게시글 번호 얻기
    public int getContentNoByComment(int commentNo) throws Exception {
        String sql = "SELECT `contentNo` from `comment` where `commentNo` = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, commentNo);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        log.info("-----------------commentNo : " + commentNo);
        return resultSet.getInt("contentNo");
    }

    // 댓글의 부모번호 얻기
    public int getParentNoByComment(int commentNo) throws Exception{
        String sql = "SELECT `parentNo` from `comment` where `commentNo` = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, commentNo);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt("parentNo");
    }
    public List<CommentDTO> selectComments(int contentNo) throws SQLException, ClassNotFoundException{
        log.info("selectComments()...");

        List<CommentDTO> commentDTOS = new ArrayList<>();
        String sql = "SELECT * FROM `comment` WHERE contentNo = ? ORDER BY `parentNo`, `commentNo`";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, contentNo);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            CommentDTO commentDTO = CommentDTO.builder()
                    .commentNo(resultSet.getInt("commentNo"))
                    .memberId(resultSet.getString("memberId"))
                    .nickName(resultSet.getString("nickName"))
                    .comment(resultSet.getString("comment"))
                    .addDate(resultSet.getString("addDate"))
                    .parentNo(resultSet.getInt("parentNo"))
                    .build();
            commentDTOS.add(commentDTO);
        }
        return commentDTOS;

    }

    public boolean deleteComment(int commentNo) throws SQLException, ClassNotFoundException {
        log.info("deleteComment()..");
        String sql = "DELETE FROM `comment` WHERE `commentNo` = ? ";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, commentNo);

        return preparedStatement.executeUpdate() == 1;
    }

    public void updateParentNo() throws SQLException, ClassNotFoundException {
        log.info("updateParentNo()..");
        String sql = "UPDATE `comment` SET `parentNo` = `commentNo` WHERE `parentNo` = 0";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.executeUpdate();
    }

    public int commentCnt(int contentNo) throws SQLException, ClassNotFoundException {
        String sql = "SELECT count(*) as cnt from comment where contentNo = ? and memberId != ''";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, contentNo);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt("cnt");
    }

    public boolean checkHasRe(int parentNo) throws SQLException{
        String sql = "SELECT count(*) as cnt from comment where parentNo = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, parentNo);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int cnt = resultSet.getInt("cnt");
        if(cnt != 1)
            return true;
        return false;
    }


    public boolean updateCommentDie(int commentNo) throws SQLException{
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        String sql = "UPDATE `comment` set nickName = ?, comment = ?, memberId = ? where commentNo = ?";
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "");
        preparedStatement.setString(2, "삭제된 댓글입니다");
        preparedStatement.setString(3, "");
        preparedStatement.setInt(4, commentNo);

        return preparedStatement.executeUpdate() == 1;
    }

    public boolean adminUpdateCommentDie(int commentNo) throws SQLException{
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        String sql = "UPDATE `comment` set nickName = ?, comment = ?, memberId = ? where commentNo = ?";
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "");
        preparedStatement.setString(2, "관리자에 의해 삭제된 댓글입니다");
        preparedStatement.setString(3, "");
        preparedStatement.setInt(4, commentNo);

        return preparedStatement.executeUpdate() == 1;
    }


}
