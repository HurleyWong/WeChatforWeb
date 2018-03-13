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
import dao.ArticleDao;
import ov.Account;
import ov.Article;

/**
 * Servlet implementation class ArticleServlet
 */
@WebServlet("/ArticleServlet")
public class ArticleServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	/**
     * @see HttpServlet#HttpServlet()
     */
	public ArticleServlet(){
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
		//String id=request.getParameter("id");
		//String a_id=(String)request.getAttribute("a_id");
		String a_id=request.getParameter("account_id");		//文章属于的公众号id
		System.out.println(a_id);
		//String a_id=(String)request.getSession().getAttribute("account_id");
		String title=new String(request.getParameter("title").getBytes("iso-8859-1"),"UTF-8");		//文章标题
		//String title=request.getParameter("title");		//文章标题
		String content=new String(request.getParameter("editorValue").getBytes("iso-8859-1"),"UTF-8");		//文章内容
		//String content=request.getParameter("editorValue");
		System.out.println(content);
		String u_id=(String)request.getSession().getAttribute("user_id");		//创建文章的用户id
		System.out.println(u_id);
		//int like_size=request.getParameter("like_size");

		article=new Article();
		//article.setId(id);
		article.setA_id(a_id);
		article.setTitle(title);
		article.setContent(content);
		article.setU_id(u_id);

		ArticleDao dao=new ArticleDao();
		if(dao.search(title)){
			System.out.println("该标题已经发表过！");
			response.sendRedirect("edit_article.jsp?error=1");
			return ;
		}
		else{
			System.out.println("可以发表该文章！");
		}
		if(dao.insertArticle(article)){
			System.out.println("插入成功！");
			request.setAttribute("str1","发表成功");
			request.setAttribute("str2","公众号文章页面");
			request.setAttribute("url1","edit_article.jsp");
			request.setAttribute("url2","content.jsp");
			AccountDao accountDao=new AccountDao();
			List<Account> accountList=accountDao.listAccount();
			request.setAttribute("list",accountList);
			request.getRequestDispatcher("content.jsp").forward(request,response);
		}
		else{
			System.out.println("插入失败！");
			response.sendRedirect("edit_article.jsp?error=2");
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



























