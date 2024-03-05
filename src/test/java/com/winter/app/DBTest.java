package com.winter.app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
public class DBTest {
    @Autowired
    private DataSource dataSource;

    @Test
    void test() throws SQLException {
        Connection con = dataSource.getConnection();
        assertNotNull(con);
    }
}
