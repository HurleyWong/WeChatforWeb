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
 * Servlet implementation class DeleteG_PServlet
 */
@WebServlet("/DeleteG_PServlet")
public class DeleteG_PServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteG_PServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String id;
		
		System.out.println("DeleteG_PServlet");
		if(request.getParameter("p_id")!=null) {
			id=request.getParameter("p_id");
			System.out.println("p_id==="+id);
			UserDao dao = new UserDao();
			if(dao.deleteShipByIds(request.getSession().getAttribute("userid").toString(), id)) {
				System.out.println("删除好友成功");
				response.getWriter().write("1");
			}else {
				System.out.println("删除好友失败");
				response.getWriter().write("0");
			}
			
			
		}
		if(request.getParameter("g_id")!=null){				
			String u_id = (String) request.getSession().getAttribute("userid");
			GroupDao dao = new GroupDao();
			id=request.getParameter("g_id");
			System.out.println("g_id"+id);
			System.out.println("  "+dao.getGroupById(Integer.parseInt(id)).getC_id());
			System.out.println("u_id"+u_id);
			if(dao.getGroupById(Integer.parseInt(id)).getC_id().equals(u_id)) {
				if(dao.deleteGroup(Integer.parseInt(id))) {
					System.out.println("删除群聊成功");
					response.getWriter().write("1");
					
				}else {
					System.out.println("删除群聊失败");
					response.getWriter().write("0");
				}
			}else {
				if(dao.movePersontoGroup(id,u_id)) {
					System.out.println("删除成员成功");
					response.getWriter().write("1");
				}else {
					System.out.println("删除成员失败");
					response.getWriter().write("0");
				}
			}
		}
		if(request.getParameter("jszzdm")!=null) {
			String str1[] = request.getParameterValues("jszzdm");
			String group_id = request.getParameter("group_id");
			String user_id =request.getParameter("user_id");
			GroupDao dao = new GroupDao();
			for(int i =0;i<str1.length;i++) {
				if(dao.movePersontoGroup(group_id, str1[i])) {
					System.out.println("移除群成员成功");
				}
			}
			
			
			response.sendRedirect("GroupInformationServlet?info_type=1&g_id="+group_id);
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
