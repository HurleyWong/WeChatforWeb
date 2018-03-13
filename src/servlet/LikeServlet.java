package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LikeDao;
import ov.Like;


@WebServlet("/like_save")
public class LikeServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LikeServlet() {
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
		int mark = Integer.parseInt(request.getParameter("mark"));
		Like like = new Like();
		String c_id = (String) request.getSession().getAttribute("user_id");
		String m_id = (String)request.getParameter("mo_id");	
		System.out.println(c_id+"ks");
		System.out.println(m_id+"ks");
		System.out.println(mark+"mark");
		
		like.setC_id(c_id);
		like.setM_id(Integer.parseInt(m_id));
		
		LikeDao dao = new LikeDao();
		boolean result = dao.saveLike(like);
		if(mark==0){
			if (result == true) {
				System.out.println("点赞成功");
				request.getRequestDispatcher("/moment_list").forward(request, response);
			} else {
				System.out.println("点赞失败");
				request.getRequestDispatcher("/moment_list").forward(request, response);
			}
	    }else if(mark==1){
	    	 if (result == true) {
	   			System.out.println("点赞成功");
	   			request.getRequestDispatcher("/mymoment_list").forward(request, response);
	   		} else {
	   			System.out.println("点赞失败");
	   			request.getRequestDispatcher("/mymoment_list").forward(request, response);
	   		}
	    }else{}
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
