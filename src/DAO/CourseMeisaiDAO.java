package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Bean.CourseMeisaiBean;

public class CourseMeisaiDAO extends DataAccessObject{

    //登録処理
    public void create  (CourseMeisaiBean courseMeisaiBean){

        update(createSQLForCreate(), new Object[]{

                courseMeisaiBean.getCourse_ID(),
                courseMeisaiBean.getGrade(),
                courseMeisaiBean.getSubject(),
                courseMeisaiBean.getTuition(),
                courseMeisaiBean.getStart_Lecture(),
                courseMeisaiBean.getEnd_Lecture(),
                courseMeisaiBean.getDay_Lecture(),
                courseMeisaiBean.getTEACHER(),
                courseMeisaiBean.getDelete_Flg(),


     });

 }
   public void  update (CourseMeisaiBean courseMeisaiBean){

       update(createSQLForUpdate(), new Object[]{



               courseMeisaiBean.getCourse_ID(),
               courseMeisaiBean.getGrade(),
               courseMeisaiBean.getSubject(),
               courseMeisaiBean.getTuition(),
               courseMeisaiBean.getStart_Lecture(),
               courseMeisaiBean.getEnd_Lecture(),
               courseMeisaiBean.getDay_Lecture(),
               courseMeisaiBean.getTEACHER(),
               courseMeisaiBean.getDelete_Flg(),

               //getUpColumn用
    		   courseMeisaiBean.getCourse_ID(),
   });
}

   //主キーを取得
   @Override
   public String[] getPkColumns(){
       return new String[] {"course_id","teacher" ,"subject","grade"};
   }

 //テーブル作成 INSERT用
   @Override
   public String[] getInsert(){
   	return new String[]{
   	        "course_id ",
            "grade  " ,
            "subject   ",
            "tuition " ,
            "start_lecture " ,
            "end_lecture  " ,
            "day_lecture  " ,
            "teacher " ,
            "delete_flg "


   	};

   }

   //カラム名を取得 UPDATE句用
   @Override
   public String[] getColumns(){
       return new String[]{
               "course_id = ? , ",
               "grade = ? , " ,
               "subject = ? , ",
               "tuition = ? ," ,
               "start_lecture = ? ," ,
               "end_lecture = ? ," ,
               "day_lecture = ? ," ,
               "teacher = ? ," ,
               "delete_flg = ?"

       };
   }

   @Override
   public String getUpColumn(){
	   return  "course_id  =?";

   }

   // //テーブル名を取得
   @Override
   public String getTableName(){
       return "coursemeisaitable";
   }

   private static final String SQL_GetDate =
           "SELECT" +
           " * " +
           "  FROM  " +
           " coursemeisaitable " +
           " WHETE " +
           " delete_flg  " +
           " = " +
           " 0 "
           ;

   public static List<CourseMeisaiBean>FindAll(){
       ConnectionManager.beginTransaction();
       PreparedStatement stmt = null;
       ResultSet rs = null;
       List<CourseMeisaiBean> courseMeisaiBean = new ArrayList<CourseMeisaiBean>();

       try{
           stmt = getConnection().prepareStatement(SQL_GetDate);
           System.out.println(SQL_GetDate);
           rs = stmt.executeQuery();
           while(rs.next()){
               CourseMeisaiBean courseMeisaiBean2 = new CourseMeisaiBean();

               courseMeisaiBean2.setCourse_ID(rs.getString("course_id"));
               courseMeisaiBean2.setGrade(rs.getInt("grade"));
               courseMeisaiBean2.setSubject(rs.getInt("subject"));
               courseMeisaiBean2.setTuition(rs.getLong("tuition"));
               courseMeisaiBean2.setStart_Lecture(rs.getTimestamp("start_lecture"));
               courseMeisaiBean2.setEnd_Lecture(rs.getTimestamp("end_lecture"));
               courseMeisaiBean2.setDay_Lecture(rs.getInt("day_lecture"));
               courseMeisaiBean2.setTEACHER(rs.getString("teacher"));
               courseMeisaiBean2.setDelete_Flg(rs.getInt("delete_flg"));

               courseMeisaiBean.add(courseMeisaiBean2);
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
       return courseMeisaiBean;
   }

   public CourseMeisaiBean findById(String id){

   	ConnectionManager.beginTransaction();

   	CourseMeisaiBean courseMeisaiBean = new CourseMeisaiBean();

       PreparedStatement stmt = null;

       ResultSet rs = null;

       try{
           stmt = getConnection().prepareStatement( "SELECT" +
                   " * " +
                   " FROM " +
                   " coursemeisaitable " +
                   " WHERE " +
                   " COURSE_ID  = " + "'"  + id + "'");
           System.out.println("SELECT" + " * " +" FROM " + " coursemeisaitable " + " WHERE " + " COURSE_ID  = " + "'"  + id + "'");
          // fillPreParedStatement(stmt, 1, id);
           rs = stmt.executeQuery();
           if(rs.next()){

               courseMeisaiBean.setCourse_ID(rs.getString("course_id"));
               courseMeisaiBean.setGrade(rs.getInt("grade"));
               courseMeisaiBean.setSubject(rs.getInt("subject"));
               courseMeisaiBean.setTuition(rs.getLong("tuition"));
               courseMeisaiBean.setStart_Lecture(rs.getTimestamp("start_lecture"));
               courseMeisaiBean.setEnd_Lecture(rs.getTimestamp("end_lecture"));
               courseMeisaiBean.setDay_Lecture(rs.getInt("day_lecture"));
               courseMeisaiBean.setTEACHER(rs.getString("teacher"));
               courseMeisaiBean.setDelete_Flg(rs.getInt("delete_flg"));


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
       return courseMeisaiBean;
   }






}


