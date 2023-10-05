package com.example.movie.service;

import com.example.movie.model.dao.BoardDAO;
import com.example.movie.model.dto.BoardDTO;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Log4j2
public class BoardService {
    static final int LISTCOUNT = 10; // 페이지당 게시물 숫자
    private final BoardDAO boardDAO;

    public BoardService(){ boardDAO = new BoardDAO();}

    public void addBoard(HttpServletRequest request) { //  게시물 추가
        BoardDTO boardDTO = new BoardDTO();

        boardDTO.setTitle(request.getParameter("title"));
        boardDTO.setContent(request.getParameter("content"));
        boardDTO.setMemberId(request.getParameter("memberId"));
        boardDTO.setNickName(request.getParameter("nickName"));

        log.info(boardDTO);

        try{
            boardDAO.insertBoard(boardDTO);
        } catch (Exception e){
            log.error(e.getMessage());
        }

    }

    public void listBoard(HttpServletRequest request) throws Exception { // 게시물 전체목록
       List<BoardDTO> boardDTOList;
       int pageNum = 1; // 페이지번호의 기본값
       int limit = LISTCOUNT; // 페이지당 게시물 수

       if(request.getParameter("pageNum") != null){
            pageNum = Integer.parseInt(request.getParameter("pageNum"));
       }
        String items = request.getParameter("items"); // 검색어
        String text = request.getParameter("text");

       int totalRecord = boardDAO.getListCount(items, text);
       boardDTOList = boardDAO.selectAll(pageNum, limit, items, text);

       int totalPage; //전체 페이지 계산
        if(totalRecord % limit == 0){
            totalPage = totalRecord /limit;
            Math.floor(totalPage);
        }
        else {
            totalPage = totalRecord /limit;
            Math.floor(totalPage);
            totalPage = totalPage + 1;
        }

        // 페이지 시작 번호 작업.
        int startNum = totalRecord - (pageNum -1 ) * limit;
       try {
           request.setAttribute("pageNum", pageNum);
           request.setAttribute("totalPage", totalPage);
           request.setAttribute("totalRecord", totalRecord);
           request.setAttribute("limit", limit);
           request.setAttribute("boardDTOList", boardDTOList);
           request.setAttribute("startNum", startNum);

       }catch (Exception e){
           log.error(e.getMessage());
           log.info("게시글 목록 생성과정에서 에러");
           request.setAttribute("error" , "게시글 목록이 정상적으로 생성되지 않았습니다");
       }
    }

    public void getBoard(HttpServletRequest request){ // 게시물 개별
        int contentNo = Integer.parseInt(request.getParameter("contentNo"));

        try{
            BoardDTO boardDTO = boardDAO.selectOne(contentNo);
            request.setAttribute("boardDTO", boardDTO);
        } catch (Exception e){
            log.error(e.getMessage());
            log.info("게시물 가져오는 과정에서 에러");
            request.setAttribute("error", "게시물을 정상적으로 가져오지 못함");
        }
    }

    public void removeBoard(HttpServletRequest request){ // 게시물 삭제
        int contentNo = Integer.parseInt(request.getParameter("contentNo"));

        try{
           boardDAO.deleteBoard(contentNo);
        } catch (Exception e){
            log.error(e.getMessage());
            log.info("게시물 삭제하는 과정에서 에러");
            request.setAttribute("error", "게시물을 정상적으로 삭제하지 못함");
        }
    }

    public void modifyBoard(HttpServletRequest request){ // 게시물 수정
        BoardDTO boardDTO = new BoardDTO();

        boardDTO.setContentNo(Integer.parseInt(request.getParameter("contentNo")));
        boardDTO.setTitle(request.getParameter("title"));
        boardDTO.setContent(request.getParameter("content"));
        boardDTO.setMemberId(request.getParameter("memberId"));
        boardDTO.setNickName(request.getParameter("nickName"));

        try {
            boardDAO.modifyBoard(boardDTO);
            log.info(boardDTO);
        }catch (Exception e){
            log.error(e.getMessage());
            request.setAttribute("error", "수정 오류");
        }
    }

    public void increaseHit(HttpServletRequest request){ // 조회수 증가
        int contentNo = Integer.parseInt(request.getParameter("contentNo"));

        try{
            boardDAO.plusHit(contentNo);
        }catch (Exception e){
            log.error(e.getMessage());
            request.setAttribute("error", "조회 증가 실패");
        }
    }


}
