package com.example.movie.model.dao;

import com.example.movie.model.dto.MovieDTO;
import lombok.extern.log4j.Log4j2;

import lombok.Cleanup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Log4j2
public class MovieDAO {
//    private final String jdbcDriver = "org.mariadb.jdbc.Driver";
//    private final String jdbcUrl = "jdbc:mariadb://localhost:3308/movie";
//    private final String jdbcUser = "root";
//    private final String jdbcPassword = "1460";
//
//    public Connection open() {
//        // 디비 연결 메소드 각각의 메소드마다 연결을 만들고 해제하는 구조
//        Connection connection = null;
//        try {
//            Class.forName(jdbcDriver);
//            connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
//            log.info("연결 완료");
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        }
//        return connection;
//    }

    public List<MovieDTO> selectAll() throws SQLException {
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        List<MovieDTO> movieList = new ArrayList<>();

        String sql = "SELECT * FROM movie";
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            MovieDTO movieDTO = MovieDTO.builder()
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
                    .m_or_o(resultSet.getString("m_or_o"))
                    .build();
            movieList.add(movieDTO);
        }
        return movieList;
    }

    public MovieDTO selectOne(int movieNo) throws SQLException {
        // 뉴스 목록에서 뉴스를 선택했을 때 특정 뉴스 기사의 세부 내용을 보여주기 위한 메소드
        log.info("selectOne(int movieNo) ...");

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();

        String sql = "SELECT * FROM movie WHERE movieNo = ?";
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, movieNo);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        MovieDTO movieDTO = null;
        if(resultSet.next()) {
            movieDTO = MovieDTO.builder()
                    .movieNo(movieNo)
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
                    .m_or_o(resultSet.getString("m_or_o"))
                    .build();
        }
        return movieDTO;

    }

    public void deleteOne(int movieNo) throws SQLException {
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();

        String sql = "DELETE FROM movie WHERE movieNo = ?";
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, movieNo);
        if(preparedStatement.executeUpdate() == 0) {
            throw new SQLException("DB에러");
        }
    }


    public void insert(MovieDTO movieDTO) throws Exception {
        String sql = "INSERT INTO movie (movieName, director, actor, releaseDate,"
                + " region, genre, audience, ranking, runningtime, outline, poster, m_or_o)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?)";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, movieDTO.getMovieName());
        preparedStatement.setString(2, movieDTO.getDirector());
        preparedStatement.setString(3, movieDTO.getActor());
        preparedStatement.setString(4, movieDTO.getReleaseDate());
        preparedStatement.setString(5, movieDTO.getRegion());
        preparedStatement.setString(6, movieDTO.getGenre());
        preparedStatement.setInt(7, movieDTO.getAudience());
        preparedStatement.setInt(8, movieDTO.getRanking());
        preparedStatement.setString(9, movieDTO.getRunningtime());
        preparedStatement.setString(10, movieDTO.getOutline());
        preparedStatement.setString(11, movieDTO.getPoster());
        preparedStatement.setString(12, movieDTO.getM_or_o());
        preparedStatement.executeUpdate();
    }
//
//    public List<MovieDTO> selectAll() throws Exception { // 목록을 저장할 컬렉션 객체
//        String sql = "SELECT * FROM movie";
//        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
//        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
//
//        List<MovieDTO> list = new ArrayList<>();
//        while(resultSet.next()) {
//            MovieDTO movieDTO = MovieDTO.builder()
//                    .movieNo(resultSet.getInt("movieNo"))
//                    .movieName(resultSet.getString("movieName"))
//                    .director(resultSet.getString("director"))
//                    .actor(resultSet.getString("actor"))
//                    .releaseDate(resultSet.getString("releaseDate"))
//                    .score(resultSet.getInt("score"))
//                    .region(resultSet.getString("region"))
//                    .genre(resultSet.getString("genre"))
//                    .audience(resultSet.getInt("audience"))
//                    .ranking(resultSet.getInt("ranking"))
//                    .runningtime(resultSet.getString("runningtime"))
//                    .outline(resultSet.getString("outline"))
//                    .poster(resultSet.getString("poster"))
//                    .build();
//            list.add(movieDTO);
//        }
//        return list;
//    }
//
//    public MovieDTO selectOne(int movieNo) throws Exception {
//        // MovieNo를 받아서 해당 번호의 데이터를 들고옴
//        String sql = "SELECT * FROM movie WHERE movieNo = ?";
//        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
//        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.setInt(1, movieNo);
//        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
//
//        MovieDTO movieDTO = null;
//        if(resultSet.next()) {
//            movieDTO = MovieDTO.builder()
//                    .movieNo(movieNo)
//                    .movieName(resultSet.getString("movieName"))
//                    .director(resultSet.getString("director"))
//                    .actor(resultSet.getString("actor"))
//                    .releaseDate(resultSet.getString("releaseDate"))
//                    .score(resultSet.getInt("score"))
//                    .region(resultSet.getString("region"))
//                    .genre(resultSet.getString("genre"))
//                    .audience(resultSet.getInt("audience"))
//                    .ranking(resultSet.getInt("ranking"))
//                    .runningtime(resultSet.getString("runningtime"))
//                    .outline(resultSet.getString("outline"))
//                    .poster(resultSet.getString("poster"))
//                    .build();
//        }
//        return movieDTO;
//    }
//
//
//    public void deleteOne(int movieNo) throws Exception {
//        // movieNo를 받아서 해당 번호의 데이터를 삭제
//        String sql = "DELETE FROM movie WHERE movieNo = ?";
//
//        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
//        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
//
//        preparedStatement.setInt(1, movieNo);
//        preparedStatement.executeUpdate();
//    }
//
//    public void updateOne(MovieDTO movieDTO) throws Exception {
//        String sql = "UPDATE movie SET movieName = ?, director = ?, actor = ?, releaseDate = ?, score = ?, region = ?, genre = ?, " +
//                "audience = ?, ranking = ?, runningtime = ?, outline = ?, poster = ? WHERE movieNo = ?";
//        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
//        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
//
//        preparedStatement.setString(1, movieDTO.getMovieName());
//        preparedStatement.setString(2, movieDTO.getDirector());
//        preparedStatement.setString(3, movieDTO.getActor());
//        preparedStatement.setString(4, movieDTO.getReleaseDate());
//        preparedStatement.setInt(5, movieDTO.getScore());
//        preparedStatement.setString(6, movieDTO.getRegion());
//        preparedStatement.setString(7, movieDTO.getGenre());
//        preparedStatement.setInt(8, movieDTO.getAudience());
//        preparedStatement.setInt(9, movieDTO.getRanking());
//        preparedStatement.setString(10, movieDTO.getRunningtime());
//        preparedStatement.setString(11, movieDTO.getOutline());
//        preparedStatement.setString(12, movieDTO.getPoster());
//        preparedStatement.setInt(13, movieDTO.getMovieNo());
//
//        preparedStatement.executeUpdate();
//    }
}

