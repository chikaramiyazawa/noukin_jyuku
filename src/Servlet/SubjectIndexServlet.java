package Servlet;

import java.io.IOException;
import java.sql.SQLException;
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

/**
 * Servlet implementation class SubjectIndexServlet
 */
@WebServlet("/SubjectIndexServlet")
public class SubjectIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubjectIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 HttpSession session = request.getSession();



	        if(request.getParameter("insert") != null ){

	            request.getRequestDispatcher("/WEB-INF/jsp/menu/subjectInsert.jsp").forward(request, response);



	        }

	        else{

	        	List<SubjectBean> subjectBean = SubjectDAO.FindAll();

	        	try{

	                ConnectionManager.close();

	            }catch(SQLException e){
	                e.printStackTrace();
	            }

	        	session.setAttribute("subject",subjectBean);

	            request.getRequestDispatcher("/WEB-INF/jsp/menu/subjectIndex.jsp").forward(request, response);

	            return;



	        }

	}

}
