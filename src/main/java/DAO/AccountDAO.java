package DAO;

import Model.Account;
import Util.ConnectionUtil;
import java.sql.*;

public class AccountDAO {
    
    public Account registerAccount(Account newAccount) throws SQLException{
        Connection connection = ConnectionUtil.getConnection();

        try{
            String sql = "INSERT INTO account VALUES (?, ?)";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, newAccount.getUsername());
            ps.setString(2, newAccount.getPassword());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            while(rs.next()){
                int newAccountId = rs.getInt("account_id");
                return new Account(newAccountId, newAccount.getUsername(), newAccount.getPassword());
            }
        }catch(SQLException e){
            return null;
        }
        return null;
    }

    public Account login(Account account) throws SQLException{
        Connection connection = ConnectionUtil.getConnection();

        try{
            String sql = "SELECT * FROM account WHERE  username = ? AND password = ?";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, account.getUsername());
            ps.setString(2, account.getPassword());

            ps.executeQuery();

            return account;
            
        }catch(SQLException e){
            return null;
        }
    }
}