package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import ov.User;

/**
 * Servlet implementation class WeChatServlet
 */
@WebServlet("/WeChatServlet")
public class WeChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WeChatServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = null;
		request.setCharacterEncoding("utf-8");
		if(request.getParameter("id")!=null) {
			UserDao dao = new UserDao();
			user = dao.getUserById(request.getParameter("id"));
		}else {
			UserDao dao = new UserDao();
			user = dao.getUserByUserName(request.getParameter("username"));
			response.sendRedirect("login.jsp?error=2");
			return ;
		}
//		System.out.println(user.toString()+"\n----user info end-----\n");
		System.out.println("geted user");
		request.setAttribute("user", user);
		request.getSession().setAttribute("User", user);
		request.getRequestDispatcher("wechat.jsp").forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
