package com.hwt.notes.jdbc;

import lombok.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.hwt.notes.constant.DatabaseConstant.*;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class MysqlConnector {
    @NonNull
    private String dataBaseName;
    private String userName;
    private String password;
    private String paramString;
    @NonNull
    private String host;
    private int port = 3306;

    public MysqlConnector(String dataBaseName,String userName,String password,String host){
        this.dataBaseName = dataBaseName;
        this.userName = userName;
        this.password = password;
        this.host = host;
    }

    public MysqlConnector(String dataBaseName,String userName,String password,String host,int port){
        this(dataBaseName,userName,password,host);
        this.port = port;
    }

    public Connection getConnection() throws SQLException {
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(MYSQL_BASE_URL)
                .append(host)
                .append(IP_PORT_SEPARATOR)
                .append(port)
                .append(URI_SEPARATOR)
                .append(dataBaseName);
        if (paramString!=null) {
            urlBuilder.append(paramString);
        }
        return DriverManager.getConnection(urlBuilder.toString(), userName, password);
    }
}
