package com.example.movie.service;

import com.example.movie.model.dao.MemberDAO;
import com.example.movie.model.dto.MemberDTO;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;

@Log4j2
public enum MemberService {
    INSTANCE;

    private MemberDAO memberDAO;

    MemberService(){
        memberDAO = new MemberDAO();
    }

    public MemberDTO login(HttpServletRequest req) throws Exception {
        String memberId =  req.getParameter("memberId");
        String passwd =  req.getParameter("passwd");
        MemberDTO memberDTO = memberDAO.getWithPassword(memberId, passwd);
        log.info("service : " + memberDTO);
        return memberDTO;
    }

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

    public MemberDTO getWithMemberId(String memberId) throws Exception {
        MemberDTO memberDTO = memberDAO.getWithMemberId(memberId);
        return memberDTO;
    }

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

    public void removeMember(HttpServletRequest req) throws Exception {
        String memberId = req.getParameter("memberId");
        memberDAO.removeMember(memberId);
    }
}
