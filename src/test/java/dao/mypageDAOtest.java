package dao;

import com.example.movie.model.dao.MyPageDAO;
import com.example.movie.model.dto.BoardDTO;
import com.example.movie.model.dto.CommentDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

@Log4j2
public class mypageDAOtest {
    private MyPageDAO myPageDAO;
    @BeforeEach
    public void ready() { myPageDAO = new MyPageDAO();}

    @Test
    public void getMyPageBoardTest() throws Exception {
        myPageDAO.viewMyContent("1111");
        log.info(myPageDAO.viewMyContent("1111"));
        List<BoardDTO> myBoardList = myPageDAO.viewMyContent("1111");
        for(BoardDTO boardDTO : myBoardList){
            log.info(boardDTO);
        }
    }


    @Test
    public void getMyPageCommentTest() throws Exception {
        myPageDAO.viewMyComment("1111");
        log.info(myPageDAO.viewMyComment("1111"));
        List<CommentDTO> myCommentList = myPageDAO.viewMyComment("1111");
        for(CommentDTO commentDTO : myCommentList){
            log.info(commentDTO);
        }

    }

}
