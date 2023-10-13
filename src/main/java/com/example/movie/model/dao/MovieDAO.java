package com.example.movie.model.dao;

import com.example.movie.model.dto.CrewDTO;
import com.example.movie.model.dto.MovieDTO;
import com.example.movie.model.dto.PhotoDTO;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Log4j2
public class MovieDAO {
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
                    .mo(resultSet.getString("mo"))
                    .avgScore(resultSet.getFloat("avgScore"))
                    .build();
            movieList.add(movieDTO);
        }
        return movieList;
    }

    public List<MovieDTO> selectMovie() throws SQLException{
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        List<MovieDTO> movieList = new ArrayList<>();

        String sql = "select * from movie where `mo` = 'm'";
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
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
                    .mo(resultSet.getString("mo"))
                    .avgScore(resultSet.getFloat("avgScore"))
                    .build();
            movieList.add(movieDTO);
        }
        return movieList;
    }

    public List<MovieDTO> selectOtt() throws SQLException {
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        List<MovieDTO> ottList = new ArrayList<>();

        String sql = "SELECT * FROM movie where mo = 'o'";
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
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
                    .mo(resultSet.getString("mo"))
                    .avgScore(resultSet.getFloat("avgScore"))
                    .build();
            ottList.add(movieDTO);
        }
        return ottList;
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
                    .mo(resultSet.getString("mo"))
                    .avgScore(resultSet.getFloat("avgScore"))
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

    public boolean selectMovieLike(int movieNo, String memberId) throws SQLException {
        String sql = "select * FROM `zzim` WHERE `memberId` = ? and `movieNo` = ?";


        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,memberId);
        preparedStatement.setInt(2,movieNo);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();


        if(resultSet.next())
            return true;
        return false;
    }
    public boolean insertMovieLike(int movieNo, String memberId) throws SQLException {
        String sql = "INSERT INTO `zzim` VALUES (?, ?) ";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,memberId);
        preparedStatement.setInt(2,movieNo);

        return preparedStatement.executeUpdate() == 1;
    }

    public boolean removeMovieLike(int movieNo, String memberId) throws  SQLException {
        String sql = "DELETE from `zzim` where memberId = ? and movieNo = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,memberId);
        preparedStatement.setInt(2,movieNo);

        return preparedStatement.executeUpdate() == 1;
    }

    public void insert(MovieDTO movieDTO) throws Exception {
        String sql = "INSERT INTO movie (movieName, director, actor, releaseDate,"
                + " region, genre, audience, ranking, runningtime, outline, poster, mo, avgScore)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
        preparedStatement.setString(12, movieDTO.getMo());
        preparedStatement.setFloat(13, movieDTO.getAvgScore());
        preparedStatement.executeUpdate();
    }

    // 출연 정보 추가
    public void insertCast(int crewNo, int movieNo) throws Exception{
        String sql = "INSERT INTO cast (crewNo, movieNo)"
                + "VALUES (?, ?)";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, crewNo);
        preparedStatement.setInt(2,movieNo);
        preparedStatement.executeUpdate();
    }

    // 출연진 가져오기
//    public List<CrewDTO> getCasts(int movieNo) throws Exception{
//        String sql = "SELECT crew.crewName, crew.crewImg, cast.castRole FROM crew JOIN cast ON crew.crewNo = cast.crewNo WHERE cast.movieNo = ?";
//
//    }

    // 배우|감독 추가
    public void insertCrew(CrewDTO crewDTO) throws Exception{
        String sql = "INSERT INTO crew (crewName, crewImg)"
                + "VALUES (?, ?)";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, crewDTO.getCrewName());
        preparedStatement.setString(2, crewDTO.getCrewImg());
        preparedStatement.executeUpdate();
    }

    // 포토 추가
    public void insertPhoto(PhotoDTO photoDTO) throws Exception{
        String sql = "INSERT INTO photo (photoImg, movieNo)"
                + "VALUES (?, ?)";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, photoDTO.getPhotoImg());
        preparedStatement.setInt(2, photoDTO.getMovieNo());
        preparedStatement.executeUpdate();
    }


    public void updateAvgScore(int movieNo, float avgScore) throws Exception {
        String sql = "UPDATE movie set avgScore = ? where movieNo = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setFloat(1, avgScore);
        preparedStatement.setInt(2, movieNo);
        preparedStatement.executeUpdate();
    }
}

