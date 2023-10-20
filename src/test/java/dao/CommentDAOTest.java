package dao;

import com.example.movie.model.dao.BoardDAO;
import com.example.movie.model.dao.CommentDAO;
import com.example.movie.model.dto.CommentDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

@Log4j2
public class CommentDAOTest {
    private CommentDAO commentDAO;

    @BeforeEach
    public void ready() { commentDAO = new CommentDAO();}

    @Test
    public void testInsertComment() throws Exception {

        for(int i = 0; i < 40; i++) {
            CommentDTO commentDTO = CommentDTO.builder()
                    .nickName("testNickName" + i)
                    .comment("댓글" + i)
                    .memberId("test" + i )
                    .contentNo(93)
                    .build();
            commentDAO.insertComment(commentDTO);
        }
    }

    @Test
    public void testSelectComments() throws Exception {
        List<CommentDTO> commentDTOList = commentDAO.selectComments(79);
        log.info(commentDTOList);

        Assertions.assertEquals(5, commentDTOList.size());
    }

    @Test
    public void testDeleteComment() throws SQLException, ClassNotFoundException {
        int commentNo = 1;
        commentDAO.deleteComment(commentNo);
    }

    @Test
    public void testInsertCommentRe() throws Exception {

        for(int i = 0; i < 10; i++) {
            CommentDTO commentDTO = CommentDTO.builder()
                    .nickName("hi")
                    .comment("대댓글" + i)
                    .memberId("test")
                    .contentNo(79)
                    .parentNo(25)
                    .build();
            commentDAO.insertCommentRe(commentDTO);
        }
    }

}
