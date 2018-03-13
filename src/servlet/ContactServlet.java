package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GroupDao;
import dao.UserDao;
import ov.MyGroup;
import ov.Myfriend;
import ov.User;

/**
 * Servlet implementation class ContactServlet
 */
@WebServlet("/ContactServlet")
public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = null;
		if(request.getParameter("id")!=null) {
			UserDao dao = new UserDao();
			GroupDao dao2 = new GroupDao();
			user = dao.getUserById(request.getParameter("id"));
			
			ArrayList<Myfriend> list = dao.getFriedsByMyId(request.getParameter("id"));
			ArrayList<MyGroup> list2 = dao2.getMyGroupsByMyId(request.getParameter("id"));
			
			System.out.println("MyGroup:");
			for(MyGroup o2:list2){
				System.out.println(o2.toString());
			}
			
			request.setAttribute("myfriends", list);
			request.setAttribute("mygroups", list2);
			request.setAttribute("user", user);
			
			if(request.getParameter("type")!=null) {
				System.out.println("type not null");
				if(request.getParameter("type").equals("create")) {
					request.getRequestDispatcher("creatgroup.jsp").forward(request, response);
					return ;
				}else {
					return ;
				}
			}
			
			request.getRequestDispatcher("contact.jsp").forward(request, response);
			
		}else {
			UserDao dao = new UserDao();
			user = dao.getUserByUserName(request.getParameter("username"));
			response.sendRedirect("login.jsp?error=2");
			return ;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
