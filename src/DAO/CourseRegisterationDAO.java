package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Bean.ContextBean;
import Bean.CourseBean;
import Bean.CourseMeisaiBean;
import Bean.SearchBeans;
import Bean.SubjectBean;
import Bean.UserBean;


//コース全件取得用
public class CourseRegisterationDAO  extends ConnectionManager {


	private static final String SQL_GetDate =

			" SELECT *  FROM  coursetable JOIN "
			+ " coursemeisaitable ON "
			+ " coursetable.course_id = coursemeisaitable.course_id "
			+"  INNER JOIN usertable ON "
			+" usertable.USER_NAME = coursemeisaitable.teacher "
			+" INNER JOIN subjecttable ON "
			+" subjecttable.ID = coursemeisaitable.subject "
			+" INNER JOIN contexttable ON "
			+" contexttable.grade = coursemeisaitable.grade"  ;


	public static List<SearchBeans>FindAll(){
        ConnectionManager.beginTransaction();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<SearchBeans> list = new ArrayList<SearchBeans>();

        try{
            stmt = getConnection().prepareStatement(SQL_GetDate);
            System.out.println(SQL_GetDate);
            rs = stmt.executeQuery();
            while(rs.next()){

            	SearchBeans searchBean = new SearchBeans();
                UserBean userBean = new UserBean();
                CourseBean courseBean = new CourseBean();
                CourseMeisaiBean courseMeisaiBean = new CourseMeisaiBean();
                SubjectBean subjectBean = new SubjectBean();
                ContextBean contextBean = new ContextBean();

                userBean.setUser_NAME(rs.getString("USER_NAME"));
                courseBean.setCourse_ID(rs.getString("COURSE_ID"));
                courseBean.setCourse_Name(rs.getString("COURSE_NAME"));
                courseBean.setDetail(rs.getString("DETAIL"));
                courseBean.setDelete_Flg(rs.getInt("DELETE_FLG"));
                courseMeisaiBean.setGrade(rs.getInt("GRADE"));
                courseMeisaiBean.setSubject(rs.getInt("SUBJECT"));
                courseMeisaiBean.setStart_Lecture(rs.getTimestamp("START_LECTURE"));
                courseMeisaiBean.setEnd_Lecture(rs.getTimestamp("END_LECTURE"));
                courseMeisaiBean.setDay_Lecture(rs.getInt("DAY_LECTURE"));
                courseMeisaiBean.setTuition(rs.getLong("TUITION"));
                courseMeisaiBean.setDelete_Flg(rs.getInt("DELETE_FLG"));
                subjectBean.setName(rs.getString("NAME"));
                courseMeisaiBean.setTEACHER(rs.getString("TEACHER"));
                contextBean.setTarget(rs.getString("TARGET"));
                contextBean.setGrade(rs.getInt("GRADE"));

                searchBean.setUserBean(userBean);
                searchBean.setCourseBean(courseBean);
                searchBean.setCourseMeisai(courseMeisaiBean);
                searchBean.setSubjectBean(subjectBean);
                searchBean.setContextBean(contextBean);



                list.add(searchBean);

            }

            }catch(SQLException e){
                throw new RuntimeException(e);
            }finally{

                if(rs != null){
                    try{
                        stmt.close();

                    }catch(SQLException e){
                        throw new RuntimeException(e);
                    }
                }

                if(rs != null){
                    try{
                        rs.close();
                    }catch(SQLException e){
                        throw new RuntimeException(e);
                    }
                }
            }


            return list;

        }
	}

