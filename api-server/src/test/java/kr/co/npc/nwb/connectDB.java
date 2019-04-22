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

        String connectionUrl = "jdbc:sqlserver://10.206.186.22:1433;" + "databaseName=CrewworksV80_DAESUNG;";//(db서버가 따로 존재한다면 로컬호스트:포트번호 대신 서버아이피:포트번호 를 입력하면된다.

        //gw db 테스트
        testDB(connectionUrl,"dazone","crewworksv8daesung");

        connectionUrl = "jdbc:sqlserver://10.206.186.22:1433;" + "databaseName=ERP;";//(db서버가 따로 존재한다면 로컬호스트:포트번호 대신 서버아이피:포트번호 를 입력하면된다.
        //erp db 테스트        
        testDB(connectionUrl,"dserp","qwer@123");        
        connectionUrl = "jdbc:sqlserver://10.206.186.22:1433;" + "databaseName=QMS;";//(db서버가 따로 존재한다면 로컬호스트:포트번호 대신 서버아이피:포트번호 를 입력하면된다.
        //qms db 테스트        
        testDB(connectionUrl,"dserp","qwer@123");
        connectionUrl = "jdbc:sqlserver://10.206.186.22:1433;" + "databaseName=MMS;";//(db서버가 따로 존재한다면 로컬호스트:포트번호 대신 서버아이피:포트번호 를 입력하면된다.
        //mms db 테스트        
        testDB(connectionUrl,"dserp","qwer@123");


    }

    public static void testDB(String connectionUrl, String id, String pw)
    {

        

        // Declare the JDBC objects.

        Connection con = null;

        Statement stmt = null;

        ResultSet rs = null;



        try {

            // Establish the connection.

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

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


