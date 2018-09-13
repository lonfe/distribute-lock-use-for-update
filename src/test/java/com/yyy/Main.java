package com.yyy;

import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    Connection conn = null;

    @Before
    public void prepare() {
        String driverName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/world";
        String user = "root";
        String password = "root";
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test0() {
        System.out.println("...test0...");
        String sql = "select * from method_lock where method_name = 'getlock' for update";
        ForUpdateTool.getLock(conn, sql);
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ForUpdateTool.releaseLock(conn);
        System.out.println("...release lock...");
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("...end...");
    }

    @Test
    public void test1() {
        System.out.println("...test1...");
        String sql = "select * from method_lock where method_name = 'getLock' for update";
        ForUpdateTool.getLock(conn, sql);
    }
}
