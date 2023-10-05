package com.example.movie.service;

import com.example.movie.model.dao.SearchDAO;
import com.example.movie.model.dto.SearchVO;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Log4j2
public class SearchService {
    private final SearchDAO searchDAO;

    public SearchService(){searchDAO = new SearchDAO();}

    public void SearchList(HttpServletRequest request) throws Exception{ // 전체 검색결과목록
        String items = request.getParameter("items"); // 검색카테고리
        String text = request.getParameter("text"); // 검색어
        List<SearchVO> searchVOList;
        try{
            searchVOList = searchDAO.searchAll(items, text);
            request.setAttribute("searchVOList", searchVOList);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public void SearchListM(HttpServletRequest request) throws Exception { // 무비만 검색
        String items = request.getParameter("items"); // 검색카테고리
        String text = request.getParameter("text"); // 검색어
        List<SearchVO> searchVOListM;
        try{
            searchVOListM = searchDAO.searchM(items, text);
            request.setAttribute("searchVOListM", searchVOListM);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public void SearchListO(HttpServletRequest request) throws Exception { // OTT만 검색
        String items = request.getParameter("items"); // 검색카테고리
        String text = request.getParameter("text"); // 검색어
        List<SearchVO> searchVOListO;
        try{
            searchVOListO = searchDAO.searchO(items, text);
            request.setAttribute("searchVOListO", searchVOListO);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }
}
