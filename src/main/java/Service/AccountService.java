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
        Account registered = this.accountDAO.registerAccount(newAccount);
        if(registered == null){
            System.out.println("registered account is null");
            System.out.println(newAccount.getAccount_id());
        } else {
            System.out.println("registered Username");
            System.out.println(registered.getUsername());
        }
        return this.accountDAO.registerAccount(newAccount);
    }

    public Account login(Account account)throws SQLException{

        Account loggedIn = this.accountDAO.login(account);

        if(loggedIn == null){
            System.out.println("logged in account is null");
            System.out.println(account.getAccount_id());
        } else {
            System.out.println("logged in Username");
            System.out.println(loggedIn.getUsername());
        }
        return this.accountDAO.login(account);
    } 
}
