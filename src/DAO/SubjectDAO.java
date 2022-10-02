package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Bean.SubjectBean;

public class SubjectDAO extends DataAccessObject{

    //登録処理
    public void create  (SubjectBean subjectBean){

        update(createSQLForCreate(), new Object[]{

                subjectBean.getID(),
                subjectBean.getName(),



     });

 }
   public void  update (SubjectBean subjectBean){

       update(createSQLForUpdate(), new Object[]{

               subjectBean.getID(),
               subjectBean.getName(),

   });
}

 //テーブル作成 INSERT用
   @Override
   public String[] getInsert(){
   	return new String[]{

   		  "ID",
          "NAME",


   	};

   }

   //主キーを取得
   @Override
   public String[] getPkColumns(){
       return new String[] {"ID" ,"NAME"};
   }

   //カラム名を取得
   @Override
   public String[] getColumns(){
       return new String[]{
               "ID = ?  , ",
               "NAME = ?  ,",


       };
   }

   @Override
   public String getUpColumn(){
	   return " ID = ? ";

   }

   // //テーブル名を取得
   @Override
   public String getTableName(){
       return "subjecttable";
   }

   private static final String SQL_GetDate =
           "SELECT" +
           " * " +
           "  FROM  " +
           " subjecttable "
           ;

   private static final String SQL_GETCOUNT =
		   "SELECT" +
           " COUNT(*) " +
		   " FROM " +
           " subjecttable "
		   ;

   public static List<SubjectBean>FindAll(){
       ConnectionManager.beginTransaction();
       PreparedStatement stmt = null;
       ResultSet rs = null;
       List<SubjectBean> subjectBean = new ArrayList<SubjectBean>();

       try{
           stmt = getConnection().prepareStatement(SQL_GetDate);
           System.out.println(SQL_GetDate);
           rs = stmt.executeQuery();
           while(rs.next()){
               SubjectBean subjectBean2 = new SubjectBean();

               subjectBean2.setID(rs.getInt("ID"));
               subjectBean2.setName(rs.getString("NAME"));


               subjectBean.add(subjectBean2);
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
       return subjectBean;
   }


public static int getCount(){
    int num = 0;
    PreparedStatement stmt = null;
    ResultSet rs = null;
        try{
        stmt = getConnection().prepareStatement(SQL_GETCOUNT);
        System.out.println(SQL_GETCOUNT);

      rs.last();
      num = rs.getRow();

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
}

