package com.example.movie.service;

import com.example.movie.model.dao.MemberDAO;
import com.example.movie.model.dao.MovieDAO;
import com.example.movie.model.dto.CastDTO;
import com.example.movie.model.dto.CrewDTO;
import com.example.movie.model.dto.MovieDTO;
import com.example.movie.model.dto.PhotoDTO;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Log4j2
public class MovieService {
    private final MovieDAO movieDAO;
    private final MemberDAO memberDAO;


    public MovieService() {
        movieDAO = new MovieDAO();
        memberDAO = new MemberDAO();
    }


    // movie만 출력하는 메소드
    public void listMovie(HttpServletRequest request) {
        List<MovieDTO> listMovie;
        try {
            listMovie = movieDAO.selectMovie();
//            for (MovieDTO movieDTO : listMovie) {
//                movieDTO.setAvgScore(reviewDAO.avgScore(movieDTO.getMovieNo()));
//            }
            request.setAttribute("listMovie", listMovie);
//            log.info("리스트무비 : " + listMovie);
        } catch (Exception e) {
            log.info(e.getMessage());
            log.info("영화 목록 생성 과정에서 문제 발생");
            request.setAttribute("error", "영화 목록이 정상적으로 처리되지 않았습니다.");
        }
    }

    // ott만 출력하는 메소드
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


    // 특정 영화를 클릭했을 때 호출하기 위한 요청을 처리하는 메소드
    public void getMovie(HttpServletRequest request) {
        int movieNo = Integer.parseInt((request.getParameter("movieNo"))); // movieNo 파라미터를 추출해서
        String memberId = (String) request.getSession().getAttribute("sessionId");
        try {
            MovieDTO movieDTO = movieDAO.selectOne(movieNo); // DB에서 해당 movieNo 값들을 호출
            List<CastDTO> castList = movieDAO.getCasts(movieNo); // 출연진
            List<PhotoDTO> photoList = movieDAO.getPhoto(movieNo); // 포토
            if(movieDAO.selectMovieLike(movieNo,memberId))
                request.setAttribute("zzim", true);
            else
                request.setAttribute("zzim", false);
            String[] actors = movieDTO.getActor().split("\\|");
            String[] directors = movieDTO.getDirector().split("\\|");
            log.info("cast list : -----------------" +castList);
            request.setAttribute("actors",actors); // 상단 배우이름
            request.setAttribute("directors",directors); // 상단 감독 이름
            request.setAttribute("movieDTO", movieDTO); // 영화 정보
            request.setAttribute("castList",castList); // 출연,제작진 리스트
            request.setAttribute("photoList",photoList); // 포토 리스트
            request.setAttribute("actors",actors);
            request.setAttribute("directors",directors);
            request.setAttribute("movieDTO", movieDTO);
        } catch (Exception e) {
            log.info(e.getMessage());
            log.info("영화를 가져오는 과정에서 문제 발생!");
            request.setAttribute("error", "영화를 정상적으로 가져오지 못했습니다!");
        }
    }


    // 찜 등록
    public boolean insertMovieLike(HttpServletRequest request) throws Exception {
        int movieNo = Integer.parseInt(request.getParameter("movieNo")); // movieNo 파라미터를 추출해서
        String memberId = (String) request.getSession().getAttribute("sessionId");
        memberDAO.zzimCntUpdate(memberId,true);
        return movieDAO.insertMovieLike(movieNo,memberId);
    }

    // 찜 해제
    public boolean removeMovieLike(HttpServletRequest request) throws Exception {
        int movieNo = Integer.parseInt(request.getParameter("movieNo")); // movieNo 파라미터를 추출해서
        String memberId = (String) request.getSession().getAttribute("sessionId");
        memberDAO.zzimCntUpdate(memberId,false);
        return movieDAO.removeMovieLike(movieNo, memberId);

    }


    // 배우/감독 가져오기
    public List<CrewDTO> getCrews() throws  Exception {

        List<CrewDTO> crewList = movieDAO.getCrews();
        return crewList;
    }
}
