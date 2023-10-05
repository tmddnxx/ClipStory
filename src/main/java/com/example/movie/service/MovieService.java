package com.example.movie.service;

import com.example.movie.model.dao.MemberDAO;
import com.example.movie.model.dao.MovieDAO;
import com.example.movie.model.dao.ReviewDAO;
import com.example.movie.model.dto.MovieDTO;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.util.List;
@Log4j2
public class MovieService {
    private final MovieDAO movieDAO;
    private final MemberDAO memberDAO;


    public MovieService() {
        movieDAO = new MovieDAO();
        memberDAO = new MemberDAO();
    }


    public void movieListAll(HttpServletRequest request) {
        // movieList.jsp에서 영화 목록을 보여주기 위한 요청을 처리하는 메소드
        List<MovieDTO> movieList;
        try {
            movieList = movieDAO.selectAll();
            request.setAttribute("movieList", movieList);
        } catch (Exception e) {
            log.info(e.getMessage());
            log.info("영화 목록 생성 과정에서 문제 발생");
            request.setAttribute("error", "영화 목록이 정상적으로 처리되지 않았습니다.");
        }

    }

    public void listMovie(HttpServletRequest request) {
        List<MovieDTO> listMovie;
        try {
            listMovie = movieDAO.selectMovie();
//            for (MovieDTO movieDTO : listMovie) {
//                movieDTO.setAvgScore(reviewDAO.avgScore(movieDTO.getMovieNo()));
//            }
            request.setAttribute("listMovie", listMovie);
            log.info("리스트무비 : " + listMovie);
        } catch (Exception e) {
            log.info(e.getMessage());
            log.info("영화 목록 생성 과정에서 문제 발생");
            request.setAttribute("error", "영화 목록이 정상적으로 처리되지 않았습니다.");
        }
    }
    public void listOtt(HttpServletRequest request) {
        List<MovieDTO> listOtt;
        try {
            listOtt = movieDAO.selectOtt();
//            for (MovieDTO movieDTO : listOtt) {
//                movieDTO.setAvgScore(reviewDAO.avgScore(movieDTO.getMovieNo()));
//            }
            request.setAttribute("listOtt", listOtt);
        } catch (Exception e) {
            log.info(e.getMessage());
            log.info("OTT 목록 생성 과정에서 문제 발생");
            request.setAttribute("error", "OTT 목록이 정상적으로 처리되지 않았습니다.");
        }
    }

    public void getMovieDTO(HttpServletRequest request) {
        // 특정 뉴스 기사를 클릭했을 때 호출하기 위한 요청을 처리하는 메소드
        int movieNo = Integer.parseInt((request.getParameter("movieNo"))); // movieNo 파라미터를 추출해서
        String memberId = (String) request.getSession().getAttribute("sessionId");
        try {
            MovieDTO movieDTO = movieDAO.selectOne(movieNo); // DB에서 해당 movieNo 값들을 호출
            if(movieDAO.selectMovieLike(movieNo,memberId))
                request.setAttribute("zzim", true);
            else
                request.setAttribute("zzim", false);
            request.setAttribute("movieDTO", movieDTO);
        } catch (Exception e) {
            log.info(e.getMessage());
            log.info("영화를 가져오는 과정에서 문제 발생!");
            request.setAttribute("error", "영화를 정상적으로 가져오지 못했습니다!");
        }
    }

    public void removeMovieDTO (HttpServletRequest request) {
        int movieNo = Integer.parseInt(request.getParameter("movieNo"));
        try {
            movieDAO.deleteOne(movieNo);
        } catch (Exception e) {
            log.info(e.getMessage());
            log.info("영화를 삭제하는 과정에서 문제 발생");
            request.setAttribute("error", "영화를 정상적으로 삭제하지 못했습니다.");
        }
    }

    public boolean insertMovieLike(HttpServletRequest request) throws Exception {
        int movieNo = Integer.parseInt((request.getParameter("movieNo"))); // movieNo 파라미터를 추출해서
        String memberId = (String) request.getSession().getAttribute("sessionId");
        memberDAO.zzimCntUpdate(memberId,true);
        return movieDAO.insertMovieLike(movieNo,memberId);
    }

    public boolean removeMovieLike(HttpServletRequest request) throws Exception {
        int movieNo = Integer.parseInt((request.getParameter("movieNo"))); // movieNo 파라미터를 추출해서
        String memberId = (String) request.getSession().getAttribute("sessionId");
        memberDAO.zzimCntUpdate(memberId,false);
        return movieDAO.removeMovieLike(movieNo, memberId);
    }
}
