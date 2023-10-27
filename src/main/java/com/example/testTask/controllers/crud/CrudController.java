package com.example.testTask.controllers.crud;

import com.example.testTask.Connector;
import com.example.testTask.models.*;

import java.sql.*;

public class CrudController {
    public static ResultSet getTable(String tableName) {
        String getEmail = "SELECT * FROM " + tableName;
        ResultSet resultSet = null;
        try {
            PreparedStatement prSt = Connector.getConnection().prepareStatement(getEmail);
            resultSet = prSt.executeQuery();
        } catch (Exception e) {
            System.out.println("Ошибка GetTable");
            System.out.println(e.getMessage());
        }
        return resultSet;
    }



}
