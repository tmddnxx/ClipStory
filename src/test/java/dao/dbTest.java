package dao;

import com.example.movie.model.dao.ConnectionUtil;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

@Log4j2
public class dbTest {

    @Test
    public void dbConnectTest() throws SQLException {
        Connection connection = ConnectionUtil.INSTANCE.getConnection();

    }
}
