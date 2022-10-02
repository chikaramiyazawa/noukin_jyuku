package Servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.SubjectBean;
import DAO.ConnectionManager;
import DAO.SubjectDAO;
import Validate.SubjectValidate;

/**
 * Servlet implementation class SubjectInsertServlet
 */
@WebServlet("/SubjectInsertServlet")
public class SubjectInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubjectInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 HttpSession session = request.getSession();

	        if(request.getParameter("insert") == null && request.getParameter("index") == null){

	            session.removeAttribute("alert");

	            session.removeAttribute("message");

	          request.getRequestDispatcher("/WEB-INF/jsp/menu/subjectInsert.jsp").forward(request, response);

	            return;

	        }


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

		HttpSession session = request.getSession();

        if (request.getParameter("insert") != null) {

        int id = 0;

        try{
            id = (Integer.parseInt(request.getParameter("ID")));

        }catch(NumberFormatException e){
            e.printStackTrace();
        }

        SubjectBean subjectBean = new SubjectBean();

           response.setCharacterEncoding("UTF-8");

           response.setContentType("text/html; charset=UTF-8");

           if(id != 0){

        	   subjectBean.setID(id);

           }else{

        	   subjectBean.setID(-1);

           }


           if(!request.getParameter("Name").equals("")){

        	   String name = request.getParameter("Name");

        	   subjectBean.setName(name);

           }

           Timestamp created = new Timestamp(System.currentTimeMillis());


           List<String> alertList = new ArrayList<>(2);

           alertList = SubjectValidate.Validate(request, subjectBean);

           if(alertList.size() > 0){

               session.setAttribute("message", alertList);

               request.getRequestDispatcher("/WEB-INF/jsp/topmenu/subjectInsert.jsp").forward(request, response);

               return;

           }else{

           SubjectDAO subjectDAO = new SubjectDAO();

           ConnectionManager.beginTransaction();

           subjectDAO.create(subjectBean);

           ConnectionManager.commit();

           String alert = "登録が完了しました";

           session.setAttribute("alert", alert);

           request.getRequestDispatcher("/WEB-INF/jsp/topmenu/subjectInsert.jsp").forward(request, response);

           return;

           }

        }

        if (request.getParameter("index") != null) {

        	request.getRequestDispatcher("/WEB-INF/jsp/topmenu/subjectIndex.jsp").forward(request, response);

        }


	}

}
