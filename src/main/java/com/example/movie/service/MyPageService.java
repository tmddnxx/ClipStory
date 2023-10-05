package com.example.movie.service;

import com.example.movie.model.dao.MemberDAO;
import com.example.movie.model.dto.BoardDTO;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;

@Log4j2
public enum MyPageService {
    INSTANCE, BoardDTO;

    private MemberDAO memberDAO;

    MyPageService(){
        memberDAO = new MemberDAO();
    }


    public void getMyBoard(HttpServletRequest request){ // 게시물 개별
        int contentNo = Integer.parseInt(request.getParameter("contentNo"));
        if (getMemberId().equals(memberId))
            try{
                BoardDTO boardDTO = boardDAO.selectOne(contentNo);
                request.setAttribute("boardDTO", boardDTO);
            } catch (Exception e){
                log.error(e.getMessage());
                log.info("게시물 가져오는 과정에서 에러");
                request.setAttribute("error", "게시물을 정상적으로 가져오지 못함");
            }
    }

    private Object getMemberId() {
    }
}
