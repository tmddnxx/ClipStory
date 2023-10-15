package com.example.movie.service;

import com.example.movie.model.dao.AdminDAO;
import com.example.movie.model.dto.AdminDTO;
import com.example.movie.model.dto.MemberDTO;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Log4j2
public enum AdminService {
    INSTANCE;
    private AdminDAO adminDAO;

    AdminService(){
        adminDAO = new AdminDAO();
    }

//    addCrew // 제작진 제작 - 은석
//    addCast // 출연정보 제작 - 은석
//    addPhoto 제작 - 은석
//    addMovie 수정 - 은석


//    (super)login 복붙 - 종원 // 새로운 관리자 전용 session 생성

public AdminDTO adminLogin(HttpServletRequest req) throws Exception { // 관리자 로그인 확인
    String superId =  req.getParameter("superId");
    String superPw =  req.getParameter("superPw");
    AdminDTO adminDTO = adminDAO.getSuperPw(superId, superPw);
    log.info("adminService : " + adminDTO);
    return adminDTO;
}

//    memberList 제작 - 종원
public List<MemberDTO> getMemberList(HttpServletRequest request) throws Exception { //  가입 회원 목록 보기
    List<MemberDTO> memberDTOList = adminDAO.viewMyMember((String)request.getSession().getAttribute("superId"));
    request.setAttribute("memberDTOList", memberDTOList);

    log.info(memberDTOList);
    return memberDTOList;
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
