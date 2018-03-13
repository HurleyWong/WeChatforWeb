package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MomentDao;
import dao.RelationDao;
import dao.ShowDao;
import ov.Moment;
import ov.Relation;



@WebServlet("/show_save")
public class ShowServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ShowServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	@SuppressWarnings("null")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<Relation> list = new ArrayList<Relation>();
		request.setCharacterEncoding("utf-8");
	    String c_id = (String) request.getSession().getAttribute("user_id");
	    System.out.println(c_id);
		String cansee = (String)request.getAttribute("cansee");
		System.out.println(cansee);
		Moment moment = (Moment)request.getAttribute("moment");
		
		Relation rela = new Relation();
		rela.setId(0);
		rela.setM_id(c_id);
		rela.setF_id(c_id);
		rela.setDate(null);
		rela.setType(null);
		rela.setRemark_name(null);
		
		System.out.println(rela.getId()+"rela");
		System.out.println(rela.getM_id()+"rela");
		System.out.println(rela.getF_id()+"rela");
		System.out.println(rela.getDate()+"rela");
		System.out.println(rela.getType()+"rela");
		System.out.println(rela.getRemark_name()+"rela");
		MomentDao mdao = new MomentDao();
	    int m_id = mdao.getMomentIdByMoment(moment);
	    if(cansee.equals("all myfriend")){
	        RelationDao rdao = new RelationDao();
	        list = rdao.listFriend(c_id);
	        System.out.println("-----"+list.size());
	        list.add(rela);
	    }else if(cansee.equals("only myself")){
	    	list.add(rela);
	    }
	    System.out.println(m_id);
	    for(int i = 0;i<list.size();i++){
	    	System.out.println((list.get(i)).getF_id());
	    }
		
		ShowDao sdao = new ShowDao();
		boolean result = sdao.saveShow(list,m_id);
		if (result == true) {
			System.out.println("可见权限设置成功");
			request.getRequestDispatcher("/moment_list").forward(request, response);
		} else {
			System.out.println("可见权限设置失败");
			request.getRequestDispatcher("/moment_add.jsp").forward(request, response);
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
