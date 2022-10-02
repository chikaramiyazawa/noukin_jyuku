package Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.UserBean;
import DAO.ConnectionManager;
import DAO.UserDAO;
import Validate.UserValidate;

/**
 * Servlet implementation class UserUpdateServlet
 */
@WebServlet("/UserUpdateServlet")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		 HttpSession session = request.getSession();

	        if(request.getParameter("back") == null && request.getParameter("update") == null){

	            session.removeAttribute("alert");

	            session.removeAttribute("message");


	          request.getRequestDispatcher("/WEB-INF/jsp/menu/userUpdate.jsp").forward(request, response);

	            return;

	        }

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 HttpSession session = request.getSession();

	        if (request.getParameter("update") != null) {

	           response.setCharacterEncoding("UTF-8");

	           response.setContentType("text/html; charset=UTF-8");


	            int admin = 0;

	            try{
	                admin  = (Integer.parseInt(request.getParameter("Admin")));
	            }catch(NumberFormatException e){
	                e.printStackTrace();
	            }



	            Timestamp update = null;

	            UserBean userBean = new UserBean();

	            if(!request.getParameter("User_ID").equals("")){

	                userBean.setUser_ID(request.getParameter("User_ID"));

	            }



	            if(!request.getParameter("User_NAME").equals("")){



	                String user_name = request.getParameter("User_NAME");

	                userBean.setUser_NAME(user_name);

	                System.out.println(user_name);

	            }


	            if(!request.getParameter("User_Pass").equals("")){

	                userBean.setUser_Pass(request.getParameter("User_Pass"));
	            }

	            if(admin != 0){

	                userBean.setAdmin(admin);
	            }else{
	                userBean.setAdmin(0);
	            }

	            update = new Timestamp(System.currentTimeMillis());

	            userBean.setUpdate(update);


	            userBean.setTuition(0);

	            userBean.setDelete_flg(0);


	            List<String> alertList = new ArrayList<>(4);

	            alertList = UserValidate.Validate(request, userBean);

	            if(alertList.size() > 0){

	                session.setAttribute("message", alertList);

	                request.getRequestDispatcher("/WEB-INF/jsp/menu/userUpdate.jsp").forward(request, response);

	                return;

	            }

	            UserDAO userDAO =new UserDAO();

	            ConnectionManager.beginTransaction();

	            userDAO.update(userBean);

	            ConnectionManager.commit();

	            String alert = "更新が完了しました";

	            session.setAttribute("alert", alert);


	            request.getRequestDispatcher("/WEB-INF/jsp/menu/userUpdate.jsp").forward(request, response);

	            return;

	        }


	        if(request.getParameter("index") != null){

	            List<UserBean> user =  UserDAO.FindAll();

	            try{

	                ConnectionManager.close();

	            }catch(SQLException e){
	                e.printStackTrace();
	            }

	            session.setAttribute("user", user);

	            request.getRequestDispatcher("/WEB-INF/jsp/menu/userIndex.jsp").forward(request, response);

	            return;

	        }

	    }



}
