package com.example.movie.service;

import com.example.movie.model.dao.AdminDAO;
import com.example.movie.model.dao.MovieDAO;
import com.example.movie.model.dto.*;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.util.ArrayList;
import java.util.List;

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
        String runningtime = request.getParameter("runningtime");
        String outline = request.getParameter("outline");
        String mo = request.getParameter("mo");
        String[] crewNoList = request.getParameterValues("crewNo");

        String fileName = "";

        // 포스터 등록
        Part partPoster = request.getPart("poster");
        fileName = this.getFileName(partPoster);
        log.info("poster : " + fileName);
        if (fileName != null && !fileName.isEmpty()) {
            log.info("poster if : " + fileName);
            partPoster.write(fileName);
        }
        String poster = "/upload" + fileName;

        // 포토 등록
        List<String> photoList = new ArrayList<>();
        photoList.add("/upload/photo1");
        photoList.add("/upload/photo2");
        photoList.add("/upload/photo3");

//        int photoCnt = 1;
//        while(true){
//            String photo = "photo" + photoCnt;
//            if(request.getPart(photo) == null)
//                break;
//
//            Part partPhoto = request.getPart(photo);
//            fileName = this.getFileName(partPhoto);
//            log.info("photo : " + fileName);
//            if (fileName != null && !fileName.isEmpty()) {
//                log.info("photo if : " + fileName);
//                partPoster.write(fileName);
//            }
//            photoList.add("/upload" + fileName);
//            photoCnt++;
//        }

        MovieDTO movieDTO = MovieDTO.builder()
                .movieName(movieName)
                .director("")
                .actor("")
                .releaseDate(releaseDate)
                .score(0)
                .region(region)
                .genre(genre)
                .audience(0)
                .ranking(0)
                .runningtime(runningtime)
                .outline(outline)
                .poster(poster)
                .mo(mo)
                .build();

        // 영화 등록
        movieDAO.insert(movieDTO);

        int movieNo = movieDAO.getLastMovieNo(); // 방금 등록한 영화 넘버

        // 출연진 등록
        for(String crewNo : crewNoList){
            String castRole = request.getParameter("castRole_" + crewNo);
            movieDAO.insertCast(Integer.parseInt(crewNo),movieNo,castRole);
        }
        // 포토 등록
        for(String photoImg : photoList){
            movieDAO.insertPhoto(photoImg, movieNo);
        }
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


    }
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
