package com.example.movie.model.dao;

import com.example.movie.model.dto.CastDTO;
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

    // 영화 목록 전체 출력(사실상 유저페이지 에선 쓰이진 않음)
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

    // movie만 불러오는 메소드
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

    // ott만 불러오는 메소드
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

    // 해당 movie나 ott 선택했을 때 해당 컨텐츠의 PK를 가져와서 출력하는 메소드
    public MovieDTO selectOne(int movieNo) throws SQLException {
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

    // 해당 영화 찜등록 여부
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
    // 찜 등록
    public boolean insertMovieLike(int movieNo, String memberId) throws SQLException {
        String sql = "INSERT INTO `zzim` VALUES (?, ?) ";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,memberId);
        preparedStatement.setInt(2,movieNo);

        return preparedStatement.executeUpdate() == 1;
    }
    // 찜 제거
    public boolean removeMovieLike(int movieNo, String memberId) throws  SQLException {
        String sql = "DELETE from `zzim` where memberId = ? and movieNo = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,memberId);
        preparedStatement.setInt(2,movieNo);

        return preparedStatement.executeUpdate() == 1;
    }

    // 출연진 가져오기
    public List<CastDTO> getCasts(int movieNo) throws Exception{
        String sql = "SELECT crew.crewName, crew.crewImg, cast.* FROM crew JOIN cast ON crew.crewNo = cast.crewNo WHERE cast.movieNo = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, movieNo);

        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        List<CastDTO> castList = new ArrayList<>();
        while(resultSet.next()){
            CastDTO castDTO = CastDTO.builder()
                    .crewName(resultSet.getString("crewName"))
                    .crewImg(resultSet.getString("crewImg"))
                    .crewNo(resultSet.getInt("crewNo"))
                    .movieNo(resultSet.getInt("movieNo"))
                    .castRole(resultSet.getString("castRole"))
                    .build();

            castList.add(castDTO);
        }

        return castList;
    }
    // 포토 가져오기
    public List<PhotoDTO> getPhoto(int movieNo) throws Exception{
        String sql = "SELECT * from photo where movieNo = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, movieNo);

        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        List<PhotoDTO> photoList = new ArrayList<>();
        while(resultSet.next()){
            PhotoDTO photoDTO = PhotoDTO.builder()
                    .photoImg(resultSet.getString("photoImg"))
                    .photoNo(resultSet.getInt("photoNo"))
                    .movieNo(resultSet.getInt("movieNo"))
                    .build();

            photoList.add(photoDTO);
        }
        return photoList;
    }

    // 배우/감독 목록 출력
    public List<CrewDTO> getCrews() throws Exception {
        String sql = "SELECT * from crew";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        List<CrewDTO> crewList = new ArrayList<>();
        while(resultSet.next()){
            CrewDTO crewDTO = CrewDTO.builder()
                    .crewNo(resultSet.getInt("crewNo"))
                    .crewName(resultSet.getString("crewName"))
                    .crewImg(resultSet.getString("crewImg"))
                    .build();

            crewList.add(crewDTO);
        }
        return crewList;
    }


    // 리뷰 등록할때 평점 계산해서 movie테이블에 넣어주기 위한 메소드
    public void updateAvgScore(int movieNo, float avgScore) throws Exception {
        String sql = "UPDATE movie set avgScore = ? where movieNo = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setFloat(1, avgScore);
        preparedStatement.setInt(2, movieNo);
        preparedStatement.executeUpdate();
    }


}

