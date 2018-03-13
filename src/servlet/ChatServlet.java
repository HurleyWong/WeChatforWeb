package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.*;
import ov.MyG_Message;
import ov.MyGroup;
import ov.Myfriend;
import ov.P_Message;
import ov.User;

/**
 * Servlet implementation class ChatServlet
 */
@WebServlet("/ChatServlet")
public class ChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChatServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id ;
		String m_id = (String) request.getSession().getAttribute("userid");
		MessageDao dao3= new MessageDao();
		
		if(request.getParameter("p_id")!=null) {	//	点击单聊
			id=request.getParameter("p_id");
			System.out.println("p_id==="+id);
			ArrayList<P_Message> list = dao3.getPersonMessageByP_Id(id,(String)request.getSession().getAttribute("userid"));
			request.setAttribute("m_p_m", list);
			
		}else {										//	点击群聊
			id=request.getParameter("g_id");
			System.out.println("g_id==="+id);
			MyG_Message  m_g_m= dao3.getGroupMessageByG_Id(id);
			request.setAttribute("m_g_m", m_g_m);

		}
		User user = null;
		UserDao dao = new UserDao();
		GroupDao dao2 = new GroupDao();
		user = dao.getUserById((String) request.getSession().getAttribute("userid"));
		
		ArrayList<Myfriend> list = dao.getFriedsByMyId(user.getId());
		ArrayList<MyGroup> list2 = dao2.getMyGroupsByMyId(user.getId());
		
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
		
		request.getRequestDispatcher("chat.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
