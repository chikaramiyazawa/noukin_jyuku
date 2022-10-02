package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import Bean.ContextBean;
import Bean.CourseBean;
import Bean.CourseMeisaiBean;
import Bean.SearchBeans;
import Bean.StudyBean;
import Bean.SubjectBean;
import Bean.UserBean;


//授業検索
public class SearchDAO  extends ConnectionManager {

    static SearchDAO searchDAO;
    public PreparedStatement stmt = null;

    private String id_course;
    private String name_course;
    private int grade = 0;
    private int subject = 0;
    private int day_lecture = 0;
    private String teacher;
    private Timestamp start_lecture;






    public static SearchDAO getInstance(){
        if(searchDAO == null){
            searchDAO = new SearchDAO();
        }

        return  searchDAO;
    }

    public List<SearchBeans> SearchJyugyou (SearchBeans searchBeans){
        ConnectionManager.beginTransaction();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = null;

        List<SearchBeans> list = new ArrayList<SearchBeans>();

        List<String> palam = new ArrayList<String>();

        List<Object> value = new ArrayList<Object>();

        List<Integer> age = new ArrayList<Integer>();

        UserBean userBean = searchBeans.getUserBean();

        CourseBean courseBean = searchBeans.getCourseBean();

        CourseMeisaiBean courseMeisaiBean = searchBeans.getCourseMeisai();


      if(courseMeisaiBean .getCourse_ID() != null){
            id_course = courseMeisaiBean.getCourse_ID();
            palam.add("COURSEMEISAITABLE.COURSE_ID");
            value.add(id_course);


        }

        if(courseBean.getCourse_Name() != null){
            name_course = courseBean.getCourse_Name();
            palam.add("COURSETABLE.COURSE_NAME");
            value.add(name_course);

        }

        if(courseMeisaiBean.getGrade() != -1){
            grade = courseMeisaiBean.getGrade();
            palam.add("COURSEMEISAITABLE.GRADE");
            value.add(grade);
            age.add(grade);
        }

        if(courseMeisaiBean.getSubject() != -1){
            subject = courseMeisaiBean.getSubject();
            palam.add("COURSEMEISAITABLE.SUBJECT");
            value.add(subject);
        }

        if(courseMeisaiBean.getStart_Lecture() != null){
            start_lecture = courseMeisaiBean.getStart_Lecture();
            palam.add("COURSEMEISAITABLE.START_LECTURE");
            value.add(start_lecture);
        }

        if(courseMeisaiBean.getDay_Lecture() != -1){
            day_lecture = courseMeisaiBean.getDay_Lecture();
            palam.add("COURSEMEISAITABLE.DAY_LECTURE");
            value.add(day_lecture);

        }

        if(courseMeisaiBean.getTEACHER() != null){
            teacher = courseMeisaiBean.getTEACHER();
            palam.add("COURSEMEISAITABLE.TEACHER");
            value.add(teacher);
        }

        sql = createSQLForSearchJyugyou(palam, value ,age);

        System.out.print(sql);

        try{
            stmt = getConnection().prepareStatement(sql);

            for(int n = 0; n < value.size(); n++){
                stmt.setObject(n +  1 , value.get(n));
            }

            rs = stmt.executeQuery();

            while(rs.next()){

                SearchBeans searchBean = new SearchBeans();
                UserBean userBean2 = new UserBean();
                CourseBean courseBean2 = new CourseBean();
                CourseMeisaiBean courseMeisaiBean2 = new CourseMeisaiBean();
                SubjectBean subjectBean = new SubjectBean();
                ContextBean contextBean = new ContextBean();

                userBean.setUser_NAME(rs.getString("USER_NAME"));
                courseBean2.setCourse_ID(rs.getString("COURSE_ID"));
                courseBean2.setCourse_Name(rs.getString("COURSE_NAME"));
                courseBean2.setDetail(rs.getString("DETAIL"));

                courseMeisaiBean2.setGrade(rs.getInt("GRADE"));
                courseMeisaiBean2.setSubject(rs.getInt("SUBJECT"));
                courseMeisaiBean2.setStart_Lecture(rs.getTimestamp("START_LECTURE"));
                courseMeisaiBean2.setEnd_Lecture(rs.getTimestamp("END_LECTURE"));
                courseMeisaiBean2.setDay_Lecture(rs.getInt("DAY_LECTURE"));
                courseMeisaiBean2.setTuition(rs.getLong("TUITION"));
                subjectBean.setName(rs.getString("NAME"));
                courseMeisaiBean2.setTEACHER(rs.getString("TEACHER"));
                contextBean.setTarget(rs.getString("TARGET"));
                contextBean.setGrade(rs.getInt("GRADE"));

                courseBean2.setDelete_Flg(rs.getInt("DELETE_FLG"));
                courseMeisaiBean2.setDelete_Flg(rs.getInt("DELETE_FLG"));

                searchBean.setUserBean(userBean2);
                searchBean.setCourseBean(courseBean2);
                searchBean.setCourseMeisai(courseMeisaiBean2);
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

    public String createSQLForSearchJyugyou(List <String> palam ,List<Object>value,  List<Integer> age){

        UserDAO userDAO = new UserDAO();
        CourseDAO courseDAO = new CourseDAO();
        CourseMeisaiDAO courseMeisaiDAO = new CourseMeisaiDAO();
        SubjectDAO subjectDAO  = new SubjectDAO();
        ContextDAO contextDAO = new ContextDAO();

        StringBuffer sb = new StringBuffer();

        sb.append(" select ");
        sb.append(" * ");
        sb.append(" from ");
        sb.append(courseMeisaiDAO.getTableName());
        sb.append(" inner join ");
        sb.append(courseDAO.getTableName());
        sb.append(" on ");
        sb.append(courseDAO.getTableName() + "." + courseDAO.getPkColumns()[0]);
        sb.append(" = ");
        sb.append(courseMeisaiDAO.getTableName() + "." + courseMeisaiDAO.getPkColumns()[0]);
        sb.append(" inner join ");
        sb.append(userDAO.getTableName());
        sb.append(" on ");
        sb.append(userDAO.getTableName() + "." + userDAO.getPkColumns()[1]);
        sb.append(" = ");
        sb.append(courseMeisaiDAO.getTableName() + "." + courseMeisaiDAO.getPkColumns()[1]);
        sb.append(" inner join ");
        sb.append(subjectDAO.getTableName());
        sb.append(" on ");
        sb.append(subjectDAO.getTableName() + "." + subjectDAO.getPkColumns()[0]);
        sb.append(" = ");
        sb.append(courseMeisaiDAO.getTableName() + "."  + courseMeisaiDAO.getPkColumns()[2]);
        sb.append(" inner join ");
        sb.append(contextDAO.getTableName());
        sb.append(" on ");
        sb.append(contextDAO.getTableName() + "." + contextDAO.getPkColumns()[0]);
        sb.append(" = ");
        sb.append(courseMeisaiDAO.getTableName() + "." + courseMeisaiDAO.getPkColumns()[3]);



        if(palam.size() != 0){
            sb.append(" where ");
            for(int n = 0; n < palam.size(); n++){
                //小学校　指定なしの検索
                if(palam.get(n) == "COURSEMEISAITABLE.GRADE" && age.get(0) == 10){
                    sb.append(palam.get(n));
                    sb.append(" > ");
                    sb.append(" ? ");
                    sb.append(" and ");
                    sb.append(" COURSEMEISAITABLE.GRADE ");
                    sb.append(" <= ");
                    sb.append(" 16 ");

                    if(n != (palam.size() -1)){
                        sb.append(" and ");
                    }
                }

                //中学校　指定なし
                else if(palam.get(n) == "COURSEMEISAITABLE.GRADE" && age.get(0) == 20){
                    sb.append(palam.get(n));
                    sb.append(" > ");
                    sb.append(" ? ");
                    sb.append(" and ");
                    sb.append(" COURSEMEISAITABLE.GRADE ");
                    sb.append(" <= ");
                    sb.append(" 23 ");

                    if(n != (palam.size() -1)){
                        sb.append(" and ");
                    }

                }

                //高校　指定なし
                else if(palam.get(n) == "COURSEMEISAITABLE.GRADE" && age.get(0) == 30){
                    sb.append(palam.get(n));
                    sb.append(" > ");
                    sb.append(" ? ");
                    sb.append(" and ");
                    sb.append(" COURSEMEISAITABLE.GRADE ");
                    sb.append(" <= ");
                    sb.append(" 33 ");

                    if(n != (palam.size() -1)){
                        sb.append(" and ");
                    }

                }

                //授業開始時間検索
                else if(palam.get(n) == "COURSEMEISAITABLE.START_LECTURE"){
                    sb.append(palam.get(n));
                    sb.append(" > ");
                    sb.append(" ? ");


                    if(n != palam.size() -1){
                        sb.append(" and ");
                    }
                }

                else{
                    sb.append(palam.get(n));
                    sb.append(" = ");
                    sb.append(" ? ");
                    if(n != (palam.size() -1)){
                        sb.append(" and ");
                    }
                }

            }

        }
                return sb.toString();

    }

    //履修登録されたコースを表示
    public List<SearchBeans> getCouseInfo (String user_id){
        ConnectionManager.beginTransaction();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = null;

        List<SearchBeans> list = new ArrayList<SearchBeans>();



        try{
            stmt = getConnection().prepareStatement("SELECT *  FROM "
            		+ " studytable  JOIN coursetable ON   "
            		+ " studytable.course_id  = coursetable.course_id   "
            		+ " JOIN  coursemeisaitable ON studytable.course_id  = coursemeisaitable.course_id  "
            		+ " WHERE studytable.user_id =  "
            		+  "'" + user_id + "'");

            System.out.println("SELECT *  FROM "
            		+ " studytable  JOIN coursetable ON   "
            		+ " studytable.course_id  = coursetable.course_id   "
            		+ " JOIN  coursemeisaitable ON studytable.course_id  = coursemeisaitable.course_id  "
            		+ " WHERE studytable.user_id =  "
            		+  "'" + user_id + "'");
            rs = stmt.executeQuery();
            while(rs.next()){

            	  SearchBeans searchBean = new SearchBeans();
            	  StudyBean studyBean2 = new StudyBean();
            	  CourseBean courseBean = new CourseBean();
            	  CourseMeisaiBean courseMeisaiBean = new CourseMeisaiBean();

            	  studyBean2.setUser_ID(rs.getString("USER_ID"));
            	  studyBean2.setStudyIng(rs.getInt("STUDYING"));
            	  courseBean.setCourse_ID(rs.getString("COURSE_ID"));
            	  courseBean.setCourse_Name(rs.getString("COURSE_NAME"));
            	  courseMeisaiBean.setTuition(rs.getLong("TUITION"));

            	  searchBean.setStudyBean(studyBean2);
            	  searchBean.setCourseBean(courseBean);
                  searchBean.setCourseMeisai(courseMeisaiBean);
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


