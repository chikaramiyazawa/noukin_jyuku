package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Bean.ContextBean;




public class ContextDAO extends DataAccessObject{

    //登録処理
    public void create  (ContextBean contextBean){

        update(createSQLForCreate(), new Object[]{

                contextBean.getGrade(),
                contextBean.getTarget(),



     });

 }
   public void  update (ContextBean contextBean){

       update(createSQLForCreate(), new Object[]{

               contextBean.getGrade(),
               contextBean.getTarget(),
   });
}

   //主キーを取得
   @Override
   public String[] getPkColumns(){
       return new String[] {"grade" ,"target"};
   }

   //テーブル作成 INSERT用
   @Override
   public String[] getInsert(){
   	return new String[]{
   		 "grade  ",
         "target ",

   	};
   }


   //カラム名を取得
   @Override
   public String[] getColumns(){
       return new String[]{
               "grade = ?",
               "target = ?",


       };
   }

   @Override
   public String getUpColumn(){
	   return  	"grade = ?";
   }

   // //テーブル名を取得
   @Override
   public String getTableName(){
       return "contexttable";
   }

   private static final String SQL_GetDate =
           "SELECT" +
           " * " +
           "  FROM  " +
           " contexttable "

           ;

   public static List<ContextBean>FindAll(){
       ConnectionManager.beginTransaction();
       PreparedStatement stmt = null;
       ResultSet rs = null;
       List<ContextBean> contextBean = new ArrayList<ContextBean>();

       try{
           stmt = getConnection().prepareStatement(SQL_GetDate);
           System.out.println(SQL_GetDate);
           rs = stmt.executeQuery();
           while(rs.next()){
               ContextBean contextBean2 = new ContextBean();

               contextBean2.setGrade(rs.getInt("GRADE"));
               contextBean2.setTarget(rs.getString("TARGET"));


               contextBean.add(contextBean2);
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
       return contextBean;
   }

}



