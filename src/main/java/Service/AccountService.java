package Service;

import java.sql.SQLException;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    private AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();
    }

    public Account registerAccount(Account newAccount)throws SQLException{
        return this.accountDAO.registerAccount(newAccount);
    }

    public Account login(Account account)throws SQLException{
        return this.accountDAO.login(account);
    } 
}
