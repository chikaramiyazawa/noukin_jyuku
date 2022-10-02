package Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.ContextBean;
import Bean.CourseBean;
import Bean.CourseMeisaiBean;
import Bean.SearchBeans;
import Bean.SubjectBean;
import Bean.UserBean;
import DAO.ConnectionManager;
import DAO.ContextDAO;
import DAO.CourseDAO;
import DAO.CourseMeisaiDAO;
import DAO.SearchDAO;
import DAO.SubjectDAO;
import DAO.UserDAO;
/**
 * Servlet implementation class CourseSearchServlet
 */
@WebServlet("/CourseSearchServlet")
public class CourseSearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html; charset=Windows-31J");

        request.setCharacterEncoding("windows-31J");

        HttpSession session = request.getSession();



        if(request.getParameter("search") == null && request.getParameter("create") == null && request.getParameter("update") == null){

            int target1 = 0;

            List<UserBean> user =  UserDAO.FindKoushi();

            try{

                ConnectionManager.close();

            }catch(SQLException e){
                e.printStackTrace();
            }

            session.setAttribute("user", user);

            List<SubjectBean> subject = SubjectDAO.FindAll();

            try{

                ConnectionManager.close();

            }catch(SQLException e){
                e.printStackTrace();
            }

            session.setAttribute("subject", subject);

            request.getRequestDispatcher("/WEB-INF/jsp/menu/courseSearch.jsp").forward(request, response);

        }


      if(request.getParameter("search") != null){

          List<SearchBeans> list = new ArrayList<SearchBeans>();

          SearchDAO searchDAO = new SearchDAO();

          SearchBeans searchBean = new SearchBeans();

          CourseBean courseBean = new CourseBean();

          CourseMeisaiBean courseMeisaiBean = new CourseMeisaiBean();

          int target1 = 0;

          int target2 = 0;

          try{
              target1 = (Integer.parseInt(request.getParameter("TARGET1")));

          }catch(NumberFormatException e){
              e.printStackTrace();
          }


          if(!request.getParameter("NAME_COURSE").equals("")){

              courseBean.setCourse_Name(request.getParameter("NAME_COURSE"));
          }

          int subject = 0;

          try{

              subject = (Integer.parseInt(request.getParameter("ID_SUBJECT")));
          }catch(NumberFormatException e){
              e.printStackTrace();
          }

          int day_lecture = 0;

          try{

              day_lecture = (Integer.parseInt(request.getParameter("DAY_LECTURE")));
          }catch(NumberFormatException e){
              e.printStackTrace();
          }


          if(target1 != 0){

              target1 = (Integer.parseInt(request.getParameter("TARGET1")));

              try{
                  target2 = (Integer.parseInt(request.getParameter("TARGET2")));

              }catch(NumberFormatException e){
                  e.printStackTrace();
              }

              int TARGET = target1 + target2;

              courseMeisaiBean.setGrade(TARGET);
          }else{

        	  courseMeisaiBean.setGrade(-1);

          }


          if(subject != 0){
              courseMeisaiBean.setSubject(subject);
          }else{

        	  courseMeisaiBean.setSubject(-1);
          }

          if(day_lecture != 0){

              courseMeisaiBean.setDay_Lecture(day_lecture);

          }else{

        	  courseMeisaiBean.setDay_Lecture(-1);

          }

          if(!request.getParameter("Year_Start").equals("") && !request.getParameter("Month_Start").equals("") &&
          		!request.getParameter("Day_Start").equals("") && !request.getParameter("Hour_Start").equals("") &&
          		!request.getParameter("Minute_Start").equals("")){

          	String str_Year_Start = request.getParameter("Year_Start");

          	String str_Month_Start = request.getParameter("Month_Start");

          	String str_Day_Start = request.getParameter("Day_Start");

          	String str_Hour_Start = request.getParameter("Hour_Start");

          	String str_Minute_Start = request.getParameter("Minute_Start");

             String str_start_lecture = str_Year_Start + "-" + str_Month_Start + "-"
              		+ str_Day_Start +  " " + str_Hour_Start + ":" + str_Minute_Start + ":" + "00" ;


              Timestamp timestamp;
              try {
                  timestamp = new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str_start_lecture).getTime());
                  courseMeisaiBean.setStart_Lecture(timestamp);
              } catch (ParseException e) {
                  // TODO 自動生成された catch ブロック
                  e.printStackTrace();
              }

          }

           if(!request.getParameter("TEACHER").equals("")){

                 courseMeisaiBean.setTEACHER(request.getParameter("TEACHER"));
           }

           searchBean.setCourseBean(courseBean);
           searchBean.setCourseMeisai(courseMeisaiBean);


           list = searchDAO.SearchJyugyou(searchBean);

           try{
               ConnectionManager.close();
           }catch(SQLException e){
               e.printStackTrace();
           }

           if(list.size() != 0){
               request.setAttribute("list", list);


           }

           List<UserBean> user =  UserDAO.FindKoushi();

           try{

               ConnectionManager.close();

           }catch(SQLException e){
               e.printStackTrace();
           }

           session.setAttribute("user", user);

           List<SubjectBean> subject2 = SubjectDAO.FindAll();

           try{

               ConnectionManager.close();

           }catch(SQLException e){
               e.printStackTrace();
           }

           session.setAttribute("subject", subject2);

           request.getRequestDispatcher("/WEB-INF/jsp/menu/courseSearch.jsp").forward(request, response);

        }



      if(request.getParameter("create") != null){

          List<UserBean> user =  UserDAO.FindKoushi();

          try{

              ConnectionManager.close();

          }catch(SQLException e){
              e.printStackTrace();
          }

          session.setAttribute("user", user);

          List<SubjectBean> subject = SubjectDAO.FindAll();

          try{

              ConnectionManager.close();

          }catch(SQLException e){
              e.printStackTrace();
          }

          session.setAttribute("subject", subject);

          request.getRequestDispatcher("/WEB-INF/jsp/menu/courseInsert.jsp").forward(request, response);

       }

      if(request.getParameter("update") != null){

    	  List<SearchBeans> list = new ArrayList<SearchBeans>();

    	  SearchDAO searchDAO = new SearchDAO();

          SearchBeans searchBean = new SearchBeans();

    	  CourseBean courseBean = new CourseBean();

          CourseMeisaiBean courseMeisaiBean = new CourseMeisaiBean();


          courseBean.setCourse_ID(request.getParameter("update"));

          courseMeisaiBean.setCourse_ID(request.getParameter("update"));

          courseMeisaiBean.setTuition(-1);

          courseMeisaiBean.setGrade(-1);

          courseMeisaiBean.setSubject(-1);

          courseMeisaiBean.setDay_Lecture(-1);

          searchBean.setCourseBean(courseBean);
          searchBean.setCourseMeisai(courseMeisaiBean);

          list = searchDAO.SearchJyugyou(searchBean);



          try{
              ConnectionManager.close();
          }catch(SQLException e){
              e.printStackTrace();
          }

          if(list.size() != 0){
              request.setAttribute("list", list);


          }

          List<UserBean> user =  UserDAO.FindKoushi();

          try{

              ConnectionManager.close();

          }catch(SQLException e){
              e.printStackTrace();
          }

          session.setAttribute("user", user);

          List<SubjectBean> subject2 = SubjectDAO.FindAll();

          try{

              ConnectionManager.close();

          }catch(SQLException e){
              e.printStackTrace();
          }

          session.setAttribute("subject", subject2);


          List<ContextBean> context = ContextDAO.FindAll();

          try{

        	  ConnectionManager.close();

          }catch(SQLException e){
              e.printStackTrace();
          }

          session.setAttribute("context", context);

          request.getRequestDispatcher("/WEB-INF/jsp/menu/courseUpdate.jsp").forward(request, response);


      }

      if(request.getParameter("delete") != null){

    	  String course_id = request.getParameter("delete");


    	  CourseBean courseBean = new CourseBean();

    	  CourseMeisaiBean coursemeisaiBean = new CourseMeisaiBean();

    	  CourseDAO courseDAO = new CourseDAO();

		  CourseMeisaiDAO courseMeisaiDAO = new CourseMeisaiDAO();

		  CourseBean course = courseDAO.findById(course_id);

		  CourseMeisaiBean coursemeisai = courseMeisaiDAO.findById(course_id);

		  courseBean.setCourse_ID(course.getCourse_ID());
		  courseBean.setCourse_Name(course.getCourse_Name());
		  courseBean.setDetail(course.getDetail());
		  courseBean.setCreated(course.getCreated());
		  courseBean.setDelete_Flg(1);

		  coursemeisaiBean.setCourse_ID(coursemeisai.getCourse_ID());
		  coursemeisaiBean.setDay_Lecture(coursemeisai.getDay_Lecture());
		  coursemeisaiBean.setGrade(coursemeisai.getGrade());
		  coursemeisaiBean.setStart_Lecture(coursemeisai.getStart_Lecture());
		  coursemeisaiBean.setEnd_Lecture(coursemeisai.getEnd_Lecture());
		  coursemeisaiBean.setSubject(coursemeisai.getSubject());
		  coursemeisaiBean.setTEACHER(coursemeisai.getTEACHER());
		  coursemeisaiBean.setTuition(coursemeisai.getTuition());
		  coursemeisaiBean.setDelete_Flg(1);

		  ConnectionManager.beginTransaction();

          courseDAO.update(courseBean);

          ConnectionManager.commit();

          courseMeisaiDAO.update(coursemeisaiBean);

          ConnectionManager.commit();

          String alert = "削除しました";

          session.setAttribute("alert", alert);

          List<UserBean> user =  UserDAO.FindKoushi();

          try{

              ConnectionManager.close();

          }catch(SQLException e){
              e.printStackTrace();
          }

          session.setAttribute("user", user);

          List<SubjectBean> subject2 = SubjectDAO.FindAll();

          try{

              ConnectionManager.close();

          }catch(SQLException e){
              e.printStackTrace();
          }

          session.setAttribute("subject", subject2);


          List<ContextBean> context = ContextDAO.FindAll();

          try{

        	  ConnectionManager.close();

          }catch(SQLException e){
              e.printStackTrace();
          }

          session.setAttribute("context", context);


          request.getRequestDispatcher("/WEB-INF/jsp/menu/courseSearch.jsp").forward(request, response);

          return;



      }

      }



    }





