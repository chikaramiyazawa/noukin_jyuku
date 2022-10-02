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
import Bean.SubjectBean;
import Bean.UserBean;
import DAO.ConnectionManager;
import DAO.ContextDAO;
import DAO.CourseDAO;
import DAO.CourseMeisaiDAO;
import DAO.SubjectDAO;
import DAO.UserDAO;
import Validate.CourseMeisaiValidate;
import Validate.CourseValidate;

/**
 * Servlet implementation class CourseUpdateServlet
 */
@WebServlet("/CourseUpdateServlet")
public class CourseUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 HttpSession session = request.getSession();

	        request.setCharacterEncoding("UTF-8");

	        response.setContentType("text/html; charset=UTF-8");

	        session.removeAttribute("alert");

	        session.removeAttribute("message");

	        if(request.getParameter("insert") == null && request.getParameter("search") == null){


	        	 List<UserBean> userBean = UserDAO.FindKoushi();

	             try{
	                 ConnectionManager.close();
	             }catch(SQLException e){
	                 e.printStackTrace();
	             }

	             session.setAttribute("user", userBean);

	             List<SubjectBean> subjectBean = SubjectDAO.FindAll();


	             try{
	                 ConnectionManager.close();
	             }catch(SQLException e){
	                 e.printStackTrace();
	             }

	             session.setAttribute("subject",subjectBean);

	             request.getRequestDispatcher("/WEB-INF/jsp/menu/courseUpdate.jsp").forward(request, response);

	             return;

	        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub


        HttpSession session = request.getSession();

        request.setCharacterEncoding("UTF-8");

        response.setContentType("text/html; charset=UTF-8");

    	if(request.getParameter("update") != null){

            int tuition = 0;

            int target = 0;

            int subject = 0;

            int day_lecture = 0;


            try{
                target = (Integer.parseInt(request.getParameter("TARGET")));

            }catch(NumberFormatException e){
                e.printStackTrace();
            }

            try{
                tuition = (Integer.parseInt(request.getParameter("TUITION")));
            }catch(NumberFormatException e){
                e.printStackTrace();
            }

            try{

                subject = (Integer.parseInt(request.getParameter("SUBJECT")));
            }catch(NumberFormatException e){
                e.printStackTrace();
            }

            try{

                day_lecture = (Integer.parseInt(request.getParameter("DAY_LECTURE")));
            }catch(NumberFormatException e){
                e.printStackTrace();
            }






            //CourseBean格納用
            CourseBean courseBean = new CourseBean();

            //CourseMeisaiBean格納用
            CourseMeisaiBean courseMeisaiBean = new CourseMeisaiBean();

            courseBean.setCourse_ID(request.getParameter("COURSE_ID"));

            courseMeisaiBean.setCourse_ID(request.getParameter("COURSE_ID"));

            if(!request.getParameter("NAME_COURSE").equals("")){

                courseBean.setCourse_Name(request.getParameter("NAME_COURSE"));
            }

            if(!request.getParameter("DETAILS_COURSE").equals("")){
                courseBean.setDetail(request.getParameter("DETAILS_COURSE"));
            }


            if(tuition != 0){
                courseMeisaiBean.setTuition(tuition);
            }else{

                courseMeisaiBean.setTuition(-1);
            }

            if(target != 0){

                try{

                    courseMeisaiBean.setGrade(target);

                }catch(NumberFormatException e){
                    e.printStackTrace();

                    courseMeisaiBean.setGrade(-1);

                }


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

            if(!request.getParameter("Year_End").equals("") && !request.getParameter("Month_End").equals("") &&
            		!request.getParameter("Day_End").equals("") && !request.getParameter("Hour_End").equals("") &&
            		!request.getParameter("Minute_End").equals("")){

            	String str_Year_End = request.getParameter("Year_End");

            	String str_Month_End = request.getParameter("Month_End");

            	String str_Day_End = request.getParameter("Day_End");

            	String str_Hour_End = request.getParameter("Hour_End");

            	String str_Minute_End = request.getParameter("Minute_End");

                String str_end_lecture = str_Year_End + "-" + str_Month_End + "-" + str_Day_End +  " " + str_Hour_End + ":" + str_Minute_End + ":" + "00" ;

                Timestamp timestamp2;
                try{
                    timestamp2 = new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str_end_lecture).getTime());

                    courseMeisaiBean.setEnd_Lecture(timestamp2);

                }catch(ParseException e){

                    e.printStackTrace();

                    courseMeisaiBean.setEnd_Lecture(null);


                }

            }

            if(!request.getParameter("TEACHER").equals("")){

                courseMeisaiBean.setTEACHER(request.getParameter("TEACHER"));
            }

            Timestamp update = new Timestamp(System.currentTimeMillis());

            courseBean.setCreated(update);



            courseBean.setDelete_Flg(0);

            courseMeisaiBean.setDelete_Flg(0);

            List<String> alertList = new ArrayList<>(3);

            alertList = CourseValidate.Validate(request, courseBean);

            if(alertList.size() > 0){

                session.setAttribute("message", alertList);

                request.getRequestDispatcher("/WEB-INF/jsp/menu/courseUpdate.jsp").forward(request, response);

                return;

            }

            List<String> alertList2 = new ArrayList<>(6);

            alertList2 = CourseMeisaiValidate.Validate(request,courseMeisaiBean);

            if(alertList2.size() > 0){

                session.setAttribute("message", alertList2);

                request.getRequestDispatcher("/WEB-INF/jsp/menu/courseUpdate.jsp").forward(request, response);

                return;
            }


            CourseDAO courseDAO = new CourseDAO();

            CourseMeisaiDAO courseMeisaiDAO = new CourseMeisaiDAO();

            ConnectionManager.beginTransaction();

            courseDAO.update(courseBean);

            ConnectionManager.commit();

            courseMeisaiDAO.update(courseMeisaiBean);

            ConnectionManager.commit();

            String alert = "更新しました";

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

        if(request.getParameter("search") != null){

            List<UserBean> userBean = UserDAO.FindKoushi();

            try{
                ConnectionManager.close();
            }catch(SQLException e){
                e.printStackTrace();
            }

            session.setAttribute("user", userBean);

            List<SubjectBean> subjectBean = SubjectDAO.FindAll();


            try{
                ConnectionManager.close();
            }catch(SQLException e){
                e.printStackTrace();
            }

            session.setAttribute("subject",subjectBean);

            request.getRequestDispatcher("/WEB-INF/jsp/menu/courseSearch.jsp").forward(request, response);
        }



    }

}
