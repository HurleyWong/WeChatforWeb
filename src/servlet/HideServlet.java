package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.HideDao;
import ov.Hide;






@WebServlet("/hide_save")
public class HideServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public HideServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		boolean result = true ;
		request.setCharacterEncoding("utf-8");
		Hide hide = new Hide();
		String c_id = request.getParameter("c_id");
		String t_id = (String) request.getSession().getAttribute("user_id");
		System.out.println(c_id+"ks");
		System.out.println(t_id+"ks");
		
		hide.setC_id(c_id);
		hide.setT_id(t_id);
		
		HideDao dao = new HideDao();
		if(c_id.equals(t_id)){
			 result = true ;
		}else{
			 result = dao.saveHide(hide);
		}	
		if (result == true) {
			System.out.println("∆¡±Œ≥…π¶");
			request.getRequestDispatcher("/moment_list").forward(request, response);
		} else {
			System.out.println("∆¡±Œ ß∞‹");
			request.getRequestDispatcher("/moment_list").forward(request, response);
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
