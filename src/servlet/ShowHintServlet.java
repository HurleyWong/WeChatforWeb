package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import net.sf.json.*;


/**
 * Servlet implementation class ShowHintServlet
 */
@WebServlet("/ShowHintServlet")
public class ShowHintServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowHintServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		System.out.println("ShowHintServlet");
		String keyword = request.getParameter("keyword");
		System.out.println("keyword:"+keyword);

		
		ArrayList<String> list = new ArrayList<String>();

		System.out.println("read json...");
		UserDao dao = new UserDao();
		list = dao.getFriendsByFriendsName(request.getParameter("keyword"), request.getSession().getAttribute("userid").toString());
//		System.out.println(JSONArray.fromObject(list).toString());
		if(list == null) {
			System.out.println("模糊查询结果为空");
		}else {
			System.out.println("模糊查询结果不为空");
			System.out.println(list.toString());
		}
		
		response.getWriter().write(JSONArray.fromObject(list).toString());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
