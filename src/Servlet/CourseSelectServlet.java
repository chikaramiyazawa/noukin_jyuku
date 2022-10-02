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
import DAO.StudyDAO;
import DAO.UserDAO;

/**
 * Servlet implementation class CourseRegistrationServlet
 */
@WebServlet("/CourseSelectServlet")
public class CourseSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseSelectServlet() {
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

		if(request.getParameter("register") == null){

			String userId =  request.getParameter("User_ID");

			UserBean user = new UserBean();

            UserDAO userDAO = new UserDAO();

            UserBean userBean = userDAO.findById(userId);

             user.setUser_ID(userBean.getUser_ID());
			 user.setUser_NAME(userBean.getUser_NAME());
			 user.setUser_Pass(userBean.getUser_Pass());
			 user.setTuition(userBean.getTuition());
			 user.setAdmin(userBean.getAdmin());
			 user.setDelete_flg(userBean.getDelete_flg());
			 user.setUpdate(userBean.getUpdate());

			 session.setAttribute("user", user);


			CourseRegisterationDAO  courseRegistorationDAO = new CourseRegisterationDAO();

			List<SearchBeans> searchBeans = CourseRegisterationDAO.FindAll();

			try{

	            ConnectionManager.close();

	        }catch(SQLException e){
	            e.printStackTrace();
	        }

			session.setAttribute("list",searchBeans);


			 request.getRequestDispatcher("/WEB-INF/jsp/menu/courseSelect.jsp").forward(request, response);

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

		if(request.getParameter("select") != null){

			List<SearchBeans> list = new ArrayList<SearchBeans>();

			StudyBean studyBean = new StudyBean();

			StudyDAO studyDAO = new StudyDAO();

			studyBean.setUser_ID(request.getParameter("user_id"));

			String u = request.getParameter("user_id");

			studyBean.setCourse_ID(request.getParameter("select"));

			String c = request.getParameter("select");

			studyBean.setStudyIng(0);

			int num =  StudyDAO.getCountByID(u , c);

			if(num != 0){

				String alert = "履修済みです";

				session.setAttribute("alert", alert);

		            request.getRequestDispatcher("/WEB-INF/jsp/menu/courseSelect.jsp").forward(request, response);

		            return;


			}else{

				ConnectionManager.beginTransaction();

				studyDAO.create(studyBean);

				 ConnectionManager.commit();

				 String alert = "選択しました。";

				 session.setAttribute("alert", alert);

				    request.getRequestDispatcher("/WEB-INF/jsp/menu/courseSelect.jsp").forward(request, response);

		            return;


			}

		}

	      if(request.getParameter("back") != null){

	        	request.getRequestDispatcher("/WEB-INF/jsp/menu/userSearch.jsp").forward(request, response);

		          return;

	      }

	}

}
