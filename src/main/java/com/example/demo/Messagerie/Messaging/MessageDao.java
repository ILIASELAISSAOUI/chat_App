package com.example.demo.Messagerie.Messaging;
import com.example.demo.Messagerie.Database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDao {

    public List<Message> getMessages(){
        String sql="SELECT * FROM messages";
        List <Message> messages=new ArrayList<>();
        DatabaseConnection connection=new DatabaseConnection();
        try(Connection conn=connection.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()){
            while (resultSet.next()) {
                long id_message=resultSet.getLong("message_id");
                long user_id=resultSet.getLong("user_id");
                long conversation_id=resultSet.getLong("conversation_id");
                String content=resultSet.getString("content");
                Timestamp timestamp=resultSet.getTimestamp("timestamp");
                String message_type=resultSet.getString("message_type");
                Message message=new Message(content,timestamp,message_type,user_id,conversation_id);
                messages.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }
}
