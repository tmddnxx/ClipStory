package com.example.movie.model.dao;


import com.example.movie.model.dto.*;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Log4j2
public class AdminDAO {

    // 관리자 로그인 - 종원
    public AdminDTO getSuperPw(String superId, String superPw) throws Exception { // 관리자 비밀번호 얻기
        String sql = "select * FROM `super_account` WHERE `superId` = ? and `superPw` = ?";

        log.info("superId : " + superId);
        log.info("superPw : " + superPw);

        AdminDTO adminDTO = null;

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,superId);
        preparedStatement.setString(2,superPw);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()){
            log.info("resultSet : " + resultSet.getString("superId"));

            adminDTO = adminDTO.builder()
                    .superId(resultSet.getString("superId"))
                    .superPw(resultSet.getString("superPw"))
                    .superName(resultSet.getString("superName"))
                    .build();
        }
        return adminDTO;
    }

//    memberList 제작 - 승우

    // member테이블의 전체 수
    public int getMemberCount(String items, String text) throws Exception {

        int cnt = 0;

        String sql;
        if(items == null || text == null) { // 검색어가 없는 경우
            sql = "select count(*) from member";
        }
        else {
            sql = "select count(*) from member where " + items + " like '%" + text + "%'";
        }
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()) { // 데이터가 있는 경우
            cnt = resultSet.getInt(1);
        }
        return cnt;
    }

    // 회원목록
    public List<MemberDTO> viewMyMember(int page, int limit, String items, String text) throws Exception {
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();

        int start = (page - 1) * limit;
        String sql;

        if(items == null || text == null) {
            sql = "select * from member order by memberId";
        }
        else {
            sql = "select * from member where " + items + " like '%" + text + "%' order by memberId";
        }
        sql += " limit " + start + ", " + limit;

        List<MemberDTO> MemberDTOList = new ArrayList<>();

        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            MemberDTO memberDTO = com.example.movie.model.dto.MemberDTO.builder()
                    .memberId(resultSet.getString("memberId"))
                    .name(resultSet.getString("name"))
                    .nickName(resultSet.getString("nickName"))
                    .joinDate(String.valueOf(resultSet.getTimestamp("joinDate")))
                    .build();
            MemberDTOList.add(memberDTO);
        }
        return MemberDTOList;
    }



    /* 공지사항 메서드 작업 시작 */

    // 공지사항 전체 갯수 구하기
    public int adminGetNoticeListCount(String items, String text) throws Exception {
        int cnt = 0;

        String sql;
        if(items == null || text == null) { // 검색어가 없는 경우
            sql = "select count(*) from admin_board";
        }
        else {
            sql = "select count(*) from admin_board where " + items + " like '%" + text + "%'";
        }
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()) { // 데이터가 있는 경우
            cnt = resultSet.getInt(1);
        }
        return cnt;
    }

    // 공지사항 목록가져오기
    public List<AdminBoardDTO> adminNoticeSelectAll(int page, int limit, String items, String text) throws Exception{
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        int start = (page - 1) * limit;
        String sql;

        if(items == null || text == null) {
            sql = "select * from admin_board order by cno desc";
        }
        else {
            sql = "select * from admin_board where " + items + " like '%" + text + "%' order by cno desc";
        }
        sql += " limit " + start + ", " + limit;

        List<AdminBoardDTO> adminBoardDTOList = new ArrayList<>();

        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            AdminBoardDTO adminBoardDTO = new AdminBoardDTO();
            adminBoardDTO.setCno(resultSet.getInt("cno"));
            adminBoardDTO.setTitle(resultSet.getString("title"));
            adminBoardDTO.setContent(resultSet.getString("content"));
            adminBoardDTO.setAddDate(resultSet.getDate("addDate"));
            adminBoardDTO.setSuperId(resultSet.getString("superId"));
            adminBoardDTO.setSuperName(resultSet.getString("superName"));
            adminBoardDTOList.add(adminBoardDTO);
        }
        return adminBoardDTOList;
    }

    // 공지사항 상세뷰
    public AdminBoardDTO adminSelectNotice(int cno) throws Exception{
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        AdminBoardDTO adminBoardDTO = null;
        String sql = "select * from admin_board where cno = ?";
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, cno);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()){
            adminBoardDTO = new AdminBoardDTO();
            adminBoardDTO.setCno(resultSet.getInt("cno"));
            adminBoardDTO.setTitle(resultSet.getString("title"));
            adminBoardDTO.setContent(resultSet.getString("content"));
            adminBoardDTO.setAddDate(resultSet.getDate("addDate"));
            adminBoardDTO.setSuperId(resultSet.getString("superId"));
            adminBoardDTO.setSuperName(resultSet.getString("superName"));
        }
        return adminBoardDTO;
    }

    /* 공지사항추가 */
    public void adminInsertNotice(AdminBoardDTO adminBoardDTO) throws Exception {
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        String sql = "insert into admin_board (title, content, addDate, superId, superName) values(?, ?, now(), ?, ?)";
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, adminBoardDTO.getTitle());
        preparedStatement.setString(2, adminBoardDTO.getContent());
        preparedStatement.setString(3, adminBoardDTO.getSuperId());
        preparedStatement.setString(4, adminBoardDTO.getSuperName());
        preparedStatement.executeUpdate();
    }

    // 공지사항 삭제
    public void adminDeleteNotice(int cno) throws Exception{
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        String sql = "delete from admin_board where cno = ?";
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, cno);
        if(preparedStatement.executeUpdate() == 0){
            throw new SQLException("삭제할 공지사항이 없습니다");
        }
    }

    // 공지사항 수정
    public void adminModifyNotice(AdminBoardDTO adminBoardDTO) throws Exception {
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        String sql = "update admin_board set title =?, content =? where cno = ?";
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, adminBoardDTO.getTitle());
        preparedStatement.setString(2, adminBoardDTO.getContent());
        preparedStatement.setInt(3, adminBoardDTO.getCno());
        preparedStatement.executeUpdate();
    }

    /* 게시판 메서드 작업 끝 */
/* ---------------------------------------------------------------------------------------------------- */

//    movieList 복붙 - 수홍
// 영화 목록 전체 출력(관리자용)
    public List<MovieDTO> adminSelectAll() throws SQLException {
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
    //    removeMovie 복붙  - 수홍
// 해당 컨텐츠 삭제하는 메소드 (PK기준으로 선택)(관리자용)
    public void adminDeleteOne(int movieNo) throws SQLException {
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();

        String sql = "DELETE FROM movie WHERE movieNo = ?";
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, movieNo);
        if(preparedStatement.executeUpdate() == 0) {
            throw new SQLException("DB에러");
        }
    }
    //    modifyMovie 복붙 -수홍
// 영화 수정 메소드
    public void adminModifyMovie(MovieDTO movieDTO) throws Exception {
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        String sql = "update movie set movieName = ?,  director = ?, actor = ?, " +
                "releaseDate = ?, region = ?, genre = ?, audience = ?, runningtime = ?, " +
                "outline = ?, poster = ?, mo = ? where movieNo = ?";
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, movieDTO.getMovieName());
        preparedStatement.setString(2, movieDTO.getDirector());
        preparedStatement.setString(3, movieDTO.getActor());
        preparedStatement.setString(4, movieDTO.getReleaseDate());
        preparedStatement.setString(5, movieDTO.getRegion());
        preparedStatement.setString(6, movieDTO.getGenre());
        preparedStatement.setInt(7, movieDTO.getAudience());
        preparedStatement.setString(8, movieDTO.getRunningtime());
        preparedStatement.setString(9, movieDTO.getOutline());
        preparedStatement.setString(10, movieDTO.getPoster());
        preparedStatement.setString(11, movieDTO.getMo());
        preparedStatement.setInt(12, movieDTO.getMovieNo());
        preparedStatement.executeUpdate();
    }

    // 영화 추가
    public void adminAddMovie(MovieDTO movieDTO) throws Exception {
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

    // 배우|감독 추가
    public void adminInsertCrew(CrewDTO crewDTO) throws Exception{
        String sql = "INSERT INTO crew (crewName, crewImg)"
                + "VALUES (?, ?)";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, crewDTO.getCrewName());
        preparedStatement.setString(2, crewDTO.getCrewImg());
        preparedStatement.executeUpdate();
    }

    // 가장 최근에 등록한 영화 넘버 가져오기
    public int adminGetLastMovieNo() throws Exception{
        String sql = "SELECT * from movie order by movieNo desc";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        int movieNo = 0;
        if(resultSet.next()){
            movieNo = resultSet.getInt("movieNo");
        }

        return movieNo;
    }

    // 출연 정보 추가
    public void adminInsertCast(int crewNo, int movieNo, String castRole) throws Exception {
        String sql = "INSERT INTO `cast` (`crewNo`, `movieNo`, `castRole`) VALUES (?, ?, ?)";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, crewNo);
        preparedStatement.setInt(2, movieNo);
        preparedStatement.setString(3, castRole);
        preparedStatement.executeUpdate();
    }

    // 포토 추가
    public void adminInsertPhoto(String photoImg, int movieNo) throws Exception{
        String sql = "INSERT INTO photo (photoImg, movieNo)"
                + "VALUES (?, ?)";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, photoImg);
        preparedStatement.setInt(2, movieNo);
        preparedStatement.executeUpdate();
    }

    // 영화 테이블의 감독/배우 갱신
    public void adminUpdateCrewInMovie(String director, String actor, int movieNo) throws Exception{
        String sql = "UPDATE movie set director = ?, actor = ? where movieNo = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, director);
        preparedStatement.setString(2, actor);
        preparedStatement.setInt(3, movieNo);
        preparedStatement.executeUpdate();
    }

    // 해당 영화에 출연진이 있는지 확인
    public boolean adminIsHasCast(int movieNo) throws Exception {
        String sql = "select * FROM `cast` WHERE `movieNo` = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,movieNo);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next())
            return true;
        return false;
    }

    // 해당 영화에 포토가 있는지 확인
    public boolean adminIsHasPhoto(int movieNo) throws Exception{
        String sql = "select * FROM `photo` WHERE `movieNo` = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,movieNo);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next())
            return true;
        return false;
    }

    // 해당 영화의 출연정보 비우기
    public void adminRemoveCast(int movieNo) throws Exception {
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();

        String sql = "DELETE FROM cast WHERE movieNo = ?";
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, movieNo);
        preparedStatement.executeUpdate();
    }
    // 해당 영화의 포토 비우기

    public void adminRemovePhoto(int movieNo) throws Exception {
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();

        String sql = "DELETE FROM photo WHERE movieNo = ?";
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, movieNo);
        preparedStatement.executeUpdate();
    }

    // 영화 랭킹 업데이트
    public void adminUpdateRankingMovie() throws Exception {
        String sql = "UPDATE movie AS m\n" +
                "JOIN (\n" +
                "    SELECT movieNo, RANK() OVER (ORDER BY audience DESC) AS ranking\n" +
                "    FROM movie\n" +
                "    WHERE mo = 'm'\n" +
                ") AS mRanking ON m.movieNo = mRanking.movieNo\n" +
                "SET m.ranking = mRanking.ranking;";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
    }

    // ott랭킹 업데이트
    public void adminUpdateRankingOTT() throws Exception {
        String sql = "UPDATE movie AS m\n" +
                "JOIN (\n" +
                "    SELECT movieNo, RANK() OVER (ORDER BY audience DESC) AS ranking\n" +
                "    FROM movie\n" +
                "    WHERE mo = 'o'\n" +
                ") AS oRanking ON m.movieNo = oRanking.movieNo\n" +
                "SET m.ranking = oRanking.ranking;";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
    }


}
