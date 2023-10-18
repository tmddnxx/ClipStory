package admin;

import com.example.movie.model.dao.AdminDAO;
import com.example.movie.model.dto.BoardDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

@Log4j2
public class adminDAOTest {
    private AdminDAO adminDAO;
    @BeforeEach
    public void ready(){  adminDAO = new AdminDAO();}

//    @Test
//    public void testAdminSelectAll() throws Exception {
//        List<BoardDTO> boardDTOList = adminDAO.adminSelectAll(1,1,"title", "123");
//        for(BoardDTO boardDTO : boardDTOList){
//            log.info(boardDTO);
//        }
//    }
}
