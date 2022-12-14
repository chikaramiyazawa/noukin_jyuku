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

import org.apache.commons.lang3.RandomStringUtils;

import Bean.CourseBean;
import Bean.CourseMeisaiBean;
import Bean.SubjectBean;
import Bean.UserBean;
import DAO.ConnectionManager;
import DAO.CourseDAO;
import DAO.CourseMeisaiDAO;
import DAO.SubjectDAO;
import DAO.UserDAO;
import Validate.CourseMeisaiValidate;
import Validate.CourseValidate;

/**
 * Servlet implementation class CourseInsertServlet
 */
@WebServlet("/CourseInsertServlet")
public class CourseInsertServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // TODO Auto-generated method stub

        HttpSession session = request.getSession();

        session.removeAttribute("message");

        session.removeAttribute("alert");

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

             request.getRequestDispatcher("/WEB-INF/jsp/menu/courseInsert.jsp").forward(request, response);

             return;

        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        HttpSession session = request.getSession();

        request.setCharacterEncoding("UTF-8");

        response.setContentType("text/html; charset=UTF-8");

    	if(request.getParameter("insert") != null){

            int tuition = 0;

            int target1 = 0;

            int target2 = 0;

            int subject = 0;

            int day_lecture = 0;


            try{
                target1 = (Integer.parseInt(request.getParameter("TARGET1")));

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






            //CourseBean?????????
            CourseBean courseBean = new CourseBean();

            //CourseMeisaiBean?????????
            CourseMeisaiBean courseMeisaiBean = new CourseMeisaiBean();

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

            if(target1 != 0){

                try{
                    target2 = (Integer.parseInt(request.getParameter("TARGET2")));

                    int grade = target1 + target2;

                    courseMeisaiBean.setGrade(grade);

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
                    // TODO ????????????????????? catch ????????????
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

            String course_id = RandomStringUtils.randomAlphabetic(10);

            courseBean.setCourse_ID(course_id);

            courseMeisaiBean.setCourse_ID(course_id);

            courseBean.setDelete_Flg(0);

            courseMeisaiBean.setDelete_Flg(0);

            List<String> alertList = new ArrayList<>(3);

            alertList = CourseValidate.Validate(request, courseBean);

            if(alertList.size() > 0){

                session.setAttribute("message", alertList);


                request.getRequestDispatcher("/WEB-INF/jsp/menu/courseInsert.jsp").forward(request, response);

                return;

            }

            List<String> alertList2 = new ArrayList<>(6);

            alertList2 = CourseMeisaiValidate.Validate(request,courseMeisaiBean);

            if(alertList2.size() > 0){

                session.setAttribute("message", alertList2);

                request.getRequestDispatcher("/WEB-INF/jsp/menu/courseInsert.jsp").forward(request, response);

                return;
            }


            CourseDAO courseDAO = new CourseDAO();

            CourseMeisaiDAO courseMeisaiDAO = new CourseMeisaiDAO();

            ConnectionManager.beginTransaction();

            courseDAO.create(courseBean);

            ConnectionManager.commit();

            courseMeisaiDAO.create(courseMeisaiBean);

            ConnectionManager.commit();

            String alert = "???????????????????????????";

            session.setAttribute("alert", alert);

            request.getRequestDispatcher("/WEB-INF/jsp/menu/courseInsert.jsp").forward(request, response);

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



