package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Bean.StudyBean;
import Bean.UserBean;

public class StudyDAO extends DataAccessObject{

	//登録処理
    public void create (StudyBean studybean){

    	 update(createSQLForCreate(), new Object[]{

    	    studybean.getUser_ID(),
    		studybean.getCourse_ID(),
    		studybean.getStudyIng()


    	 });
    }


  //更新処理
    public void update (StudyBean studybean){

    	update(createSQLForUpdate(), new Object[] {

    		studybean.getUser_ID(),
    		studybean.getCourse_ID(),
    		studybean.getStudyIng(),

    		 //getUpColumn用
    		studybean.getCourse_ID()

    	});

    }


    //主キーを取得
    @Override
    public String[] getPkColumns(){
        return new String[] {"USER_ID" ,"COURSE_ID","STUDYING"};
    }

    //テーブル作成 INSERT用
    @Override
    public String[] getInsert(){
    	return new String[]{

    	" USER_ID   "  ,
    	" COURSE_ID " ,
    	" STUDYING  " ,

    	};
    }

    //カラム名を取得 UPDATE用
    @Override
    public String[] getColumns(){
        return new String[]{

            " USER_ID = ?  ,"  ,
            " COURSE_ID = ? ," ,
            " STUDYING = ? " ,

        };
    }

    @Override
    public String getUpColumn(){
    	return " COURSE_ID = ? " ;

    }

  //テーブル名を取得
    @Override
    public String getTableName(){
        return  "studytable" ;
    }

    private static final String SQL_GetDate =
            "SELECT" +
            " * " +
            "  FROM  " +
            "  studytable ";

    private static final String SQL_FIND_BY_USER_ID =
            "SELECT" +
            " * " +
            " FROM " +
            " studytable " +
            " WHERE " +
            " USER_ID = ? ";

    //ユーザーIDと一致する行数
    private static final String SQL_GET_COUNT_BY_STUDY =
            "SELECT " +
            " COUNT(*) "+
            " FROM " +
            " studytable "+
            " WHERE " +
            " USER_ID = ? " +
            "  AND " +
            " COURSE_ID = ?" ;

    public static List<StudyBean> FindAll(){
    	ConnectionManager.beginTransaction();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<StudyBean> studybean = new ArrayList<StudyBean>();

        try{
            stmt = getConnection().prepareStatement(SQL_GetDate);
            System.out.println(SQL_GetDate);
            rs = stmt.executeQuery();
            while(rs.next()){

            	StudyBean studybean2 = new StudyBean();
            	studybean2.setUser_ID(rs.getString("USER_ID"));
            	studybean2.setCourse_ID(rs.getString("COURSE_ID"));
            	studybean2.setStudyIng(rs.getInt("STUDYING"));

            	studybean.add(studybean2);

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

        return studybean;


    }


    //USER表　ユーザーID
    public static int getCountByID(String id, String course){
    	ConnectionManager.beginTransaction();
        UserBean userbean = new UserBean();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int num = 0;

        try{
            stmt = getConnection().prepareStatement( "SELECT" +
                    " COUNT(*) " +
                    " FROM " +
                    " studytable " +
                    " WHERE " +
                    " USER_ID  = " + "'"  + id + "'" +
                    " AND " +
                    " COURSE_ID = " + "'" + course + "'"

            		);
            rs = stmt.executeQuery();
            if(rs.next()){
            	num = rs.getInt("count(*)");
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

    //PKの検索結果
    public StudyBean findById(String id){

    	ConnectionManager.beginTransaction();

        StudyBean studybean = new StudyBean();

        PreparedStatement stmt = null;

        ResultSet rs = null;

        try{
            stmt = getConnection().prepareStatement( "SELECT" +
                    " * " +
                    " FROM " +
                    " studytable " +
                    " WHERE " +
                    " USER_ID  = " + "'"  + id + "'");
            System.out.println("SELECT" + " * " +" FROM " + " studytable " + " WHERE " + " USER_ID  = " + "'"  + id + "'");
           // fillPreParedStatement(stmt, 1, id);
            rs = stmt.executeQuery();
            if(rs.next()){
                studybean.setUser_ID(rs.getString("USER_ID"));
                studybean.setCourse_ID(rs.getString("COURSE_ID"));
                studybean.setStudyIng(rs.getInt("STUDYING"));


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
        return studybean;
    }




}
