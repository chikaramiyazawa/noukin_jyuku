package Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import calendar.MyCalendar;
import calendar.MyCalendarLogic;

/**
 * Servlet implementation class CalendarServlet
 */
@WebServlet("/CalendarServlet")
public class CalendarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalendarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String s_year = request.getParameter("year");
		String s_month = request.getParameter("month");
		MyCalendarLogic logic = new MyCalendarLogic();
		MyCalendar mc = null;
		if(s_year != null && s_month != null){
			int year = Integer.parseInt(s_year);
			int month = Integer.parseInt(s_month);
			if(month==0){
				month=12;
				year--;
			}
			if(month==13){
				month=1;
				year++;
			}

			mc = logic.createMyCalendar(year,month);
		}else{
			mc = logic.createMyCalendar();
		}

		request.setAttribute("mc", mc);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/menu/calendar.jsp");
		rd.forward(request, response);
	}

}
