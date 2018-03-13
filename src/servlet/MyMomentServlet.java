package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LikeDao;
import dao.MomentDao;
import dao.ReviewDao;
import dao.UserDao;
import ov.Like;
import ov.Moment;
import ov.Review;
import ov.User;





@WebServlet("/mymoment_list")
public class MyMomentServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public MyMomentServlet() {
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
		
		String c_id = (String) request.getSession().getAttribute("user_id");
		MomentDao dao = new MomentDao();
		List<Moment> mymomentList = dao.listMyMoment(c_id);
		
		UserDao udao = new UserDao();
		System.out.println("----"+mymomentList.size());		
		List<User> userList = udao.listUser(mymomentList);
		
		LikeDao ldao = new LikeDao();
		List<List<Like>> likeList = ldao.listLike(mymomentList);
		
		ReviewDao rdao = new ReviewDao();
		List<List<Review>> reviewList = rdao.listReview(mymomentList);
		
		System.out.println("显示我的朋友圈成功");
		request.setAttribute("list", mymomentList);
		request.setAttribute("userList", userList);
		request.setAttribute("likeList", likeList);
		request.setAttribute("reviewList", reviewList);
		request.getRequestDispatcher("myfriend.jsp").forward(request, response);
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
