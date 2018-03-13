package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FileDao;
import dao.GroupDao;
import dao.UserDao;
import net.sf.json.JSONArray;
import ov.*;

/**
 * Servlet implementation class GroupInformationServlet
 */
@WebServlet("/GroupInformationServlet")
public class GroupInformationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupInformationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("info_type");
		
		if(type.equals("1")) {//	群管理
			int g_id = Integer.parseInt(request.getParameter("g_id"));
			UserDao dao1 = new UserDao();
			GroupDao dao2 = new GroupDao();
			FileDao dao3 = new FileDao();
			
			ArrayList<Myfriend> list = dao1.getFriedsByMyId(request.getSession().getAttribute("userid").toString());
			User user = dao1.getUserById(request.getSession().getAttribute("userid").toString());
			GroupInfo f_info = new GroupInfo();
			f_info.setGroup(dao2.getGroupById(g_id));
			f_info.setList(dao2.getGroupPerson(g_id));
			f_info.setList2(dao3.getAllFilesByGroupId(g_id));
			
			request.setAttribute("user", user);
			request.setAttribute("group_info", f_info);
			request.setAttribute("myfriends", list);
			
			request.getRequestDispatcher("managegroup.jsp").forward(request, response);
			
		}
		if(type.equals("0")) {
			
			
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
