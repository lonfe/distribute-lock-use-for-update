package com.yyy;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ForUpdateTool {

    public static void getLock(Connection conn, String sql) {
        try {
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                System.out.println("...get lock...");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void releaseLock(Connection conn) {
        try {
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
