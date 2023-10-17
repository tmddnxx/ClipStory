package com.example.movie.service;

import com.example.movie.model.dao.BoardDAO;
import com.example.movie.model.dao.CommentDAO;
import com.example.movie.model.dto.CommentDTO;
import com.example.movie.model.dto.MemberDTO;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
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
        if(commentDAO.checkHasRe(parentNo) && commentNo == parentNo)
            result = commentDAO.updateCommentDie(commentNo);
        else
            result = commentDAO.deleteComment(commentNo);

        int cnt = commentDAO.commentCnt(contentNo);
        BoardDAO boardDAO = new BoardDAO();
        try{
            boardDAO.updateCnt(contentNo, cnt);
        }catch (Exception e){
            log.error(e.getMessage());
        }

        return result;
    }

    // 리뷰 마이페이지 선택삭제 버튼 메서드
    public boolean removeMyComment(HttpServletRequest request) throws Exception { // 내 댓글
        log.info("removeMyComment()...");

        String[] commentNumbers = request.getParameterValues("commentNo"); // 댓글 번호의 배열을 얻습니다
        int contentNo = 0; // contentNo를 초기화합니다

        for (String commentNumber : commentNumbers) {
            int commentNo = Integer.parseInt(commentNumber);
            int parentNo = Integer.parseInt(request.getParameter("parentNo"));

            if (contentNo == 0) {
                contentNo = commentDAO.getContentNoByComment(commentNo);
            }

            boolean result;

            if (commentDAO.checkHasRe(parentNo) && commentNo == parentNo) {
                result = commentDAO.updateCommentDie(commentNo);
            } else {
                result = commentDAO.deleteComment(commentNo);
            }

            int cnt = commentDAO.commentCnt(contentNo);
            BoardDAO boardDAO = new BoardDAO();

            try {
                boardDAO.updateCnt(contentNo, cnt);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }

        return true; // 필요에 따라 반환 값을 수정할 수 있습니다.
    }


}
