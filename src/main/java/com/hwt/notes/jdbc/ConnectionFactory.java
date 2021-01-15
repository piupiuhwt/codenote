package com.hwt.notes.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import static com.hwt.notes.constant.DatabaseConstant.MYSQL_DRIVER;

public class ConnectionFactory {
    private static final MysqlConnector mysqlConnector;

    static{
        try {
            Class.forName(MYSQL_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        mysqlConnector = new MysqlConnector("test","root","111111","192.168.149.129");
    }

    public static Connection getConnection(Database databaseName) throws SQLException{
        if (databaseName == Database.MYSQL) {
            return mysqlConnector.getConnection();
        }
        return null;
    }
}
