package dao;


import com.example.movie.model.dao.ReviewDAO;
import com.example.movie.model.dto.ReviewDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

@Log4j2
public class ReviewDAOTests {
    private ReviewDAO reviewDAO;


    @BeforeEach
    public void ready() {
        reviewDAO = new ReviewDAO();
    }


    @Test
    public void testInsert() throws Exception {
        for (int i = 1; i <= 10; i++) {
            ReviewDTO reviewDTO = ReviewDTO.builder()
                    .nickName("홍길동" + i)
                    .review("ererer" + i)
                    .score(i)
                    .movieName("영화" + i)
                    .memberId("유저" + i)
                    .build();
            reviewDAO.insert(reviewDTO);
            log.info("입력완료");
        }
    }


    @Test
    public void testSelectAll() throws Exception {
        List<ReviewDTO> reviewList = reviewDAO.selectAll();
//        log.info(reviewList);
        //movieList.forEach(movieDTO -> log.info(movieDTO));

        for (ReviewDTO reviewDTO : reviewList) {
            log.info(reviewDTO);
            log.info("출력완료");
        }
    }


    @Test
    public void testDeleteOne() throws Exception {
        reviewDAO.deleteOne(1);
        log.info("삭제완료");
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