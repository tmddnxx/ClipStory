package com.example.movie.service;

import com.example.movie.model.dao.AdminDAO;
import com.example.movie.model.dto.AdminBoardDTO;
import com.example.movie.model.dto.AdminDTO;
import com.example.movie.model.dto.MemberDTO;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Log4j2
public class AdminService {
    private final AdminDAO adminDAO;
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



    static final int LISTCOUNT = 10; // 페이지당 게시믈 수 제한
    static final int NOTIECOUNT = 10; // 공지사항 제한 갯수


    public AdminService(){ adminDAO = new AdminDAO();}

    /*---------- 게시판 메서드 작업 시작 -------------*/

    // 공지사항 전체 목록
    public void adminListNotice(HttpServletRequest request) throws Exception {
        List<AdminBoardDTO> adminBoardDTOList;
        int pageNum = 1; // 페이지번호의 기본값
        int limit = NOTIECOUNT; // 페이지당 게시물 수

        if(request.getParameter("pageNum") != null){
            pageNum = Integer.parseInt(request.getParameter("pageNum"));
        }
        String items = request.getParameter("items"); // 검색어
        String text = request.getParameter("text");

        int totalRecord = adminDAO.adminGetNoticeListCount(items, text);
        adminBoardDTOList = adminDAO.adminNoticeSelectAll(pageNum, limit, items, text);

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
            request.setAttribute("adminBoardDTOList", adminBoardDTOList);
            request.setAttribute("startNum", startNum);

        }catch (Exception e){
            log.error(e.getMessage());
            log.info("게시글 목록 생성과정에서 에러");
            request.setAttribute("error" , "게시글 목록이 정상적으로 생성되지 않았습니다");
        }
    }


    // 공지사항 상세뷰
    public void adminGetNotice(HttpServletRequest request){
        int cno = Integer.parseInt(request.getParameter("cno"));

        try{
            AdminBoardDTO adminBoardDTO = adminDAO.adminSelectNotice(cno);
        } catch (Exception e){
            log.error(e.getMessage());
            log.info("공지사항을 가져오는 과정에서 에러");
            request.setAttribute("error", "공지사항을 정상적으로 가져오지 못함.");
        }
    }

    // 공지사항 추가
    public void adminAddNotice(HttpServletRequest request) {
        AdminBoardDTO adminBoardDTO = new AdminBoardDTO();

        adminBoardDTO.setTitle(request.getParameter("title"));
        adminBoardDTO.setContent(request.getParameter("content"));
        adminBoardDTO.setSuperId(request.getParameter("super    Id"));
        adminBoardDTO.setSuperName(request.getParameter("superName"));

        log.info(adminBoardDTO);

        try{
            adminDAO.adminInsertNotice(adminBoardDTO);
        } catch (Exception e){
            log.error(e.getMessage());
        }
    }

    // 공지사항 삭제
    public void adminRemoveNotice(HttpServletRequest request){
        int cno = Integer.parseInt(request.getParameter("cno"));

        try{
            adminDAO.adminDeleteNotice(cno);
        } catch (Exception e){
            log.error(e.getMessage());
            log.info("공지사항 삭제하는 과정에서 에러");
            request.setAttribute("error", "공지사항을 정상적으로 삭제하지 못함");
        }
    }

    // 공지사항 수정
    public void adminModifyNotice(HttpServletRequest request){
        AdminBoardDTO adminBoardDTO = new AdminBoardDTO();

        adminBoardDTO.setCno(Integer.parseInt(request.getParameter("cno")));
        adminBoardDTO.setTitle(request.getParameter("title"));
        adminBoardDTO.setContent(request.getParameter("content"));
        adminBoardDTO.setSuperId(request.getParameter("superId"));
        adminBoardDTO.setSuperName(request.getParameter("superName"));

        try {
            adminDAO.adminModifyNotice(adminBoardDTO);
            log.info(adminBoardDTO);
        }catch (Exception e){
            log.error(e.getMessage());
            request.setAttribute("error", "수정 오류");
        }
    }
    /*------------게시판 메서드 작업 끝 --------------- */



//    movieList 수정 - 수홍
//    removeMovie 복붙  - 수홍
//    modifyMovie 복붙 -수홍
//    movieView 복붙 - 수홍
//    reviewList 복붙 - 수홍
//    removeReview 복붙 - 수홍
}
