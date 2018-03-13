package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.HideDao;
import dao.LikeDao;
import dao.MomentDao;
import dao.ReviewDao;
import dao.ShowDao;
import dao.UserDao;
import ov.Hide;
import ov.Like;
import ov.Moment;
import ov.Review;
import ov.Show;
import ov.User;





@WebServlet("/moment_list")
public class MomentServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public MomentServlet() {
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
		
		String mid = (String) request.getSession().getAttribute("user_id");
		
		HideDao hdao = new HideDao();
		List<Hide> hideList = hdao.listHide(mid);
		System.out.println(hideList.size()+"hideList_size");
		
		ShowDao sdao = new ShowDao();
		List<Show> showList = sdao.listShow(mid);
		System.out.println(showList.size()+"showList_size");
		
		MomentDao dao = new MomentDao();
		List<Moment> momentList = dao.listMoment();
		

		for(int i = 0;i<momentList.size();i++){
			int k = 0;
			for(int j=0;j<hideList.size();j++){
				if((momentList.get(i).getC_id()).equals(hideList.get(j).getC_id())){
					k++;
				}
			}
			if(k!=0)
			momentList.remove(i);
		}
		
		for(int i = 0;i<momentList.size();i++){
			int k = 0;
			for (int j=0;j<showList.size();j++){
				if(momentList.get(i).getId() == showList.get(j).getM_id()){
					k++;
				}
			}
			if(k==0)
				momentList.remove(i);
		}
		
		UserDao udao = new UserDao();
		System.out.println("----"+momentList.size());		
		List<User> userList = udao.listUser(momentList);
		
		LikeDao ldao = new LikeDao();
		List<List<Like>> likeList = ldao.listLike(momentList);
		
		ReviewDao rdao = new ReviewDao();
		List<List<Review>> reviewList = rdao.listReview(momentList);
		
		System.out.println("momentList——size"+momentList.size());
//		System.out.println("显示"+momentList.size());
//		System.out.println("显示"+userList.size());
//		System.out.println("显示"+likeList.get(0).size());
//		System.out.println("显示"+reviewList.get(0).size());
		request.setAttribute("list", momentList);
		request.setAttribute("userList", userList);
		request.setAttribute("likeList", likeList);
		request.setAttribute("reviewList", reviewList);
		request.getRequestDispatcher("moment.jsp").forward(request, response);
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
