package com.example.demo.Messagerie.Users;

import com.example.demo.Messagerie.Database.DatabaseConnection;
import com.example.demo.Messagerie.Users.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*public class UserDao {
    public void getUsers(){
        String sql="SELECT * FROM users";
        List <User> users=new ArrayList<>();
        DatabaseConnection connection=new DatabaseConnection();
        try(Connection conn=connection.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()){
            while (resultSet.next()) {
                long user_id=resultSet.getLong("user_id");
                String username=resultSet.getString("username");
                String email=resultSet.getString("email");
                String password=resultSet.getString("password");
                User user=new User(user_id,username,email,password);
                users.add(user);
                System.out.println(user_id+" "+email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //return users;

    }
}*/
