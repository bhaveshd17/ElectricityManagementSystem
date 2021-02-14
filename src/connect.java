

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
public class connect{
    Connection c;
    Statement s;
    public connect(){
        try{  
            Class.forName("com.mysql.jdbc.Driver");  
            c =DriverManager.getConnection("jdbc:mysql://localhost:3306/ems","root","");    
            s =c.createStatement();
           
        }catch(Exception e){ 
            System.out.println(e);
        }  
    }
} 