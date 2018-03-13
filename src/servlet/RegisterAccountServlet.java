package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccountDao;
import ov.Account;
import ov.User;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterAccountServlet")
public class RegisterAccountServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	/**
     * @see HttpServlet#HttpServlet()
     */
	public RegisterAccountServlet(){
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException{

		User user=null;

		response.setCharacterEncoding("utf-8");
		String id=request.getParameter("id");
		String account_name=request.getParameter("account_name");
		String introduction=request.getParameter("introduction");
		String c_id=(String)request.getSession().getAttribute("user_id");
		System.out.println("创建公众号用户id:"+c_id);

		Account account=new Account();
		account.setId(id);
		account.setAccount_name(account_name);
		account.setC_id(c_id);
		account.setIntroduction(introduction);

		AccountDao dao=new AccountDao();
		if(dao.search(id)){
			System.out.println("该id已申请过公众号！");
			response.sendRedirect("register_account.jsp?error=1");
			return ;
		}
		else{
			System.out.println("可以申请公众号！");
		}
		if(dao.insertAccount(account)){
			System.out.println("插入成功！");
			request.setAttribute("str1","申请成功");
			request.setAttribute("str2","公众号文章编辑界面");
			request.setAttribute("url1","register_account.jsp");
			request.setAttribute("url2","edit_article.jsp");
			//request.setAttribute("a_id",id);		//传公众号id到edit_article.jsp
			request.setAttribute("account1",account);
			System.out.println(account);
			List<Account> accountList=dao.listAccount();
			request.setAttribute("list",accountList);
			request.getRequestDispatcher("edit_article.jsp").forward(request,response);
		}
		else{
			System.out.println("插入失败");
			response.sendRedirect("register_account.jsp?error=2");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		doGet(request,response);
	}
}
















