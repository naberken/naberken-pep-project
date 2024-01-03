package DAO;

import Model.Account;
import Util.ConnectionUtil;
import java.sql.*;

public class AccountDAO {
    
    public Account registerAccount(String username, String password) throws SQLException{
        Connection connection = ConnectionUtil.getConnection();
        if(username != "" && password.length() > 4){
            try{
                String sql = "INSERT INTO account (username, password) VALUES (?, ?)";
    
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);
    
                ps.executeUpdate();
    
                String sql2 = "SELECT account_id FROM account WHERE username = ?";
    
                PreparedStatement ps2 = connection.prepareStatement(sql2);
                ps2.setString(1, username);
    
                ResultSet rs = ps2.executeQuery();
                
                while(rs.next()){
                    int newAccountId = rs.getInt("account_id");
                    return new Account(newAccountId, username, password);
                }
            }catch(SQLException e){
                return null;
            }
        } 
        return null;
    }

    public Account login(String username, String password) throws SQLException{
        Connection connection = ConnectionUtil.getConnection();
        try{
            String sql = "SELECT * FROM account WHERE  username = ? AND password = ?";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                
                Account newAccount  = new Account(rs.getInt("account_id"), rs.getString("username"), rs.getString("password"));
                return newAccount;
            }
            
        }catch(SQLException e){
            return null;
        }
        return null;
    }
}