package Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.SearchBeans;
import Bean.StudyBean;
import Bean.UserBean;
import DAO.ConnectionManager;
import DAO.CourseRegisterationDAO;
import DAO.SearchDAO;
import DAO.StudyDAO;
import DAO.UserDAO;

/**
 * Servlet implementation class UserSearchServlet
 */
@WebServlet("/UserSearchServlet")
public class UserSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	    HttpSession session = request.getSession();

	    session.removeAttribute("alert");

		  if(request.getParameter("search") == null && request.getParameter("register") == null){

			  request.getRequestDispatcher("/WEB-INF/jsp/menu/userSearch.jsp").forward(request, response);

	          return;

		  }

		    if(request.getParameter("register") != null){

		    	CourseRegisterationDAO  courseRegistorationDAO = new CourseRegisterationDAO();

				List<SearchBeans> searchBeans = CourseRegisterationDAO.FindAll();

				try{

		            ConnectionManager.close();

		        }catch(SQLException e){
		            e.printStackTrace();
		        }

				session.setAttribute("list",searchBeans);



	        	request.getRequestDispatcher("/WEB-INF/jsp/menu/courseSelect.jsp").forward(request, response);

				 return;


	        }

		       if(request.getParameter("back") != null){

		        	request.getRequestDispatcher("/WEB-INF/jsp/menu/userSearch.jsp").forward(request, response);

			          return;

		        }



	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		session.removeAttribute("alert");

		response.setCharacterEncoding("UTF-8");

        response.setContentType("text/html; charset=UTF-8");

        if (request.getParameter("search") != null) {

        	UserBean user = new UserBean();

        	UserDAO userDAO = new UserDAO();

        	StudyBean study = new StudyBean();

        	StudyDAO studyDAO = new StudyDAO();


            if(!request.getParameter("User_ID").equals("")){

             String userId =  request.getParameter("User_ID");



             UserBean userBean = userDAO.findById(userId);

             user.setUser_ID(userBean.getUser_ID());
			 user.setUser_NAME(userBean.getUser_NAME());
			 user.setUser_Pass(userBean.getUser_Pass());
			 user.setTuition(userBean.getTuition());
			 user.setAdmin(userBean.getAdmin());
			 user.setDelete_flg(userBean.getDelete_flg());
			 user.setUpdate(userBean.getUpdate());

			 session.setAttribute("user", user);


			 StudyBean studyBean = studyDAO.findById(userId);

			 study.setUser_ID(studyBean.getUser_ID());
			 study.setCourse_ID(study.getCourse_ID());
			 study.setStudyIng(studyBean.getStudyIng());

			 session.setAttribute("study",study);

			 SearchDAO searchDAO = new SearchDAO();

			 List<SearchBeans> searchBean = new ArrayList<SearchBeans>();

			 searchBean = searchDAO.getCouseInfo(userId);

			 request.setAttribute("list", searchBean);

			 request.getRequestDispatcher("/WEB-INF/jsp/menu/userInfo.jsp").forward(request, response);

			 return;



            }



        }






	}


}
