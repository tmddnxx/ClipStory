package com.example.movie.model.dao;

import com.example.movie.model.dto.MemberDTO;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Log4j2
public class MemberDAO {


    public MemberDTO getWithPassword(String memberId, String passwd) throws Exception {
        String sql = "select * FROM `member` WHERE `memberId` = ? and `passwd` = ?";

        log.info("memberId : " + memberId);
        log.info("pwd : " + passwd);

        MemberDTO memberDTO = null;

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,memberId);
        preparedStatement.setString(2,passwd);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()){
            log.info("resultSet : " + resultSet.getString("memberId"));

            memberDTO = memberDTO.builder()
                    .memberId(resultSet.getString("memberId"))
                    .passwd(resultSet.getString("passwd"))
                    .name(resultSet.getString("name"))
                    .nickName(resultSet.getString("nickName"))
                    .joinDate(resultSet.getString("joinDate"))
                    .build();
        }
        return memberDTO;
    }

    public MemberDTO getWithMemberId(String memberId) throws SQLException {
        String sql = "select * FROM `member` WHERE `memberId` = ?";

        MemberDTO memberDTO = null;

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,memberId);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()){
            memberDTO = memberDTO.builder()
                    .memberId(resultSet.getString("memberId"))
                    .passwd(resultSet.getString("passwd"))
                    .name(resultSet.getString("name"))
                    .nickName(resultSet.getString("nickName"))
                    .joinDate(resultSet.getString("joinDate"))
                    .build();
        }
        return memberDTO;
    }

    public void addMember(MemberDTO memberDTO) throws Exception { // 회원가입
        String sql = "insert into `member` (memberId, passwd, name, nickName) values (?, ?, ?, ?)";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1,memberDTO.getMemberId());
        preparedStatement.setString(2,memberDTO.getPasswd());
        preparedStatement.setString(3,memberDTO.getName());
        preparedStatement.setString(4,memberDTO.getNickName());
        preparedStatement.executeUpdate();
    }

    public void modifyMember(MemberDTO memberDTO) throws Exception {
        String sql = "update `member` set passwd = ?, name = ?, nickName = ? WHERE memberId = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1,memberDTO.getPasswd());
        preparedStatement.setString(2,memberDTO.getName());
        preparedStatement.setString(3,memberDTO.getNickName());
        preparedStatement.setString(4,memberDTO.getMemberId());
        preparedStatement.executeUpdate();

    }
    public void removeMember(String memberId) throws Exception {
        String sql = "delete from `member` where memberId = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1,memberId);
        preparedStatement.executeUpdate();

    }
}
