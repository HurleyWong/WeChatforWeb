package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MomentDao;
import ov.Moment;
import util.StringUtil;


@WebServlet("/moment_detele")
public class MomentDeteleServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public MomentDeteleServlet() {
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
        
		request.setCharacterEncoding("utf-8");
		int m_id  = Integer.parseInt(request.getParameter("m_id"));		
		System.out.println(m_id+"detele");
		
		MomentDao dao = new MomentDao();
		boolean result = dao.deteleMoment(m_id);
		if (result == true) {
			System.out.println("É¾³ý³É¹¦");
			request.getRequestDispatcher("/mymoment_list").forward(request, response);
		} else {
			System.out.println("É¾³ýÊ§°Ü");
			request.getRequestDispatcher("/myfriend.jsp").forward(request, response);
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
