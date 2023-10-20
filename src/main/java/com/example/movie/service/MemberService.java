package com.example.movie.service;

import com.example.movie.model.dao.MemberDAO;
import com.example.movie.model.dto.MemberDTO;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;

@Log4j2
public class MemberService {

    private final MemberDAO memberDAO;

    public MemberService(){
        memberDAO = new MemberDAO();
    }

    // 로그인
    public MemberDTO login(HttpServletRequest req) throws Exception {
        String memberId =  req.getParameter("memberId");
        String passwd =  req.getParameter("passwd");
        MemberDTO memberDTO = memberDAO.getWithPassword(memberId, passwd);
        log.info("service : " + memberDTO);
        return memberDTO;
    }

    // 회원가입
    public void addMember(HttpServletRequest req) throws Exception {
        String memberId = req.getParameter("memberId");
        String passwd = req.getParameter("passwd");
        String name = req.getParameter("name");
        String nickName = req.getParameter("nickName");

        MemberDTO memberDTO = MemberDTO.builder()
                .memberId(memberId)
                .passwd(passwd)
                .name(name)
                .nickName(nickName)
                .build();

        memberDAO.addMember(memberDTO);
    }

    // 회원정보 가져오기
    public void getWithMemberId(HttpServletRequest req) throws Exception {

        MemberDTO memberDTO = memberDAO.getWithMemberId((String) req.getSession().getAttribute("sessionId"));
        req.setAttribute("memberDTO", memberDTO);
    }

    // 회원 수정
    public void modifyMember(HttpServletRequest req) throws Exception {
        String memberId = req.getParameter("memberId");
        String passwd = req.getParameter("passwd");
        String name = req.getParameter("name");
        String nickName = req.getParameter("nickName");

        MemberDTO memberDTO = MemberDTO.builder()
                .memberId(memberId)
                .passwd(passwd)
                .name(name)
                .nickName(nickName)
                .build();
        memberDAO.modifyMember(memberDTO);
    }

    // 회원 탈퇴
    public void removeMember(HttpServletRequest req) throws Exception {
        String memberId = req.getParameter("memberId");
        memberDAO.removeMember(memberId);
    }

    // 아이디 중복 검사
    public boolean idCheck(HttpServletRequest req) throws Exception {
        String memberId = req.getParameter("memberId");
        return memberDAO.idCheck(memberId);
    }

    // 이름 중복 검사
    public boolean nameCheck(HttpServletRequest req) throws Exception {
        String name = req.getParameter("name");
        log.info("name : " + name);
        return memberDAO.nameCheck(name);
    }

    // 닉네임 중복 검사
    public boolean nickCheck(HttpServletRequest req) throws Exception {
        String nickName = req.getParameter("nickName");
        return memberDAO.nickCheck(nickName);
    }
}
