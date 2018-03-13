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


@WebServlet("/moment_save")
public class MomentSaveServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public MomentSaveServlet() {
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Moment moment = new Moment();
		String cansee = request.getParameter("list");
		System.out.println(cansee);
		String c_id = (String) request.getSession().getAttribute("user_id");
		String image = "";
		String content=new String(request.getParameter("editorValue").getBytes("iso-8859-1"),"UTF-8");
		System.out.println(content);
		java.sql.Date date = new java.sql.Date((new java.util.Date()).getTime());
		
		System.out.println(c_id);
		moment.setC_id(c_id);
		moment.setImage(image);
		moment.setContent(content);
		moment.setDate(date);
		
		MomentDao dao = new MomentDao();
		boolean result = dao.saveMoment(moment);
		if (result == true) {
			System.out.println("插入成功");
			request.setAttribute("cansee", cansee);
			request.setAttribute("moment", moment);
			request.getRequestDispatcher("/show_save").forward(request, response);
			//request.getRequestDispatcher("/moment_list").forward(request, response);
		} else {
			System.out.println("插入失败");
			request.getRequestDispatcher("/moment_add.jsp").forward(request, response);
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
