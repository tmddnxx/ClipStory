package com.example.movie.model.dao;

import com.example.movie.model.dto.AdminDTO;
import com.example.movie.model.dto.MemberDTO;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class AdminDAO {
    //    addCrew // 제작진 제작 - 은석
//    addCast // 출연정보 제작 - 은석
//    addPhoto 제작 - 은석
//    addMovie 수정 - 은석


//    (super)login 복붙 - 종원
    public AdminDTO getSuperPw(String superId, String superPw) throws Exception { // 관리자 비밀번호 얻기
        String sql = "select * FROM `super_account` WHERE `superId` = ? and `superPw` = ?";

        log.info("superId : " + superId);
        log.info("superPw : " + superPw);

        AdminDTO adminDTO = null;

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,superId);
        preparedStatement.setString(2,superPw);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()){
            log.info("resultSet : " + resultSet.getString("superId"));

            adminDTO = adminDTO.builder()
                    .superId(resultSet.getString("superId"))
                    .superPw(resultSet.getString("superPw"))
                    .superName(resultSet.getString("superName"))
                    .build();
        }
        return adminDTO;
    }

    public AdminDTO getSuperId(String superId) throws SQLException { // 관리자 ID 얻기
        String sql = "select * FROM `super_account` WHERE `superId` = ?";

        AdminDTO adminDTO = null;

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,superId);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()){
            adminDTO = adminDTO.builder()
                    .superId(resultSet.getString("superId"))
                    .superPw(resultSet.getString("superPw"))
                    .superName(resultSet.getString("superName"))
                    .build();
        }
        return adminDTO;
    }


    public boolean superIdCheck(String superId) throws Exception {
        String sql = "SELECT * FROM `super_account` where superId = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,superId);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next())
            return true;
        else
            return false;

    }

    public boolean superNameCheck(String superName) throws Exception{
        String sql = "SELECT * FROM `super_account` where superName = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,superName);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next())
            return true;
        else
            return false;
    }
    public boolean superPwCheck(String superPw) throws Exception{
        String sql = "SELECT * FROM `super_account` where superPw = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,superPw);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next())
            return true;
        else
            return false;
    }

//    memberList 제작 - 종원
public List<MemberDTO> viewMyMember(String memberId) throws Exception { // 회원 작성글 보기
    String sql = "select * FROM `member` WHERE `memberId` = ?";

    List<MemberDTO> memberList = new ArrayList<>();

    @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
    @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setString(1,memberId);
    @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

    while(resultSet.next()){
        MemberDTO memberDTO = com.example.movie.model.dto.MemberDTO.builder()
                .memberId(resultSet.getString("memberId"))
                .name(resultSet.getString("name"))
                .nickName(resultSet.getString("nickName"))
                .joinDate(String.valueOf(resultSet.getTimestamp("joinDate")))
                .memberId(resultSet.getString("memberId"))
                .build();
        memberList.add(memberDTO);
    }
    return memberList;
}



//    boardList 복붙 - 승우
//    addBoard 복봍 - 승우
//    boardView 복붙 - 승우
//    removeBoard 복붙 - 승우
//    commentList 복붙 - 승우
//    removeComment 복붙 - 승우


//    movieList 수정 - 수홍
//    removeMovie 복붙  - 수홍
//    modifyMovie 복붙 -수홍
//    movieView 복붙 - 수홍
//    reviewList 복붙 - 수홍
//    removeReview 복붙 - 수홍
}
