package Service;

import java.sql.SQLException;
import java.util.List;

import DAO.MessageDAO;
import Model.Message;

public class MessageService {
    private MessageDAO messageDAO;

    public MessageService(){
        this.messageDAO = new MessageDAO();
    }

    public Message postMessage(int posted_by, String message_text, long time_posted_epoch) throws SQLException{
        
        return this.messageDAO.postMessage(posted_by, message_text, time_posted_epoch);
    }

    public List<Message> getAllMessages() throws SQLException{

        return this.messageDAO.getAllMessages();
    }

    public Message getMessageById(int message_id) throws SQLException{

        return this.messageDAO.getMessageById(message_id);
    }

    public Message deleteMessageById(int message_id) throws SQLException{

        return this.messageDAO.deleteMessageById(message_id);
    }

    public Message patchMessage(int message_id, String messagesText) throws SQLException{

        return this.messageDAO.patchMessage(message_id, messagesText);
    }

    public List<Message> getMessagesFromAccount(int account_id) throws SQLException{

        return this.messageDAO.getMessagesFromAccount(account_id);
    }
}
