
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class logIn_class {
conn con = new conn();
    String fname = "";
    
    public int login(String username, String password){
        int x = 0;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection(con.url,con.username,con.password);
            
            String sql = "SELECT * FROM register WHERE username = ? AND password = MD5(?);";
            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
            
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next()){
                fname = rs.getString("name");
                x = 1;
            }else{
                x = 0;
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(logIn_class.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(logIn_class.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return x;
    }
    
    
}
