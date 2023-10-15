package com.example.movie.controller;

import com.example.movie.model.dto.CommentDTO;
import com.example.movie.model.dto.CrewDTO;
import com.example.movie.service.AdminService;
import com.example.movie.service.BoardService;
import com.example.movie.service.CommentService;
import com.example.movie.service.MovieService;
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
    CommentService commentService = CommentService.INSTANCE;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        if(action == null){
            action = "main";
        }

        switch (action) {
            case "main" : // 메인화면
                req.getRequestDispatcher("/WEB-INF/admin/main.jsp").forward(req, resp);
                break;

            // 영화 관련 컨트롤러
            case "addCrew" : // 배우/감독 등록 페이지
                req.getRequestDispatcher("/WEB-INF/admin/addCrew.jsp").forward(req, resp);
                break;
            case "addMovie": // 영화 등록 페이지
                req.getRequestDispatcher("/WEB-INF/admin/addMovie.jsp").forward(req, resp);
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
                boardService.removeBoard(req);
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
                req.getRequestDispatcher("WEB-INF/admin/noticeView.jsp").forward(req, resp);
                break;

            case "noticeAddView" : // 공지사항 글쓰기 뷰
                req.getRequestDispatcher("WEB-INF/admin/addNotice.jsp").forward(req, resp);
                break;

            case "noticeAdd" : // 공지사항 추가
                adminService.adminAddNotice(req);
                resp.sendRedirect("/admin?action=noticeList");
                break;

            case "noticeRemove" : // 공지사항 삭제
                adminService.adminRemoveNotice(req);
                resp.sendRedirect("/admin?action=noticeList");
                break;

            case "noticeModifyView" : // 공지사항 수정뷰
                req.getRequestDispatcher("/WEB-INF/admin/modifyNotice.jsp").forward(req, resp);
                break;

            case "noticeModify" : // 공지사항 수정
                adminService.adminModifyNotice(req);
                resp.sendRedirect("/admin?action=noticeList");
                break;

            /*----------- 게시판 컨트롤러 끝-----------*/
        /* ------------------------------------------------------*/
            /*----------- 댓글 컨트롤러 시작-----------*/
            case "commentList" : // 댓글 목록

            case "commentRemove" : // 댓글삭제

            /*----------- 댓글 컨트롤러 끝-----------*/
        }
    }
}
