package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccountDao;
import dao.UserDao;
import ov.Account;
import ov.User;

/**
 * Servlet implementation class OfficialAccountsServlet
 */
@WebServlet("/OfficialAccountsServlet")
public class OfficialAccountsServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public OfficialAccountsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException{
    	User user = null;
    	Account account=null;

		request.setCharacterEncoding("utf-8");
		if(request.getParameter("id")!=null) {
			UserDao dao = new UserDao();
			user = dao.getUserById(request.getParameter("id"));
		}
		else {
			UserDao dao = new UserDao();
			user = dao.getUserByUserName(request.getParameter("username"));
			response.sendRedirect("login.jsp?error=2");
			return ;
		}
		request.setAttribute("user", user);

		AccountDao dao=new AccountDao();
		List<Account> accountList=dao.listAccount();
		System.out.println("显示成功！");
		request.setAttribute("list",accountList);
		request.getRequestDispatcher("officialAccounts.jsp").forward(request, response);
    }

    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}


















