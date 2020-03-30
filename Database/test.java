import java.sql.*;  
class MysqlCon{  
        public static void main(String args[]){  
        try{  
        
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ip","root","ip20");   //connection to the DB
                //random entry that simulate user entry
                String mdp = "boubou";                  //mdp = password in french my bad
                String name = "bertrand";
                String email = "flemme";
                String country = "UK";
                
                //simple hashin algorithm (not really hashing but...)
                int hash = 31;
                for(int i =0;i<mdp.length();i++){
                        hash = hash*7 + mdp.charAt(i);
                }
                System.out.println(hash);  

                //typical insert query
                PreparedStatement  query = con.prepareStatement("INSERT INTO USERS(name, email, country) VALUES(?,?,?)");
                query.setString (1, name);
                query.setString (2, email);
                query.setString (3, country);
                query.executeUpdate();  

                //typical select query
                query = con.prepareStatement("select DISTINCT ID from USERS WHERE name = ?" ); 
                query.setString (1, name);
                rs = query.executeQuery();  
                rs.first();
                System.out.println(rs.getInt(1));  

                query = con.prepareStatement("INSERT INTO PSW(value, user_id) VALUES(?,?)");
                query.setInt (1, hash);
                query.setInt (2, rs.getInt(1));
                query.executeUpdate();  

                System.out.println(allow_connection(name, mdp));
                }
                catch(Exception e){ 
                        System.out.println(e);
                } 
        }  

        public static boolean allow_connection(String username, String password){  //standalone function to check if anyone is connected
                int hash = 31;
                for(int i =0;i<password.length();i++){
                        hash = hash*7 + password.charAt(i);
                }
                try{
                        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ip","root","ip20");  
                        PreparedStatement stmt=con.prepareStatement("select DISTINCT ID from USERS where name = ?"); 
                        stmt.setString(1,username);
                        ResultSet rs=stmt.executeQuery();
                        rs.first();
                        int user_id = rs.getInt(1);
                        
                        stmt=con.prepareStatement("select DISTINCT value from PSW where user_id = ?"); 
                        stmt.setInt(1,user_id);
                        rs=stmt.executeQuery();
                        rs.first();
                        int hashedpsw = rs.getInt(1);
                        if(hashedpsw == hash){
                                return true;
                        }else{
                                return false;
                        }
                }
                catch(Exception e){ 
                        System.out.println(e);
                        return false;
                } 
        }

}  