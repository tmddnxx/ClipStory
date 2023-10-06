package com.example.movie.model.dao;


import com.example.movie.model.dto.SearchVO;
import lombok.Cleanup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SearchDAO {
    public List<SearchVO> searchAll(String items, String text) throws Exception{
        /* 전체 검색하기 */
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();

        String sql = "select * from movie where " + items + " like '%" + text + "%' order by ranking";


        List<SearchVO> searchVoList = new ArrayList<>();

        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            SearchVO searchVO = SearchVO.builder()
                    .movieNo(resultSet.getInt("movieNo"))
                    .movieName(resultSet.getString("movieName"))
                    .director(resultSet.getString("director"))
                    .actor(resultSet.getString("actor"))
                    .releaseDate(resultSet.getString("releaseDate"))
                    .region(resultSet.getString("region"))
                    .genre(resultSet.getString("genre"))
                    .audience(resultSet.getInt("audience"))
                    .ranking(resultSet.getInt("ranking"))
                    .runningtime(resultSet.getString("runningtime"))
                    .outline(resultSet.getString("outline"))
                    .poster(resultSet.getString("poster"))
                    .m_or_o(resultSet.getString("mo"))
                    .build();
            searchVoList.add(searchVO);
        }
        return searchVoList;
    }

    public List<SearchVO> searchM(String items, String text) throws Exception{
        /* 무비 검색하기 */
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        String sql = "select * from movie where mo = 'm' and " + items + " like '%" + text + "%' order by ranking";
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        List<SearchVO> searchVoListM = new ArrayList<>();
        while (resultSet.next()){
            SearchVO searchVO = SearchVO.builder()
                    .movieNo(resultSet.getInt("movieNo"))
                    .movieName(resultSet.getString("movieName"))
                    .director(resultSet.getString("director"))
                    .actor(resultSet.getString("actor"))
                    .releaseDate(resultSet.getString("releaseDate"))
                    .region(resultSet.getString("region"))
                    .genre(resultSet.getString("genre"))
                    .audience(resultSet.getInt("audience"))
                    .ranking(resultSet.getInt("ranking"))
                    .runningtime(resultSet.getString("runningtime"))
                    .outline(resultSet.getString("outline"))
                    .poster(resultSet.getString("poster"))
                    .m_or_o(resultSet.getString("mo"))
                    .build();
            searchVoListM.add(searchVO);
        }
        return searchVoListM;
    }

    public List<SearchVO> searchO(String items, String text) throws Exception{
        /* OTT 검색하기 */
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        String sql = "select * from movie where mo = 'o' and " + items + " like '%" + text + "%' order by ranking";
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        List<SearchVO> searchVoListO = new ArrayList<>();
        while (resultSet.next()){
            SearchVO searchVO = SearchVO.builder()
                    .movieNo(resultSet.getInt("movieNo"))
                    .movieName(resultSet.getString("movieName"))
                    .director(resultSet.getString("director"))
                    .actor(resultSet.getString("actor"))
                    .releaseDate(resultSet.getString("releaseDate"))
                    .region(resultSet.getString("region"))
                    .genre(resultSet.getString("genre"))
                    .audience(resultSet.getInt("audience"))
                    .ranking(resultSet.getInt("ranking"))
                    .runningtime(resultSet.getString("runningtime"))
                    .outline(resultSet.getString("outline"))
                    .poster(resultSet.getString("poster"))
                    .m_or_o(resultSet.getString("mo"))
                    .build();
            searchVoListO.add(searchVO);
        }
        return searchVoListO;
    }
}
