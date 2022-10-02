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

import Bean.UserBean;
import DAO.ConnectionManager;
import DAO.UserDAO;

/**
 * Servlet implementation class UserInsertServlet
 */
@WebServlet("/UserIndexServlet")
public class UserIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }



    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

    	if(request.getParameter("insert") == null &&  request.getParameter("update") == null  && request.getParameter("delete") == null){

    		HttpSession session = request.getSession();

    		 List<UserBean> user =  UserDAO.FindAll();


             try{

                 ConnectionManager.close();

             }catch(SQLException e){
                 e.printStackTrace();
             }

             session.setAttribute("user", user);

             request.getRequestDispatcher("/WEB-INF/jsp/menu/userIndex.jsp").forward(request, response);



         }
	}


    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        HttpSession session = request.getSession();



        if(request.getParameter("insert") != null  ){

            request.getRequestDispatcher("/WEB-INF/jsp/menu/userInsert.jsp").forward(request, response);



        }

        if(request.getParameter("update") != null){



        	 String userId = request.getParameter("update");

        	 System.out.println(userId);

        	 //userBean.setUser_ID(request.getParameter("User_ID"));

        	 UserBean user = new UserBean();

        	 UserDAO userDAO = new UserDAO();

        	 UserBean  userBean = userDAO.findById(userId);

        	 if(!userBean.getUser_ID().equals(null)){



        		 user.setUser_ID(userBean.getUser_ID());
        		 user.setUser_NAME(userBean.getUser_NAME());
        		 user.setUser_Pass(userBean.getUser_Pass());
        		 user.setTuition(userBean.getTuition());
        		 user.setAdmin(userBean.getAdmin());
        		 user.setDelete_flg(userBean.getDelete_flg());
        		 user.setUpdate(userBean.getUpdate());

        		 session.setAttribute("user", user);

        		 request.getRequestDispatcher("/WEB-INF/jsp/menu/userUpdate.jsp").forward(request, response);


        	 }

        }


        if(request.getParameter("delete") != null){

       	 String userId = request.getParameter("delete");

    	 System.out.println(userId);

    	 UserBean user = new UserBean();

    	 UserDAO userDAO = new UserDAO();

    	 UserBean  userBean = userDAO.findById(userId);

    	 user.setUser_ID(userBean.getUser_ID());
		 user.setUser_NAME(userBean.getUser_NAME());
		 user.setUser_Pass(userBean.getUser_Pass());
		 user.setTuition(userBean.getTuition());
		 user.setAdmin(userBean.getAdmin());
		 user.setDelete_flg(1);
		 user.setUpdate(userBean.getUpdate());

	     ConnectionManager.beginTransaction();

         userDAO.update(user);

         ConnectionManager.commit();

         String alert = "削除しました";

         session.setAttribute("alert", alert);

         List<UserBean> user2 =  UserDAO.FindAll();


         try{

             ConnectionManager.close();

         }catch(SQLException e){
             e.printStackTrace();
         }

         session.setAttribute("user", user2);

         request.getRequestDispatcher("/WEB-INF/jsp/menu/userIndex.jsp").forward(request, response);

         return;

        }

    }

}
