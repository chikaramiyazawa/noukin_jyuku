package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import Bean.UserBean;

public class UserDAO extends DataAccessObject{

    //登録処理
    public void create (UserBean userBean){

        update(createSQLForCreate(), new Object[]{
                userBean.getUser_ID() ,
                userBean.getUser_NAME(),
                userBean.getUser_Pass(),
                userBean.getAdmin(),
                userBean.getUpdate(),
                userBean.getTuition(),
                userBean.getDelete_flg()
        });
    }


    //更新処理
    public void update(UserBean userBean){

        update(createSQLForUpdate(), new Object[] {

                userBean.getUser_ID(),
                userBean.getUser_NAME(),
                userBean.getUser_Pass(),
                userBean.getAdmin(),
                userBean.getUpdate(),
                userBean.getTuition(),
                userBean.getDelete_flg(),

                //getUpColumn用
                userBean.getUser_ID()

        });

    }


    //主キー検索
    public UserBean findByPrimaryKey(java.lang.Integer  userno){
        return (UserBean) query (createSQLForFindByPK() ,

                new Object[] {userno} , UserBean.class);
    }

    //主キーを取得
    @Override
    public String[] getPkColumns(){
        return new String[] {"USER_ID" ,"USER_NAME","ADMIN"};
    }

    //テーブル作成 INSERT用
    @Override
    public String[] getInsert(){
    	return new String[]{
    			"USER_ID   " ,
                "USER_NAME   " ,
                "USER_PASS   " ,
                "ADMIN  " ,
                "UP  " ,
                "TUITION  " ,
                "DELETE_FLG " ,


    	};
    }

    //カラム名を取得 UPDATE句用
    @Override
    public String[] getColumns(){
        return new String[]{
                "USER_ID = ? , " ,
                "USER_NAME = ? , " ,
                "USER_PASS = ? , " ,
                "ADMIN = ? , " ,
                "UP = ? , " ,
                "TUITION = ? ," ,
                "DELETE_FLG = ?" ,
        };
    }

    @Override
    public String getUpColumn(){
 	   return  " USER_ID = ?";

    }


    //テーブル名を取得
    @Override
    public String getTableName(){
        return "usertable";
    }

    private static final String SQL_GetDate =
            "SELECT" +
            " * " +
            "  FROM  " +
            " usertable ";


    private static final String SQL_admin_koushi =

            "SELECT" +
            " * " +
            " FROM " +
            " usertable " +
            " WHERE " +
            " ADMIN " +
            " = " +
            " 2 ";

    private static final String SQL_FIND_BY_USER_ID =
            "SELECT" +
            " * " +
            " FROM " +
            " usertable " +
            " WHERE " +
            " USER_ID = ? ";


    //ユーザーIDと一致する行数
    private static final String SQL_GET_COUNT_BY_USER_ID =
            "SELECT " +
            " COUNT(*) "+
            " FROM " +
            " usertable "+
            " WHERE " +
            " USER_ID = ?";

    //各ユーザーの更新日
    private static final String SQL_GET_UPDATE =
            "SELECT " +
            " UPDATE " +
            " FROM " +
            " usertable "+
            " UP = ?";

    //USER表　ユーザーID
    public int getCountByID(String id){
        int num = -1;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            stmt = getConnection().prepareStatement(SQL_GET_COUNT_BY_USER_ID);
            System.out.println(SQL_GET_COUNT_BY_USER_ID);
            System.out.println("id+" + id);
            fillPreParedStatement(stmt, 1, id);
            rs = stmt.executeQuery();
            if(rs.next()){
                num = rs.getInt("count(*)");
            }else{
                System.out.println("カウントが正しく取得できていません");
            }
        }catch(SQLException e){
            e.getMessage();
            e.printStackTrace();
        }finally{
            if(stmt != null){
                try{
                    stmt.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }


        return num;

    }

    public static List<UserBean>FindAll(){
        ConnectionManager.beginTransaction();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<UserBean> userBean = new ArrayList<UserBean>();

        try{
            stmt = getConnection().prepareStatement(SQL_GetDate);
            System.out.println(SQL_GetDate);
            rs = stmt.executeQuery();
            while(rs.next()){
                UserBean userBean2 = new UserBean();
                userBean2.setUser_ID(rs.getString("USER_ID"));
                userBean2.setUser_NAME(rs.getString("USER_NAME"));
                userBean2.setUser_Pass(rs.getString("USER_PASS"));
                userBean2.setAdmin(rs.getInt("ADMIN"));
                userBean2.setTuition(rs.getLong("TUITION"));
                userBean2.setDelete_flg(rs.getInt("DELETE_FLG"));
                userBean2.setUpdate(rs.getTimestamp("UP"));

                userBean.add(userBean2);
            }
        }catch(SQLException e){
            e.getMessage();
            e.printStackTrace();
        }finally{
            if(stmt != null){
                try{
                    stmt.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return userBean;
    }





    public static List<UserBean>FindKoushi(){
        ConnectionManager.beginTransaction();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<UserBean> userBean = new ArrayList<UserBean>();

        try{
            stmt = getConnection().prepareStatement(SQL_admin_koushi);
            System.out.println(SQL_admin_koushi);
            rs = stmt.executeQuery();
            while(rs.next()){
                UserBean userBean2 = new UserBean();
                userBean2.setUser_ID(rs.getString("USER_ID"));
                userBean2.setUser_NAME(rs.getString("USER_NAME"));
                userBean2.setUser_Pass(rs.getString("USER_PASS"));
                userBean2.setAdmin(rs.getInt("ADMIN"));
                userBean2.setTuition(rs.getLong("TUITION"));
                userBean2.setDelete_flg(rs.getInt("DELETE_FLG"));
                userBean2.setUpdate(rs.getTimestamp("UP"));

                userBean.add(userBean2);
            }
        }catch(SQLException e){
            e.getMessage();
            e.printStackTrace();
        }finally{
            if(stmt != null){
                try{
                    stmt.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return userBean;
    }

    //PKの検索結果
    public UserBean findById(String id){

    	ConnectionManager.beginTransaction();

        UserBean userbean = new UserBean();

        PreparedStatement stmt = null;

        ResultSet rs = null;

        try{
            stmt = getConnection().prepareStatement( "SELECT" +
                    " * " +
                    " FROM " +
                    " usertable " +
                    " WHERE " +
                    " USER_ID  = " + "'"  + id + "'");
            System.out.println("SELECT" + " * " +" FROM " + " usertable " + " WHERE " + " USER_ID  = " + "'"  + id + "'");
           // fillPreParedStatement(stmt, 1, id);
            rs = stmt.executeQuery();
            if(rs.next()){

                userbean.setUser_ID(rs.getString("USER_ID"));
                userbean.setUser_NAME(rs.getString("USER_NAME"));
                userbean.setUser_Pass(rs.getString("USER_PASS"));
                userbean.setAdmin(rs.getInt("ADMIN"));
                userbean.setTuition(rs.getLong("TUITION"));
                userbean.setDelete_flg(rs.getInt("DELETE_FLG"));
                userbean.setUpdate(rs.getTimestamp("UP"));


            }
        }catch(SQLException e){
            e.getMessage();
            e.printStackTrace();
        }finally{
            if(stmt != null){
                try{
                    stmt.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return userbean;
    }

    //ログインセッション使ってないです
    public HttpSession getLogin(HttpSession session, String palamID, String palamPass){

        UserBean userBean = new UserBean();
        ConnectionManager.beginTransaction();
        Connection conn = ConnectionManager.getConnection();

        try{
            if(palamID.equals("")){
                userBean.setLogin("IDを記入してください");
                session.setAttribute("alartmessage",userBean);

            }else{
                userBean = findById(palamID);
                conn.commit();
                if(userBean.getUser_Pass().equals(palamPass)){
                    session.setAttribute("loginSession", userBean);
                }else{
                    System.out.println("パスワードが間違っています。");
                    userBean.setLogin("ユーザーIDかパスワードが間違っています");
                    session.setAttribute("alertmessage", userBean);
                }
            }
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }

        return session;
    }
}