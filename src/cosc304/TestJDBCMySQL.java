package cosc304;

import java.sql.*;

public class TestJDBCMySQL
{   public static void main(String[] args)
    {   String url = "jdbc:mysql://cosc304.ok.ubc.ca/WorksOn";
        String uid = "mkurz";
        String pw = "33205121";              
        
        // Note: Using try-with-resources syntax from Java 7        
        try ( Connection con = DriverManager.getConnection(url, uid, pw);
              Statement stmt = con.createStatement();) 
        {
        	ResultSet rst = stmt.executeQuery("SELECT ename,salary FROM Emp");
            System.out.println("Employee Name,Salary");
            while (rst.next())
            {   System.out.println(rst.getString("ename")+","+rst.getDouble("salary"));
            }
        }
        catch (SQLException ex)
        {
            System.err.println("SQLException: " + ex);
        }
    }
}
