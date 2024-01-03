package Service;

import java.sql.SQLException;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    private AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();
    }

    public Account registerAccount(String username, String password)throws SQLException{
        return this.accountDAO.registerAccount(username, password);
    }

    public Account login(String username, String password)throws SQLException{
        return this.accountDAO.login(username, password);
    } 
}
