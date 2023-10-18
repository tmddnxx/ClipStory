package com.example.movie.controller;

import com.example.movie.model.dto.AdminDTO;
import com.example.movie.model.dto.CommentDTO;
import com.example.movie.model.dto.CrewDTO;
import com.example.movie.model.dto.ReviewDTO;
import com.example.movie.service.*;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Log4j2
@WebServlet("/admin")
@MultipartConfig(maxFileSize = 2 * 1024 * 1024, location = "c:/upload")
public class AdminController extends HttpServlet {
    AdminService adminService = new AdminService();
    BoardService boardService = new BoardService();
    MovieService movieService = new MovieService();
    ReviewService reviewService = ReviewService.getInstance();
    CommentService commentService = CommentService.INSTANCE;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");
        if(action == null){
            action = "main";
        }
        /*-----------------movie 컨트롤러------------------*/
        switch (action) {
            case "main" : // 관리자 메인페이지
                req.getRequestDispatcher("/WEB-INF/admin/main.jsp").forward(req, resp);
                break;
            case "movieList" : // 관리자 영화 목록 페이지
                adminService.adminMovieListAll(req); // 관리자 서비스 모든 영화 목록 출력
                movieService.listOtt(req);
                movieService.listMovie(req);
                req.getRequestDispatcher("/WEB-INF/admin/movieList.jsp").forward(req, resp);
                break;
            case "removeMovie" : // 영화 한개 삭제
                adminService.adminRemoveMovie(req);
                resp.sendRedirect("/admin?action=movieList");
                break;
            case "modifyMovieProcess" : // 영화 한개 수정
                try {
                    adminService.adminModifyMovie(req);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                resp.sendRedirect("/admin?action=movieList");
                break;
            case "movieView" : // 영화 상세 페이지
                movieService.getMovie(req);
                try {
                    reviewService.getReviews(req);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                req.getRequestDispatcher("/WEB-INF/admin/movieView.jsp").forward(req, resp);
                break;
            case "addCrew" : // 배우/감독 등록 페이지
                req.getRequestDispatcher("/WEB-INF/admin/addCrew.jsp").forward(req, resp);
                break;
            case "addMovie": // 영화 등록 페이지
                req.getRequestDispatcher("/WEB-INF/admin/addMovie.jsp").forward(req, resp);
                break;
            case "modifyMovie": // 영화 수정 페이지
                try {
                    adminService.adminGetMovie(req); // 수정용 데이터 가져오기
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                req.getRequestDispatcher("/WEB-INF/admin/modifyMovie.jsp").forward(req, resp);
                break;
            case "addCast": // 출연진 등록 팝업 페이지
                req.getRequestDispatcher("/WEB-INF/admin/addCast.jsp").forward(req, resp);
                break;
            case "addCrewProcess" : // 배우/감독 추가
                adminService.adminAddCrew(req);
                resp.sendRedirect("/admin");
                break;
            case "addMovieProcess":
                try {
                    adminService.adminAddMovie(req);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                resp.sendRedirect("/admin");
                break;
            case "getCrew": // 팝업창에서 얻을 배우/감독 목록
                try {
                    List<CrewDTO> crewList = movieService.getCrews();
                    // collection List를 json으로 변환.
                    JSONArray jsonArray = new JSONArray(); // 목록을 저장해야 되서 JSONArray 사용.
                    for (CrewDTO crewDTO : crewList) {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("crewNo", crewDTO.getCrewNo());
                        jsonObject.put("crewName", crewDTO.getCrewName());
                        jsonObject.put("crewImg", crewDTO.getCrewImg());
                        jsonArray.add(jsonObject);
                    }
                    resp.getWriter().print(jsonArray.toJSONString());
                }  catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            /*-----------------movie 컨트롤러 끝------------------*/

            /*----------- 게시판 컨트롤러 시작-----------*/
            case "boardList" : // 게시판 목록
                try {
                    boardService.listBoard(req); // 유저 게시물 목록
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                req.getRequestDispatcher("/WEB-INF/admin/boardList.jsp").forward(req, resp);
                break;
            case "boardGet" : // 유저게시물 상세 뷰
                boardService.getBoard(req);
                req.getRequestDispatcher("/WEB-INF/admin/boardView.jsp").forward(req, resp);
                break;

            case "boardRemove" : // 유저게시물 삭제
                try {
                    boardService.removeBoard(req);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                resp.sendRedirect("/admin?action=boardList");
                break;

            case "noticeList" : // 공지사항 목록
                try {
                    adminService.adminListNotice(req); // 공지사항 목록
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                req.getRequestDispatcher("/WEB-INF/admin/noticeList.jsp").forward(req, resp);
                break;

            case "noticeGet" : // 공지사항 상세 뷰
                adminService.adminGetNotice(req);
                req.getRequestDispatcher("/WEB-INF/admin/noticeView.jsp").forward(req, resp);
                break;

            case "noticeAddView" : // 공지사항 글쓰기 뷰
                req.getRequestDispatcher("/WEB-INF/admin/addNotice.jsp").forward(req, resp);
                break;

            case "noticeAdd" : // 공지사항 추가
                adminService.adminAddNotice(req);
                resp.sendRedirect("/admin?action=noticeList");
                break;

            case "noticeRemove" : // 공지사항 삭제
                try {
                    adminService.adminRemoveNotice(req);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                resp.sendRedirect("/admin?action=noticeList");
                break;

            case "noticeModifyView" : // 공지사항 수정뷰
                adminService.adminGetNotice(req);
                req.getRequestDispatcher("/WEB-INF/admin/modifyNotice.jsp").forward(req, resp);
                break;

            case "noticeModify" : // 공지사항 수정
                adminService.adminModifyNotice(req);
                adminService.adminGetNotice(req);
                req.getRequestDispatcher("/WEB-INF/admin/noticeView.jsp").forward(req, resp);
                break;

            /*----------- 게시판 컨트롤러 끝-----------*/

            /*----------- 댓글 컨트롤러 시작-----------*/
            case "commentList" : // 댓글 목록

            case "commentRemove" : // 댓글삭제

            /*----------- 댓글 컨트롤러 끝-----------*/

            /* -----------------회원 목록 불러오기 -------------------- */
            case "memberList" :
                try {
                    adminService.adminGetMemberList(req);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                req.getRequestDispatcher("/WEB-INF/admin/memberList.jsp").forward(req,resp);
                break;
        }

        /*------------리뷰 스위치 문-----------------*/
        String RequestURI = req.getRequestURI();
        String contextPath = req.getContextPath();
        String command = RequestURI.substring(contextPath.length());
        AdminDTO adminDTO = new AdminDTO();
        log.info("command : " + command);
        switch (command) {
            case "/review/get":
                try {
                    List<ReviewDTO> reviewDTOS = reviewService.getReviews(req);
                    // collection List를 json으로 변환.
                    JSONArray jsonArray = new JSONArray(); // 목록을 저장해야 되서 JSONArray 사용.
                    for (ReviewDTO reviewDTO : reviewDTOS) {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("reviewNo", reviewDTO.getReviewNo());
                        jsonObject.put("nickName", reviewDTO.getNickName());
                        jsonObject.put("review", reviewDTO.getReview());
                        jsonObject.put("addDate", reviewDTO.getAddDate());
                        jsonObject.put("score", reviewDTO.getScore());
                        jsonObject.put("memberId", reviewDTO.getMemberId());
                        jsonObject.put("isLogin", reviewDTO.isLogin());
                        jsonArray.add(jsonObject);
                    }
                    resp.getWriter().print(jsonArray.toJSONString());
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "/review/remove":
                try {
                    JSONObject jsonObject = new JSONObject();
                    if (reviewService.removeReview(req)) {
                        jsonObject.put("result", "true");
                    } else {
                        jsonObject.put("result", "false");
                    }
                    resp.getWriter().println(jsonObject.toJSONString());
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
        }
        /*------------------리뷰 끝--------------------------------*/
    }
}
