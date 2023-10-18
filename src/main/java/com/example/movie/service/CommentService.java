package com.example.movie.service;

import com.example.movie.model.dao.BoardDAO;
import com.example.movie.model.dao.CommentDAO;
import com.example.movie.model.dto.CommentDTO;
import com.example.movie.model.dto.MemberDTO;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public enum CommentService {
    INSTANCE;
    private CommentDAO commentDAO;

    CommentService(){
        commentDAO = new CommentDAO();
    }

    public boolean addComment(HttpServletRequest req) throws Exception {
        log.info("addComment()..");
        int contentNo = Integer.parseInt(req.getParameter("contentNo"));
        MemberDTO memberDTO= (MemberDTO) req.getSession().getAttribute("loginInfo");
        CommentDTO commentDTO = CommentDTO.builder()
                .nickName(req.getParameter("nickName"))
                .comment(req.getParameter("comment"))
                .memberId(memberDTO.getMemberId())
                .contentNo(contentNo)
                .build();

        boolean result = commentDAO.insertComment(commentDTO);
        commentDAO.updateParentNo();

        int cnt = commentDAO.commentCnt(contentNo);
        BoardDAO boardDAO = new BoardDAO();
        try{
            boardDAO.updateCnt(contentNo, cnt);
        }catch (Exception e){
            log.error(e.getMessage());
        }

        return result;
    }

    public boolean addCommentRe(HttpServletRequest req) throws Exception {
        log.info("addCommentRe()..");
        int contentNo = Integer.parseInt(req.getParameter("contentNo"));
        MemberDTO memberDTO= (MemberDTO) req.getSession().getAttribute("loginInfo");
        CommentDTO commentDTO = CommentDTO.builder()
                .nickName(req.getParameter("nickName"))
                .comment(req.getParameter("comment"))
                .memberId(memberDTO.getMemberId())
                .contentNo(contentNo)
                .parentNo(Integer.parseInt(req.getParameter("parentNo")))
                .build();

        boolean result = commentDAO.insertCommentRe(commentDTO);
        int cnt = commentDAO.commentCnt(contentNo);
        BoardDAO boardDAO = new BoardDAO();
        try{
            boardDAO.updateCnt(contentNo, cnt);
        }catch (Exception e){
            log.error(e.getMessage());
        }

        return result;
    }

    public List<CommentDTO> getComments(HttpServletRequest req) throws SQLException, ClassNotFoundException {
        log.info("getComments()...");

        int contentNo = Integer.parseInt(req.getParameter("contentNo"));
        List<CommentDTO> commentDTOS = commentDAO.selectComments(contentNo);
        MemberDTO memberDTO= (MemberDTO) req.getSession().getAttribute("loginInfo");

        for(CommentDTO commentDTO : commentDTOS) {
            log.info(commentDTO.getMemberId());

            if(memberDTO != null) {
                if (commentDTO.getMemberId().equals(memberDTO.getMemberId())) {
                    commentDTO.setLogin(true);
                }
            }
        }
        return commentDTOS;
    }

    public boolean removeComment(HttpServletRequest request) throws Exception {
        log.info("removeComment()...");

        int commentNo = Integer.parseInt(request.getParameter("commentNo"));
        int parentNo = Integer.parseInt(request.getParameter("parentNo"));
        int contentNo = commentDAO.getContentNoByComment(commentNo);
        boolean result;

        if(request.getSession().getAttribute("superInfo") == null) { // 사용자 로그인일경우

            if (commentDAO.checkHasRe(parentNo) && commentNo == parentNo)
                result = commentDAO.updateCommentDie(commentNo); // 삭제된 댓글입니다 남김

            else
                result = commentDAO.deleteComment(commentNo); // 대댓글이 없으면 그냥 삭제

            int cnt = commentDAO.commentCnt(contentNo);
            BoardDAO boardDAO = new BoardDAO();
            try {
                boardDAO.updateCnt(contentNo, cnt);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        } else{ // 관리자 로그인일 경우
            result = commentDAO.adminUpdateCommentDie(commentNo); // 관리자에 의해 삭제된 댓글입니다 남김.

            int cnt = commentDAO.commentCnt(contentNo);
            BoardDAO boardDAO = new BoardDAO();
            try {
                boardDAO.updateCnt(contentNo, cnt);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        return result;
    }

    // 리뷰 마이페이지 선택삭제 버튼 메서드
    public void removeMyComment(HttpServletRequest request) throws Exception {
        log.info("remove board----------------");
        String [] conNo = request.getParameterValues("selectedItems2");
        for(String no : conNo){// no = 각각의 게시물 넘버값
            int commentNo = Integer.parseInt(no);
            int contentNo = commentDAO.getContentNoByComment(commentNo);
            int parentNo = commentDAO.getParentNoByComment(commentNo);

            if (commentDAO.checkHasRe(parentNo) && commentNo == parentNo)
                commentDAO.updateCommentDie(commentNo); // 삭제된 댓글입니다 남김

            else
                commentDAO.deleteComment(commentNo); // 대댓글이 없으면 그냥 삭제

            int cnt = commentDAO.commentCnt(contentNo);
            BoardDAO boardDAO = new BoardDAO();
            try {
                boardDAO.updateCnt(contentNo, cnt);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        log.info("removeMyComment()...");
    }


}
