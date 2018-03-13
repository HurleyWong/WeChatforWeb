package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.User;

import dao.AccountDao;
import ov.Account;
import ov.Article;

/**
 * Servlet implementation class ArticleContentServlet
 */
@WebServlet("/ArticleContentServlet")
public class ArticleContentServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	/**
     * @see HttpServlet#HttpServlet()
     */
	public ArticleContentServlet(){
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException{

		User user=null;
		Account account=null;
		Article article=null;

		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");

		String a_id=request.getParameter("account_id");		//文章属于的公众号id
		System.out.println(a_id);
		String title=new String(request.getParameter("title").getBytes("iso-8859-1"),"UTF-8");		//文章标题
		System.out.println(title);
		String content=new String(request.getParameter("editorValue").getBytes("iso-8859-1"),"UTF-8");	//文章内容
		System.out.println(content);
		String u_id=(String)request.getSession().getAttribute("user_id");		//创建文章的用户id
		System.out.println(u_id);

		AccountDao accountDao=new AccountDao();
		List<Account> accountList=accountDao.listAccount();
		request.setAttribute("list",accountList);
		request.getRequestDispatcher("article_review.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		doGet(request,response);
	}
}




















