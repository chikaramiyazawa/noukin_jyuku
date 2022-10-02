package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static final ThreadLocal<Connection> Connection =
            new ThreadLocal<Connection>();

            //JDBCドライバークラス
            private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";


            //データベースURL
            private static final String URL = "jdbc:mysql://localhost/noukin_jyuku?useSSL=false&useUnicode=true&characterEncoding=UTF-8";

            private static final String USER = "repuser";

            private static final String PASS = "reppass";


    static{
            try{
                Class.forName(DRIVER_CLASS);
            }catch(ClassNotFoundException e){
                throw new RuntimeException(e);
            }
      }

    public static void beginTransaction(){

        try{
            createConnection().setAutoCommit(false);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static void rollback(){

        Connection conn = getConnection();

        try{
            conn.rollback();
        }catch(SQLException e){
            throw new RuntimeException(e);

        }
       }

    public static void commit(){

        Connection conn = getConnection();


        try{
            conn.commit();
        }catch(SQLException e){
            throw new RuntimeException(e);

        }

    }




    public static void close() throws SQLException{

        Connection conn = getConnection();
        conn.close();
    }

    public static Connection getConnection(){
        return Connection.get();

    }

    //データベースコネクション
    private static Connection createConnection(){

        Connection result = Connection.get();

        try{
            if(result == null || result.isClosed()){
                result = DriverManager.getConnection(URL,USER,PASS);
                Connection.set(result);
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }

        return result;

    }






    }
