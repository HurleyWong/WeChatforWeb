package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReviewDao;
import ov.Review;




@WebServlet("/review_save")
public class ReviewServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ReviewServlet() {
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
		Review review = new Review();
		int mark = Integer.parseInt(request.getParameter("mark"));
        int m_id = Integer.parseInt(request.getParameter("m_id"));
		String r_id = (String) request.getSession().getAttribute("user_id");
		String content = request.getParameter("content");
		java.sql.Date date = new java.sql.Date((new java.util.Date()).getTime());
		
		System.out.println(m_id+"pinglun");
		System.out.println(content+"pinglun");
		System.out.println(r_id+"pinglun");
		System.out.println(date+"pinglun");
		
		review.setM_id(m_id);
		review.setR_id(r_id);
		review.setContent(content);
		review.setDate(date);
		
		ReviewDao dao = new ReviewDao();
		boolean result = dao.saveReview(review);
		
		

       if(mark==0){
		if (result == true) {
			System.out.println("评论成功");
			request.getRequestDispatcher("/moment_list").forward(request, response);
		} else {
			System.out.println("评论失败");
			request.getRequestDispatcher("/moment_list").forward(request, response);
		}
       }else if(mark==1){
    	   if (result == true) {
   			System.out.println("评论成功");
   			request.getRequestDispatcher("/mymoment_list").forward(request, response);
   		} else {
   			System.out.println("评论失败");
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
