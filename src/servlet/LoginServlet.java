package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import ov.Myfriend;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LoginServlet");
		// TODO Auto-generated method stub
		String str1 = request.getParameter("username");
		String str2 = request.getParameter("password");
		
		if(request.getParameter("jzmm")!=null) {
			if(request.getParameter("jzmm").equals("1")) {
				System.out.println("选择了免登陆");
				Cookie cookie1 = new Cookie("myusername",str1);
				Cookie cookie2 = new Cookie("mypassword",str2);
				
				cookie1.setMaxAge(60*60*24*3);
				cookie2.setMaxAge(60*60*24*3);
				
				response.addCookie(cookie1);
				response.addCookie(cookie2);
			}else {
			}
		}else {
			System.out.println("没有选择了免登陆");
			Cookie cookie = null;
			Cookie[] cookies = null;
			   // 获取当前域名下的cookies，是一个数组
			   cookies = request.getCookies();
			   if( cookies.length!=0){
			      for (int i = 0; i < cookies.length; i++){
			         cookie = cookies[i];
			         System.out.println("cookies:"+cookie.toString());
			         if((cookie.getName()).equals("myusername")){
			            cookie.setMaxAge(0);
			            response.addCookie(cookie);
			            System.out.println("删除了username");
			         }
			         if((cookie.getName()).equals("mypassword")){
			            cookie.setMaxAge(0);
			            response.addCookie(cookie);
			            System.out.println("删除了password");
				     }
			      }
			   }else {
				   System.out.println("cookies为空");
			   }
		}
		
		UserDao dao = new UserDao();
		if(dao.login1(str1, str2)) {
			
			System.out.println("登录成功");
			request.setAttribute("str1", "登录成功");
			request.setAttribute("str2", "主页面");
			request.getSession().setAttribute("userid", str1);
			request.setAttribute("url1", "login.jsp");
			request.setAttribute("url2", "WeChatServlet?id="+str1);
			request.getRequestDispatcher("turn.jsp").forward(request, response);
			return;
		}
		if(dao.login2(str1, str2)) {
			System.out.println("登录成功");
			request.setAttribute("str1", "登录成功");
			request.setAttribute("str2", "主页面");
			request.setAttribute("url1", "login.jsp");
			request.setAttribute("url2", "WeChatServlet?username="+str1);
			request.getRequestDispatcher("turn.jsp").forward(request, response);
			return;
		}
		response.sendRedirect("login.jsp?error=1");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
