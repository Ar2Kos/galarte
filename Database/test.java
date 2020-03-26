import java.sql.*;  
class MysqlCon{  
        public static void main(String args[]){  
        try{  
        
        Connection con=DriverManager.getConnection(  
        "jdbc:mysql://localhost:3306/ip","root","ip20");   

        Statement stmt=con.createStatement();  
        ResultSet rs=stmt.executeQuery("select * from STYLE");  
        while(rs.next())  
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
                con.close();  
                }catch(Exception e){ System.out.println(e);}  
        }  
}  