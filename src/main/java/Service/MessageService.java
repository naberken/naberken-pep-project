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

    public Message postMessage(Message newMessage) throws SQLException{
        return this.messageDAO.postMessage(newMessage);
    }

    public List<Message> getAllMessages() throws SQLException{
        return this.messageDAO.getAllMessages();
    }

    public Message getMessageById(String message_id) throws SQLException{
        return this.messageDAO.getMessageById(message_id);
    }

    public Message deleteMessageById(String message_id) throws SQLException{
        return this.messageDAO.deleteMessageById(message_id);
    }

    public Message patchMessage(String message_id, Message messagesText) throws SQLException{
        String message_text = messagesText.getMessage_text();
        return this.messageDAO.patchMessage(message_id, message_text);
    }

    public List<Message> getMessagesFromAccount(String account_id) throws SQLException{
        return this.messageDAO.getMessagesFromAccount(account_id);
    }
}
