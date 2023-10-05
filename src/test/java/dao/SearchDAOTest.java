package dao;

import com.example.movie.model.dao.SearchDAO;
import com.example.movie.model.dto.SearchVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Log4j2
public class SearchDAOTest {
    private SearchDAO searchDAO;
    @BeforeEach
    public void ready(){searchDAO = new SearchDAO();}
    @Test
    public void searchTest() throws Exception{
        log.info(searchDAO.searchAll("movieName", "무비"));
    }
}
