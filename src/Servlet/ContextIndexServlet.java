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

import Bean.ContextBean;
import DAO.ConnectionManager;
import DAO.ContextDAO;

/**
 * Servlet implementation class ContextIndexServlet
 */
@WebServlet("/ContextIndexServlet")
public class ContextIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContextIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	   HttpSession session = request.getSession();


		List<ContextBean> contextBean = ContextDAO.FindAll();

    	try{

            ConnectionManager.close();

        }catch(SQLException e){
            e.printStackTrace();
        }

    	session.setAttribute("context",contextBean);

        request.getRequestDispatcher("/WEB-INF/jsp/menu/contextIndex.jsp").forward(request, response);

        return;

	}

}
