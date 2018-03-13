package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GroupDao;
import dao.UserDao;

/**
 * Servlet implementation class AddG_PServlet
 */
@WebServlet("/AddG_PServlet")
public class AddG_PServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddG_PServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("AddG_PServlet");
		
		if(request.getParameter("m_id")!=null&&request.getParameter("f_id")!=null) {
			UserDao dao = new UserDao();
			
			if(dao.addFriends(request.getParameter("m_id"), request.getParameter("f_id"))) {
				if(dao.addFriends(request.getParameter("f_id"), request.getParameter("m_id"))) {
					System.out.println("增加好友成功");
					request.getRequestDispatcher("ContactServlet?id="+request.getParameter("m_id")).forward(request, response);
				}
			}
			
			return ;
		}
		if(request.getParameter("g_id")!=null) {
			
			return ;
		}
		if(request.getParameter("add_group_id")!=null) {
			String str1[] = request.getParameterValues("jszzdm_add");
			int g_id = Integer.parseInt(request.getParameter("add_group_id"));
			System.out.println("g_id="+g_id);
			GroupDao dao = new GroupDao();
			for(int i=0;i<str1.length;i++) {
				System.out.println("- "+str1[i]);
				dao.insertPersonToGroup(str1[i], g_id);
			}
			response.sendRedirect("GroupInformationServlet?info_type=1&g_id="+g_id);
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
