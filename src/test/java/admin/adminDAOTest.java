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

    @Test
    public void testMemberList() throws Exception {
        log.info(adminDAO.viewMyMember(1, 10, "memberId", "t"));
    }
}
