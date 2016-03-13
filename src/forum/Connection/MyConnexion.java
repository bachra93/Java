/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package forum.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mcd
 */
public class MyConnexion {
    private static Connection cnx;
    private String url="jdbc:mysql://localhost:3306/forum";
    private String user="root";
    private String pwd="";

    
    
    private MyConnexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MyConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            cnx=DriverManager.getConnection(url, user, pwd);
        } catch (SQLException ex) {
            Logger.getLogger(MyConnexion.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur"+ex.getMessage());
        }
    }
    
   public static Connection getInstance()
   {if (cnx==null) new MyConnexion();
   return cnx;
   }
    
}
