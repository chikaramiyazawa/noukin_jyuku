package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.CourseMeisaiBean;
import Bean.StudyBean;
import Bean.UserBean;
import DAO.ConnectionManager;
import DAO.CourseMeisaiDAO;
import DAO.StudyDAO;
import DAO.UserDAO;

/**
 * Servlet implementation class StudyInsertServlet
 */
@WebServlet("/StudyInsertServlet")
public class StudyInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudyInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();

		session.removeAttribute("alert");

		if(request.getParameter("studying") != null){

			String study_course_id = request.getParameter("studying");

			String study_user_id = request.getParameter("user_id");

			StudyBean studyBean = new StudyBean();

			StudyDAO studyDAO = new StudyDAO();

			studyBean.setUser_ID(request.getParameter("user_id"));

			studyBean.setCourse_ID(study_course_id);

			studyBean.setStudyIng(1);

			CourseMeisaiBean coursemeisaiBean = new CourseMeisaiBean();

			CourseMeisaiDAO courseMesaiDAO = new CourseMeisaiDAO();

			coursemeisaiBean = courseMesaiDAO.findById(study_course_id);

			long tuition = coursemeisaiBean.getTuition();

			UserBean userBean = new UserBean();

			UserDAO userDAO = new UserDAO();

			userBean = userDAO.findById(study_user_id);

			long user_tuition = userBean.getTuition();

			user_tuition = user_tuition + tuition;

			 userBean.setUser_ID(userBean.getUser_ID());
             userBean.setUser_NAME(userBean.getUser_NAME());
             userBean.setUser_Pass(userBean.getUser_Pass());
             userBean.setAdmin(userBean.getAdmin());
             userBean.setTuition(user_tuition);
             userBean.setDelete_flg(userBean.getDelete_flg());
             userBean.setUpdate(userBean.getUpdate());

             ConnectionManager.beginTransaction();

             studyDAO.update(studyBean);

             ConnectionManager.commit();

             ConnectionManager.beginTransaction();

             userDAO.update(userBean);

             ConnectionManager.commit();

             String alert = "履修登録しました。";

			 session.setAttribute("alert", alert);

			 request.getRequestDispatcher("/WEB-INF/jsp/menu/userSearch.jsp").forward(request, response);

			 return;

		}


		if(request.getParameter("reset") != null){

			String study_course_id = request.getParameter("reset");

			String study_user_id = request.getParameter("user_id");

			StudyBean studyBean = new StudyBean();

			StudyDAO studyDAO = new StudyDAO();

			studyBean.setUser_ID(request.getParameter("user_id"));

			studyBean.setCourse_ID(study_course_id);

			studyBean.setStudyIng(0);

			CourseMeisaiBean coursemeisaiBean = new CourseMeisaiBean();

			CourseMeisaiDAO courseMesaiDAO = new CourseMeisaiDAO();

			coursemeisaiBean = courseMesaiDAO.findById(study_course_id);

			long tuition = coursemeisaiBean.getTuition();

			UserBean userBean = new UserBean();

			UserDAO userDAO = new UserDAO();

			userBean = userDAO.findById(study_user_id);

			long user_tuition = userBean.getTuition();

			user_tuition = user_tuition - tuition;

			 userBean.setUser_ID(userBean.getUser_ID());
             userBean.setUser_NAME(userBean.getUser_NAME());
             userBean.setUser_Pass(userBean.getUser_Pass());
             userBean.setAdmin(userBean.getAdmin());
             userBean.setTuition(user_tuition);
             userBean.setDelete_flg(userBean.getDelete_flg());
             userBean.setUpdate(userBean.getUpdate());

             ConnectionManager.beginTransaction();

             studyDAO.update(studyBean);

             ConnectionManager.commit();

             ConnectionManager.beginTransaction();

             userDAO.update(userBean);

             ConnectionManager.commit();

             String alert = "取り消しました。";

			 session.setAttribute("alert", alert);

			 request.getRequestDispatcher("/WEB-INF/jsp/menu/userSearch.jsp").forward(request, response);

			 return;

		}
	}

}
