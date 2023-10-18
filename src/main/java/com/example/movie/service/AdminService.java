package com.example.movie.service;

import com.example.movie.model.dao.AdminDAO;
import com.example.movie.model.dao.MemberDAO;
import com.example.movie.model.dto.AdminBoardDTO;
import com.example.movie.model.dto.AdminDTO;
import com.example.movie.model.dto.MemberDTO;
import com.example.movie.model.dao.MovieDAO;
import com.example.movie.model.dto.*;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Log4j2
public class AdminService {

    private final AdminDAO adminDAO;
    private final MovieDAO movieDAO;

    public AdminService(){
        adminDAO = new AdminDAO();
        movieDAO = new MovieDAO();
    }

    // 파일업로드
    private String getFileName(Part part){
        String fileName = null;
        String header = part.getHeader("content-disposition");
        log.info("File Header : " + header);

        int start = header.indexOf("filename=");
        fileName = header.substring(start + 10, header.length() - 1);
        log.info("file name : " + fileName);
        return fileName;
    }
//    addCrew // 제작진 제작 - 은석
    public void adminAddCrew(HttpServletRequest request){

        try{
            Part part = request.getPart("crewImg");
            String fileName = this.getFileName(part);
            log.info("crewImg : " + fileName);
            if(fileName != null && !fileName.isEmpty()){
                UUID uuid = UUID.randomUUID();
                fileName = uuid + fileName;
                log.info("crewImg if : " + fileName);
                part.write(fileName);
            }
            CrewDTO crewDTO = CrewDTO.builder()
                    .crewImg("/upload/" + fileName)
                    .crewName(request.getParameter("crewName"))
                    .build();
            movieDAO.insertCrew(crewDTO);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }
// addMovie // 영화 등록 -은석
    public void adminAddMovie(HttpServletRequest request) throws Exception {
        String movieName = request.getParameter("movieName");
        String releaseDate = request.getParameter("releaseDate");
        String region = request.getParameter("region");
        String genre = request.getParameter("genre");
        int audience = Integer.parseInt(request.getParameter("audience"));
        String runningtime = request.getParameter("runningtime");
        String outline = request.getParameter("outline");
        String mo = request.getParameter("mo");
        String[] crewNoList = request.getParameterValues("crewNo");

        int photoCnt = 1;

        String fileName = "";

        // 포스터 등록
        Part partPoster = request.getPart("poster");
        fileName = this.getFileName(partPoster);
        log.info("poster : " + fileName);
        if (fileName != null && !fileName.isEmpty()) {
            UUID uuid = UUID.randomUUID();
            fileName = uuid + fileName;
            log.info("poster if : " + fileName);
            partPoster.write(fileName);
        }
        String poster = "/upload/" + fileName;

        // 포토 등록
        List<String> photoList = new ArrayList<>();

        while(true){
            String photo = "photo" + photoCnt;
            if(request.getPart(photo) == null)
                break;

            Part partPhoto = request.getPart(photo);
            fileName = this.getFileName(partPhoto);
            log.info("photo : " + fileName);
            if (fileName != null && !fileName.isEmpty()) {
                UUID uuid = UUID.randomUUID();
                fileName = uuid + fileName;
                log.info("photo if : " + fileName);
                partPhoto.write(fileName);
                log.info("photoWrite success");
            }
            photoList.add("/upload/" + fileName);
            photoCnt++;
        }

        MovieDTO movieDTO = MovieDTO.builder()
                .movieName(movieName)
                .director("")
                .actor("")
                .releaseDate(releaseDate)
                .score(0)
                .region(region)
                .genre(genre)
                .audience(audience)
                .ranking(0)
                .runningtime(runningtime)
                .outline(outline)
                .poster(poster)
                .mo(mo)
                .build();

        log.info("movie insert------------" + movieDTO);
        // 영화 등록
        movieDAO.insert(movieDTO);
        log.info("movie insert complete------------");

        int movieNo = movieDAO.getLastMovieNo(); // 방금 등록한 영화 넘버

        log.info("get movieNo ------------" + movieNo);

        log.info("cast insert------------");
        // 출연진 등록
        for(String crewNo : crewNoList){
            String castRole = request.getParameter("castRole_" + crewNo);
            movieDAO.insertCast(Integer.parseInt(crewNo),movieNo,castRole);
        }
        log.info("cast insert complete------------");

        // 포토 등록
        for(String photoImg : photoList){
            movieDAO.insertPhoto(photoImg, movieNo);
        }
        log.info("photo insert complete------------");
        String directors = "";
        String actors = "";

        List<CastDTO> castList = movieDAO.getCasts(movieNo);
        for(CastDTO castDTO : castList){
            if(castDTO.getCastRole().equals("감독"))
                directors += castDTO.getCrewName() + "|";
            else
                actors += castDTO.getCrewName() + "|";
        }
        // 무비 테이블 배우, 감독 갱신
        movieDAO.updateCrewInMovie(directors,actors,movieNo);
        // 무비 랭킹 갱신
        movieDAO.updateRankingMovie();
        movieDAO.updateRankingOTT();

    }

    // 영화 수정 페이지용 데이터 얻기
    public void adminGetMovie(HttpServletRequest request) throws Exception {
        int movieNo = Integer.parseInt((request.getParameter("movieNo"))); // movieNo 파라미터를 추출해서
        MovieDTO movieDTO = movieDAO.selectOne(movieNo); // DB에서 해당 movieNo 값들을 호출
        List<CastDTO> castList = movieDAO.getCasts(movieNo); // 출연진
        List<PhotoDTO> photoList = movieDAO.getPhoto(movieNo); // 포토

        request.setAttribute("movieDTO", movieDTO); // 영화 정보
        request.setAttribute("castList",castList); // 출연,제작진 리스트
        request.setAttribute("photoList",photoList); // 포토 리스트
    }


    //    (super)login 복붙 - 종원 // 새로운 관리자 전용 session 생성

    public AdminDTO adminLogin(HttpServletRequest req) throws Exception { // 관리자 로그인 확인
        String superId =  req.getParameter("superId");
        String superPw =  req.getParameter("superPw");
        AdminDTO adminDTO = adminDAO.getSuperPw(superId, superPw);
        log.info("adminService : " + adminDTO);
        return adminDTO;
    }

//    memberList 제작 - 종원
    static final int MemberCount = 10; // 멤버리스트 제한 갯수
    public void adminGetMemberList(HttpServletRequest request) throws Exception { // 게시물 전체목록
        List<MemberDTO> memberDTOList;
        int pageNum = 1; // 페이지번호의 기본값
        int limit = MemberCount; // 페이지당 멤버 수

        if(request.getParameter("pageNum") != null){
            pageNum = Integer.parseInt(request.getParameter("pageNum"));
        }
        String items = request.getParameter("items"); // 검색어
        String text = request.getParameter("text");

        int totalRecord = adminDAO.getMemberCount(items, text);
        memberDTOList = adminDAO.viewMyMember(pageNum, limit, items, text);

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
            request.setAttribute("memberDTOList", memberDTOList);
            request.setAttribute("startNum", startNum);

        }catch (Exception e){
            log.error(e.getMessage());
            log.info("게시글 목록 생성과정에서 에러");
            request.setAttribute("error" , "게시글 목록이 정상적으로 생성되지 않았습니다");
        }
    }

    /*---------- 게시판 메서드 작업 시작 -------------*/
    static final int NOTIECOUNT = 10; // 공지사항 제한 갯수

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
            request.setAttribute("adminBoardDTO", adminBoardDTO);
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
        adminBoardDTO.setSuperId(request.getParameter("superId"));
        adminBoardDTO.setSuperName(request.getParameter("superName"));

        log.info(adminBoardDTO);

        try{
            adminDAO.adminInsertNotice(adminBoardDTO);
        } catch (Exception e){
            log.error(e.getMessage());
        }
    }

    // 공지사항 삭제
    public void adminRemoveNotice(HttpServletRequest request) throws Exception {
        if(request.getParameter("cno") == null){ // 다중 삭제
            String[] cno = request.getParameterValues("chBox");
            for(String no : cno){
                Integer.parseInt(no); // no = 각각의 게시물 넘버값
                adminDAO.adminDeleteNotice(Integer.parseInt(no));
            }
        } else{ // 개별삭제
            int cno = Integer.parseInt(request.getParameter("cno"));
            try{
                adminDAO.adminDeleteNotice(cno);
            } catch (Exception e){
                log.error(e.getMessage());
                log.info("공지사항 삭제하는 과정에서 에러");
                request.setAttribute("error", "공지사항을 정상적으로 삭제하지 못함");
            }
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



    // movieList - 수홍
// movieList.jsp에서 영화 목록을 보여주기 위한 요청을 처리하는 메소드(관리자용)
    public void adminMovieListAll(HttpServletRequest request) {
        List<MovieDTO> movieList;
        try {
            movieList = adminDAO.adminSelectAll();
            request.setAttribute("movieList", movieList);
        } catch (Exception e) {
            log.info(e.getMessage());
            log.info("영화 목록 생성 과정에서 문제 발생");
            request.setAttribute("error", "영화 목록이 정상적으로 처리되지 않았습니다.");
        }
    }
    // removeMovie - 수홍
// 영화 삭제 메소드(관리자용)
    public void adminRemoveMovie (HttpServletRequest request) {
        int movieNo = Integer.parseInt(request.getParameter("movieNo"));
        try {
            adminDAO.adminDeleteOne(movieNo);
        } catch (Exception e) {
            log.info(e.getMessage());
            log.info("영화를 삭제하는 과정에서 문제 발생");
            request.setAttribute("error", "영화를 정상적으로 삭제하지 못했습니다.");
        }
    }
    // modifyMovie - 수홍
// 영화 수정 메소드(관리자용)
    public void adminModifyMovie(HttpServletRequest request) throws Exception {

        int movieNo = Integer.parseInt(request.getParameter("movieNo"));
        String movieName = request.getParameter("movieName");
        String releaseDate = request.getParameter("releaseDate");
        String region = request.getParameter("region");
        String genre = request.getParameter("genre");
        int audience = Integer.parseInt(request.getParameter("audience"));
        String runningtime = request.getParameter("runningtime");
        String outline = request.getParameter("outline");
        String mo = request.getParameter("mo");
        String[] crewNoList = request.getParameterValues("crewNo");

        String fileName = "";
        String poster = request.getParameter("prePoster"); // 기존 포스터 경로

        // 포스터가 변경됐으면 새로 등록
        if(request.getPart("poster") != null) {
            Part partPoster = request.getPart("poster");
            fileName = this.getFileName(partPoster);
            log.info("poster : " + fileName);
            if (fileName != null && !fileName.isEmpty()) {
                UUID uuid = UUID.randomUUID();
                fileName = uuid + fileName;
                log.info("poster if : " + fileName);
                partPoster.write(fileName);
            }
            poster = "/upload/" + fileName;
        }

        // 포토 등록
        List<String> photoList = new ArrayList<>();

        int photoCnt = 1;
        while(true){
            String photo = "photo" + photoCnt;
            if(request.getPart(photo) == null)
                break;

            Part partPhoto = request.getPart(photo);
            fileName = this.getFileName(partPhoto);
            log.info("photo : " + fileName);
            if (fileName != null && !fileName.isEmpty()) {
                UUID uuid = UUID.randomUUID();
                fileName = uuid + fileName;
                log.info("photo if : " + fileName);
                partPhoto.write(fileName);
                log.info("photoWrite success");
            }
            photoList.add("/upload/" + fileName);
            photoCnt++;
        }

        MovieDTO movieDTO = MovieDTO.builder()
                .movieNo(movieNo)
                .movieName(movieName)
                .director("")
                .actor("")
                .releaseDate(releaseDate)
                .region(region)
                .genre(genre)
                .audience(audience)
                .runningtime(runningtime)
                .outline(outline)
                .poster(poster)
                .mo(mo)
                .build();

        log.info("movie update------------" + movieDTO);
        // 영화 업데이트
        adminDAO.adminModifyMovie(movieDTO);
        log.info("movie update complete------------");

        // 기존 출연진 비우기
        log.info("cast remove complete------------");
        movieDAO.removeCast(movieNo);

        // 출연진 등록
        for(String crewNo : crewNoList){
            String castRole = request.getParameter("castRole_" + crewNo);
            movieDAO.insertCast(Integer.parseInt(crewNo),movieNo,castRole);
        }
        log.info("cast insert complete------------");

        // 포토 등록
        for(String photoImg : photoList){
            movieDAO.insertPhoto(photoImg, movieNo);
        }
        log.info("photo insert complete------------");
        String directors = "";
        String actors = "";

        List<CastDTO> castList = movieDAO.getCasts(movieNo);
        for(CastDTO castDTO : castList){
            if(castDTO.getCastRole().equals("감독"))
                directors += castDTO.getCrewName() + "|";
            else
                actors += castDTO.getCrewName() + "|";
        }
        // 무비 테이블 배우, 감독 갱신
        movieDAO.updateCrewInMovie(directors,actors,movieNo);
        // 무비 랭킹 갱신
        movieDAO.updateRankingMovie();
        movieDAO.updateRankingOTT();
    }
}
