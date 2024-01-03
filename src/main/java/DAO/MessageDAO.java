package DAO;

import java.util.ArrayList;
import java.util.List;
import Model.Message;
import Util.ConnectionUtil;
import java.sql.*;

public class MessageDAO {

    
    public Message postMessage(int posted_by, String message_text, long time_posted_epoch) throws SQLException{
        if(message_text != "" && message_text.length() <= 255){
            try{
                Connection connection = ConnectionUtil.getConnection();
            
                String sql = "INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES (?, ?, ?)";
                PreparedStatement ps = connection.prepareStatement(sql);

                ps.setInt(1, posted_by);
                ps.setString(2, message_text);
                ps.setLong(3, time_posted_epoch);

                ps.executeUpdate(); 

                String sql2 = "SELECT message_id FROM message WHERE posted_by = ? AND message_text = ? AND time_posted_epoch = ?";

                PreparedStatement ps2 = connection.prepareStatement(sql2);

                ps2.setInt(1, posted_by);
                ps2.setString(2, message_text);
                ps2.setLong(3, time_posted_epoch);

                ResultSet rs = ps2.executeQuery();
                
                while(rs.next()){
                    
                    int newMessageId = rs.getInt(1);
                    return new Message(
                                 newMessageId,
                                 posted_by,
                                 message_text,
                                 time_posted_epoch);
                }
            } catch(SQLException e){
                return null;
            }
            
        }
        return null;
    }

    public List<Message> getAllMessages() throws SQLException{
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<Message>();

        try{
            String sql = "SELECT * FROM message";

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Message message = new Message(
                    rs.getInt("message_id"),
                    rs.getInt("posted_by"),
                    rs.getString("message_text"),
                    rs.getLong("time_posted_epoch"));
                messages.add(message);
            }
            return messages;
        }catch(SQLException e){
            return messages;
        }
    }

    public Message getMessageById(int message_id) throws SQLException{
        Connection connection = ConnectionUtil.getConnection();

        try{
            String sql = "SELECT * FROM message WHERE message_id = ?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, message_id);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
            Message message = new Message(
                    rs.getInt("message_id"),
                    rs.getInt("posted_by"),
                    rs.getString("message_text"),
                    rs.getLong("time_posted_epoch"));
            return message;
            }
        }catch(SQLException e){
            return null;
        }
        return null;
    }

    public Message deleteMessageById(int message_id) throws SQLException{
        
        Message deletedMessage = this.getMessageById(message_id);
        
        if(deletedMessage != null){
            try{
                Connection connection = ConnectionUtil.getConnection();

                String sql = "DELETE FROM message WHERE message_id = ?";

                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, message_id);

                ps.executeUpdate();

                return deletedMessage;
            }catch(SQLException e){
                return null;
            }
        }

        return null;
    }

    public Message patchMessage(int message_id, String message_text)throws SQLException{
        
        
        
        if(message_text != "" && message_text.length() <= 255 && this.getMessageById(message_id) != null){
            try{
                Connection connection = ConnectionUtil.getConnection();

                String sql = "UPDATE message SET message_text = ? WHERE message_id = ?";

                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, message_text);
                ps.setInt(2, message_id);

                ps.executeUpdate();

                return this.getMessageById(message_id);
                
            }catch(SQLException e){
                return null;
            }
        }
        return null;
    }

    public List<Message> getMessagesFromAccount(int account_id)throws SQLException{
        List<Message> messages = new ArrayList<Message>();
        Connection connection = ConnectionUtil.getConnection();

        try{
            String sql = "SELECT * FROM message WHERE posted_by = ?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, account_id);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Message message = new Message(
                    rs.getInt("message_id"),
                    rs.getInt("posted_by"),
                    rs.getString("message_text"),
                    rs.getLong("time_posted_epoch"));
                messages.add(message);
            }
        }catch(SQLException e){
            return messages;
        }

        return messages;
    }
}