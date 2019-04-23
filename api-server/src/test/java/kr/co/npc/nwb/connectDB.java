package kr.co.npc.nwb;


import java.sql.*;



import sun.misc.*;



public class connectDB {



    /**

     * @param args

     * @throws ClassNotFoundException 

     * @throws SQLException 

     */

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        String connectionUrl = "jdbc:mariadb://127.0.0.1:3306/NWB";//(db서버가 따로 존재한다면 로컬호스트:포트번호 대신 서버아이피:포트번호 를 입력하면된다.
        //gw db 테스트
        testDB(connectionUrl,"root","!0TU$npc");


    }

    public static void testDB(String connectionUrl, String id, String pw)
    {

        

        // Declare the JDBC objects.

        Connection con = null;

        Statement stmt = null;

        ResultSet rs = null;



        try {

            // Establish the connection.

            Class.forName("org.mariadb.jdbc.Driver");

            System.out.println("Driver okay");

            con = DriverManager.getConnection(connectionUrl,id,pw);

            System.out.println("Connection Made");

            con.close();

            System.out.println("Disconnected Done.");

        }

        // Handle any errors that may have occurred.

        catch (Exception e) {

            e.printStackTrace();

        }
    }
    
   
}


