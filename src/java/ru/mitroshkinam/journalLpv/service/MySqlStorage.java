package ru.mitroshkinam.journalLpv.service;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.sql.DataSource;

public class MySqlStorage {
    private Connection connection;
    @Resource(lookup = "java:/comp/env/jdbc/journallpv")
//    @Resource(name = "jdbc/journallpv")
    private DataSource dataSource;

    public MySqlStorage() {
        try {
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setUser("root");
            dataSource.setPassword("root");
            dataSource.setURL("jdbc:mysql://localhost:3306/journallpv?useLegacyDatetimeCode=false&serverTimezone=UTC");
//            dataSource.setUrl("jdbc:mysql://localhost:3306/journallpv");
            
//            dataSource.setServerName("localhost");
//            dataSource.setPort(3306);
//            dataSource.setDatabaseName("journallpv");
            
//            dataSource.setServerName("jdbc:mysql://localhost:3306/journallpv");
            this.connection = dataSource.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(MySqlStorage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
