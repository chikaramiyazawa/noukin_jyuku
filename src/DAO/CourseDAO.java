package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Bean.CourseBean;




public class CourseDAO  extends DataAccessObject{

    //登録処理
    public void create  (CourseBean courseBean){

        update(createSQLForCreate(), new Object[]{

                courseBean.getCourse_ID(),
                courseBean.getCourse_Name(),
                courseBean.getDetail(),
                courseBean.getCreated(),
                courseBean.getDelete_Flg(),


     });

 }
   public void  update (CourseBean courseBean){

       update(createSQLForUpdate(), new Object[]{


       courseBean.getCourse_ID(),
       courseBean.getCourse_Name(),
       courseBean.getDetail(),
       courseBean.getCreated(),
       courseBean.getDelete_Flg(),

     //getUpColumn用
       courseBean.getCourse_ID(),

   });
}

   //主キーを取得
   @Override
   public String[] getPkColumns(){
       return new String[] {"course_id" ,"course_name"};
   }

   //テーブル作成 INSERT用
   @Override
   public String[] getInsert(){
   	return new String[]{
   			"course_id   " ,
            "course_name  " ,
            "detail   " ,
            "created   " ,
            "delete_flg  " ,

   	   };

   }

   //カラム名を取得  UPDATE句用
   @Override
   public String[] getColumns(){
       return new String[]{
               "course_id = ? , " ,
               "course_name = ? ," ,
               "detail = ? , " ,
               "created = ? , " ,
               "delete_flg = ? " ,

       };
   }

   @Override
   public String getUpColumn(){
	   return "course_id =?";

   }

   // //テーブル名を取得
   @Override
   public String getTableName(){
       return "coursetable";
   }

   private static final String SQL_GetDate =
           "SELECT" +
           " * " +
           "  FROM  " +
           " coursetable " +
           " WHETE " +
           " delete_flg  " +
           " = " +
           " 0 "
           ;



   public static List<CourseBean>FindAll(){
       ConnectionManager.beginTransaction();
       PreparedStatement stmt = null;
       ResultSet rs = null;
       List<CourseBean> courseBean = new ArrayList<CourseBean>();

       try{
           stmt = getConnection().prepareStatement(SQL_GetDate);
           System.out.println(SQL_GetDate);
           rs = stmt.executeQuery();
           while(rs.next()){
               CourseBean courseBean2 = new CourseBean();

               courseBean2.setCourse_ID(rs.getString("course_id"));
               courseBean2.setCourse_Name(rs.getString("course_name"));
               courseBean2.setDetail(rs.getString("detail"));
               courseBean2.setCreated(rs.getTimestamp("created"));
               courseBean2.setDelete_Flg(rs.getInt("delete_flg"));

               courseBean.add(courseBean2);
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
       return courseBean;
   }

   public CourseBean findById(String id){

	   ConnectionManager.beginTransaction();

	   CourseBean courseBean = new CourseBean();

	   PreparedStatement stmt = null;

	   ResultSet rs = null;

	   try{

		   stmt = getConnection().prepareStatement("SELECT" + " * " +
		   " FROM " + " coursetable " + " WHERE " + " COURSE_ID =" + "'"
				    + id + "'");

		   System.out.println("SELECT" + " * " +
				   " FROM " + " coursetable " + " WHERE " + " COURSE_ID =" + "'"
				    + id + "'");

		   rs = stmt.executeQuery();
           if(rs.next()){

               courseBean.setCourse_ID(rs.getString("course_id"));
               courseBean.setCourse_Name(rs.getString("course_name"));
               courseBean.setDetail(rs.getString("detail"));
               courseBean.setCreated(rs.getTimestamp("created"));
               courseBean.setDelete_Flg(rs.getInt("delete_flg"));


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
	   return courseBean;

   }

}
