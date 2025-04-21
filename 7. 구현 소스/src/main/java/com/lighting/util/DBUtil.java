package com.lighting.util;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBUtil {
    public static Connection open() throws Exception {
        Context initContext = new InitialContext();
        Context envContext = (Context)initContext.lookup("java:comp/env");
        DataSource ds = (DataSource)envContext.lookup("jdbc/pool"); // context.xml에서 등록한 이름
        return ds.getConnection();
    }

    public static void close(Connection conn, PreparedStatement pstat) {
        try {
            if (pstat != null) {
                pstat.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
