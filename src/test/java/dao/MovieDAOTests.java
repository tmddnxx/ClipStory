package dao;

import com.example.movie.model.dao.MovieDAO;
import com.example.movie.model.dao.ReviewDAO;
import com.example.movie.model.dto.CastDTO;
import com.example.movie.model.dto.MovieDTO;
import com.example.movie.model.dto.ReviewDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

@Log4j2
public class MovieDAOTests {
    private MovieDAO movieDAO;
    private ReviewDAO reviewDAO;


    @BeforeEach
    public void ready() {
        movieDAO = new MovieDAO();
        reviewDAO = new ReviewDAO();
    }

//    @Test
//    public void testInsert() throws Exception {
//        for(int i = 1; i <= 10; i++) {
//            MovieDTO movieDTO = MovieDTO.builder()
//                    .movieName("무비"+i)
//                    .director("홍길동"+i)
//                    .actor("홍길순"+i)
//                    .releaseDate("2050-10-10"+i)
//                    .score(4+i)
//                    .region("한국"+i)
//                    .genre("느와르"+i)
//                    .audience(20000+i)
//                    .ranking(3+i)
//                    .runningtime("180분"+i)
//                    .outline("어쩌구저쩌구 어쩌구저쩌구 이리쿵 저리쿵 그래서 하지만 그러나 그럼에도 불구하고 그러므로 그리고 "+i)
//                    .poster("https://img1.daumcdn.net/thumb/C408x596/?fname=https%3A%2F%2Ft1.daumcdn.net%2Fmovie%2F27c29998d10ba744f8898f99541b93405bef27ec")
//                    .mo("m")
//                    .build();
//            movieDAO.insert(movieDTO);
//            log.info("입력완료");
//        }
//    }

    @Test
    public void testList() throws Exception {
        List<MovieDTO> movieList = movieDAO.selectAll();
        log.info(movieList);
        //movieList.forEach(movieDTO -> log.info(movieDTO));

        for (MovieDTO movieDTO : movieList) {
            log.info(movieDTO);
            log.info("출력완료");
        }
    }

    @Test
    public void testGetCasts() throws  Exception{
        List<CastDTO> castList = movieDAO.getCasts(1);
        castList.forEach(castDTO -> log.info(castDTO));
    }

    @Test
    public void testSelectOne() throws Exception {
        MovieDTO movieDTO1 = movieDAO.selectOne(1);
        log.info(movieDTO1);
        MovieDTO movieDTO2 = movieDAO.selectOne(2);
        log.info(movieDTO2);
        MovieDTO movieDTO3 = movieDAO.selectOne(3);
        log.info(movieDTO3);
        log.info("출력완료");
    }

//    @Test
//    public void testDeleteOne() throws Exception {
//        movieDAO.deleteOne(4);
//        log.info("삭제완료");
//    }

    @Test
    public void testInsertReview() throws Exception {

        for(int i = 0; i < 10; i++) {
            ReviewDTO reviewDTO = ReviewDTO.builder()
                    .nickName("hong" + i)
                    .review("리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰" + i)
                    .movieNo(14)
                    .score("3")
                    .memberId("test")
                    .build();
            reviewDAO.insertReview(reviewDTO);
        }
    }

    @Test
    public void movieLikeTest() throws Exception {
        String memberId = "test1";
        int movieNo = 1;
        boolean result = false;
        result = movieDAO.selectMovieLike(movieNo,memberId);
        log.info(result);
    }
//    @Test
//    public void testUpdateOne() throws Exception {
//        MovieDTO movieDTO = MovieDTO.builder()
//                .movieName("무비")
//                .director("홍길순")
//                .actor("홍길동")
//                .releaseDate("2048-10-10")
//                .score(4)
//                .region("세부")
//                .genre("코미디")
//                .audience(120)
//                .ranking(3)
//                .runningtime("180분")
//                .outline("국ㅁ저이ㅏㅁ저이머니ㅓㅁ인러닝;ㅏㅓ리;낭커")
//                .poster("img.jpg")
//                .movieNo(2)
//                .build();
//        movieDAO.updateOne(movieDTO);
//        log.info("업데이트 완료");
//    }
}