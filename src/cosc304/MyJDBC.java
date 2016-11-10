package cosc304;

import java.sql.*;

public class MyJDBC {

	 public static void main(String[] args)
	    { String url = "jdbc:mysql://cosc304.ok.ubc.ca/WorksOn";
        String uid = "mkurz";
        String pw = "33205121";
        Connection con = null;
        try{
        con = DriverManager.getConnection(url,uid,pw);
        Statement stmt = con.createStatement();
        ResultSet rst = stmt.executeQuery("SELECT eno,ename FROM Emp"
        		+ " WHERE eno IN (SELECT supereno FROM Emp)"
        		+ " ORDER BY ename ASC");
        
        while(rst.next()){
        	System.out.println("Supervisor: " + rst.getString("ename"));
        	String sql = "SELECT ename, salary FROM Emp WHERE supereno = ? ORDER BY salary DESC";
        	PreparedStatement pstmt2 = con.prepareStatement(sql);
        	pstmt2.setString(1, rst.getString("eno"));
        	ResultSet rst2 = pstmt2.executeQuery();
        	while(rst2.next()){
        		System.out.println(rst2.getString("ename") +"," + rst2.getString("salary"));
        	}
        }
		 
	    }
        catch(SQLException er){
        	System.err.println(er);
        }
        finally{
        	try{
        		if(con != null){
        			con.close();
        		}
           	}
        	catch(SQLException er){
        		System.err.println(er);
        	}
        }
        }
}
