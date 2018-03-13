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
 * Servlet implementation class Register2Servlet
 */
@WebServlet("/Register2Servlet")
public class Register2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register2Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("str")!=null) {
			System.out.println("账号:"+request.getParameter("str"));
			UserDao dao = new UserDao();
			if(dao.search(request.getParameter("str"))) {
				System.out.println("id已使用");
				response.getWriter().write("0");
			}else {
				System.out.println("可用");
				response.getWriter().write("1");
			}
		}
		if(request.getParameter("str2")!=null) {
			System.out.println("群id:"+request.getParameter("str"));
			GroupDao dao = new GroupDao();
			if(dao.searchGroup(Integer.parseInt(request.getParameter("str2")))) {
				System.out.println("id已使用");
				response.getWriter().write("0");
			}else {
				System.out.println("可用");
				response.getWriter().write("1");
			}
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
