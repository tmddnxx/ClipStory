package com.example.movie.controller;

import com.example.movie.model.dto.CommentDTO;
import com.example.movie.service.BoardService;
import com.example.movie.service.CommentService;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@WebServlet("/comment/*")
public class CommentController extends HttpServlet {
    CommentService commentService = new CommentService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String RequestURI = req.getRequestURI();
        String contextPath = req.getContextPath();
        String command = RequestURI.substring(contextPath.length());
        resp.setCharacterEncoding("UTF-8"); // 한글 인코딩
        log.info("command : " + command);

        switch (command){
            case "/comment/add": //댓글 추가
                try {
                    JSONObject jsonObject = new JSONObject();
                    if (commentService.addComment(req)){
                        jsonObject.put("result", "true");
                    }
                    else{
                        jsonObject.put("result", "false");
                    }
                    resp.getWriter().println(jsonObject.toJSONString());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "/comment/addre": //대댓글 추가
                try {
                    JSONObject jsonObject = new JSONObject();
                    if (commentService.addCommentRe(req)){
                        jsonObject.put("result", "true");
                    }
                    else{
                        jsonObject.put("result", "false");
                    }
                    resp.getWriter().println(jsonObject.toJSONString());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "/comment/get": //댓글 목록 가져오기
                try{
                    List<CommentDTO> commentDTOS = commentService.getComments(req);

                    JSONArray jsonArray = new JSONArray();
                    for(CommentDTO commentDTO : commentDTOS){
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("commentNo", commentDTO.getCommentNo());
                        jsonObject.put("parentNo", commentDTO.getParentNo());
                        jsonObject.put("nickName",commentDTO.getNickName());
                        jsonObject.put("comment",commentDTO.getComment());
                        jsonObject.put("addDate",commentDTO.getAddDate());
                        jsonObject.put("isLogin",commentDTO.isLogin());
                        jsonArray.add(jsonObject);
                    }
                    resp.getWriter().println(jsonArray.toJSONString());

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "/comment/remove": //댓글 삭제
                try {
                    JSONObject jsonObject = new JSONObject();
                    if (commentService.removeComment(req)){
                        jsonObject.put("result", "true");
                    } else {
                        jsonObject.put("result", "false");
                    }
                    resp.getWriter().println(jsonObject.toJSONString());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "/comment/myRemoveOne": // 마이페이지 댓글 개별 삭제
                try {
                    commentService.removeComment(req);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                resp.sendRedirect("/list.mypage");
                break;

            case "/comment/myRemove":
                try { // 마이페이지 체크한 댓글 목록 삭제
                    // 댓글을 삭제하고 삭제된 댓글 목록을 얻기 위해 removeMyComment 메서드를 호출합니다
                    commentService.removeMyComment(req);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                resp.sendRedirect("/list.mypage");
                break;
        }
    }

}
