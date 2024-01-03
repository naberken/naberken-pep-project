package DAO;

import java.util.ArrayList;
import java.util.List;
import Model.Message;
import Util.ConnectionUtil;
import java.sql.*;

public class MessageDAO {

    
    public Message postMessage(Message newMessage) throws SQLException{
        if(newMessage.getMessage_text() != "" && newMessage.getMessage_text().length() <= 255){
            
            
            try{
                Connection connection = ConnectionUtil.getConnection();
            
                String sql = "INSERT INTO message VALUES (?, ?, ?)";
                PreparedStatement ps = connection.prepareStatement(sql);

                ps.setInt(1, newMessage.getPosted_by());
                ps.setString(2, newMessage.getMessage_text());
                ps.setLong(3, newMessage.getTime_posted_epoch());

                ps.executeUpdate();

                if(ps.getGeneratedKeys().next()){
                    int newMessageId = ps.getGeneratedKeys().getInt("message_id");
                    return new Message(newMessageId, newMessage.getPosted_by(), newMessage.getMessage_text(), newMessage.getTime_posted_epoch());
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

    public Message getMessageById(String message_id) throws SQLException{
        Connection connection = ConnectionUtil.getConnection();

        try{
            String sql = "SELECT * FROM message WHERE message_id = ?";

            PreparedStatement ps = (connection.prepareStatement(sql));
            ps.setInt(1, Integer.parseInt(message_id));

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
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

    public Message deleteMessageById(String message_id) throws SQLException{
        
        Message deletedMessage = this.getMessageById(message_id);
        
        if(deletedMessage != null){
            try{
                Connection connection = ConnectionUtil.getConnection();

                String sql = "DELETE FROM message WHERE message_id = ?";

                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(message_id));

                ps.executeUpdate();

                return deletedMessage;
            }catch(SQLException e){
                return null;
            }
        }

        return null;
    }

    public Message patchMessage(String message_id, String message_text)throws SQLException{
        Message updatedMessage = this.getMessageById(message_id);
        
        if(message_text != "" && message_text.length() <= 255 && updatedMessage != null){
            try{
                Connection connection = ConnectionUtil.getConnection();

                String sql = "UPDATE message SET message_text = ? WHERE message_id = ?";

                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, message_text);
                ps.setInt(2, Integer.parseInt(message_id));

                ps.executeUpdate();
                updatedMessage.setMessage_text(message_text);

                return updatedMessage;
            }catch(SQLException e){
                return null;
            }
        }

        return null;
    }

    public List<Message> getMessagesFromAccount(String account_id)throws SQLException{
        List<Message> messages = new ArrayList<Message>();
        Connection connection = ConnectionUtil.getConnection();

        try{
            String sql = "SELECT * FROM message WHERE posted_by = ?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(account_id));

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