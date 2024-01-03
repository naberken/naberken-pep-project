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
        Message posted = this.messageDAO.postMessage(newMessage);

        if(posted == null){
            System.out.println("posted message is null");
            System.out.println(newMessage.getMessage_id());
        } else {
            System.out.println("posted Message_id");
            System.out.println(posted.getMessage_id());
        }
        return this.messageDAO.postMessage(newMessage);
    }

    public List<Message> getAllMessages() throws SQLException{

        List<Message> allMessages = this.messageDAO.getAllMessages();
        if(allMessages.size() == 0){
            System.out.println("all messages is empty");
        } else {
            System.out.println("all messages first id");
            System.out.println(allMessages.get(0).getMessage_id());
        }
        return this.messageDAO.getAllMessages();
    }

    public Message getMessageById(int message_id) throws SQLException{
        Message gottenMessage = this.messageDAO.getMessageById(message_id);
        if(gottenMessage == null){
            System.out.println("gotten message is null");
            System.out.println(message_id);
        } else {
            System.out.println("gotten message id");
            System.out.println(gottenMessage.getMessage_id());
        }
        return this.messageDAO.getMessageById(message_id);
    }

    public Message deleteMessageById(int message_id) throws SQLException{

        Message deletedMessage = this.messageDAO.deleteMessageById(message_id);
        if(deletedMessage == null){
            System.out.println("deleted message is null");
            System.out.println(message_id);
        } else {
            System.out.println("deleted message message id");
            System.out.println(deletedMessage.getMessage_id());
        }
        return this.messageDAO.deleteMessageById(message_id);
    }

    public Message patchMessage(int message_id, String messagesText) throws SQLException{
        Message patchedMessage = this.messageDAO.patchMessage(message_id, messagesText);
        if(patchedMessage == null){
            System.out.println("patched message is null");
            System.out.println(message_id);
            System.out.println(messagesText);
        } else {
            System.out.println("patched message id");
            System.out.println(patchedMessage.getMessage_id());
        }
        return this.messageDAO.patchMessage(message_id, messagesText);
    }

    public List<Message> getMessagesFromAccount(int account_id) throws SQLException{
        List<Message> messagesFromAccount = this.messageDAO.getMessagesFromAccount(account_id);
        if(messagesFromAccount.size() == 0){
            System.out.println("messages from account is empty");
            System.out.println(account_id);
        } else {
            System.out.println("messages from account first message id");
            System.out.println(messagesFromAccount.get(0).getMessage_id());
        }
        return this.messageDAO.getMessagesFromAccount(account_id);
    }
}
