package Controller;

import java.sql.SQLException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {

    AccountService accountService;
    MessageService messageService;

    public SocialMediaController(){
        this.accountService = new AccountService();
        this.messageService = new MessageService();
    }

    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.get("example-endpoint", this::exampleHandler);
        app.post("/register", this::registerHandler);
        app.post("/login", this::loginHandler);
        app.post("/messages", this::postMessageHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{message_id}", this::getMessageByIdHandler);
        app.delete("/messages/{message_id}", this::deleteMessageHandler);
        app.patch("/messages/{message_id}", this::patchMessageHandler);
        app.get("/accounts/{account_id}/messages", this::getAllMessagesFromUserHandler);


        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }

    private void registerHandler(Context context) throws JsonProcessingException, SQLException{
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(), Account.class);
        Account registeredAccount = accountService.registerAccount(account);
        if(registeredAccount != null){
            context.json(mapper.writeValueAsString(registeredAccount));
        } else {
            context.status(400);
        }
    }

    private void loginHandler(Context context) throws JsonProcessingException, SQLException{
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(), Account.class);
        Account loggedInAccount = accountService.login(account);
        if(loggedInAccount != null){
            context.json(mapper.writeValueAsString(loggedInAccount));
        } else {
            context.status(401);
        }
    }

    private void postMessageHandler(Context context) throws JsonProcessingException, SQLException{
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(context.body(), Message.class);
        Message postedMessage = messageService.postMessage(message);
        if(postedMessage != null) {
            context.json(mapper.writeValueAsString(postedMessage));
        } else {
            context.status(400);
        }
    }

    private void getAllMessagesHandler(Context context)throws SQLException{
        List<Message> messages = messageService.getAllMessages();
        context.json(messages);
    }

    private void getMessageByIdHandler(Context context)throws SQLException{
        Message message = messageService.getMessageById(context.pathParam("message_id"));
        context.json(message.toString());
    }

    private void deleteMessageHandler(Context context)throws SQLException{
        Message message = messageService.deleteMessageById(context.pathParam("message_id"));
        if(message != null){
             context.json(message.toString());
        } else {
            context.json("");
        }
    }

    private void patchMessageHandler(Context context) throws JsonProcessingException , SQLException{ 
        ObjectMapper mapper = new ObjectMapper();
        Message message_text = mapper.readValue(context.body(), Message.class);
        Message updatedMessage = messageService.patchMessage(context.pathParam("message_id"), message_text);
        if(updatedMessage != null){
            context.json(mapper.writeValueAsString(updatedMessage));
        } else {
            context.status(400);
        }
    }

    private void getAllMessagesFromUserHandler(Context context)throws SQLException{
        List<Message> usersMessages = messageService.getMessagesFromAccount(context.pathParam("account_id"));
        context.json(usersMessages);
    }


}