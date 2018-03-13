package servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GroupDao;
import ov.Group;

/**
 * Servlet implementation class CreatGroupServlet
 */
@WebServlet("/CreatGroupServlet")
public class CreatGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreatGroupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("CreatGroupServlet");
		ArrayList<String> list = new ArrayList<String>();
		String str1[] = request.getParameterValues("jszzdm");
		
		Group group = new Group();
		group.setId(Integer.parseInt(request.getParameter("id")));
		group.setC_id(request.getSession().getAttribute("userid")+"");
		Date date = new Date();
		group.setC_date(date);
		group.setName(request.getParameter("name"));
		group.setSize(1);
		GroupDao dao = new GroupDao();
		if(dao.searchGroup(group.getId())) {
//			response.sendRedirect("creatgroup.jsp?error=1");
			return;
		}
		try{
        	BufferedInputStream in = new BufferedInputStream(new FileInputStream("C:\\Users\\jack\\eclipse-workspace\\Web_ClassDesign\\WebContent\\images\\headImage.png")); 
        	String p_headImage = "C:\\Users\\jack\\eclipse-workspace\\Web_ClassDesign\\WebContent\\images\\groupImage\\";
        	p_headImage += group.getId()+".png"; 
        	System.out.println(p_headImage);
    		BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(p_headImage));
    		int i;
    		while((i=in.read())!=-1){  
    			output.write(i);  
			}
    		
    		output.flush();  
    		output.close();
        }catch(Exception e){
        	System.out.println("群头像存取异常");
        }
        String headImage = "images/groupImage/"+group.getId()+".png";
        
        group.setImage(headImage);
		
		if(dao.insertGroup(group)) {
			System.out.println("创建群聊成功");
			
			list.add((String) request.getSession().getAttribute("userid"));
			
			if(str1!=null) {
				for(int i = 0;i<str1.length;i++) {
					System.out.println(" - " + str1[i]);
					list.add(str1[i]);
				}
			}else {
				System.out.println("str1 = null");
			}
			
			if(dao.insertListPersonToGroup(list, group.getId())) {
				System.out.println("群成员加入群——人表成功");
			}else {
				System.out.println("群成员加入群——人表失败");
			}
			
			response.sendRedirect("ContactServlet?id="+request.getSession().getAttribute("userid"));

		}else {
			System.out.println("创建群聊失败");
			response.sendRedirect("creatgroup.jsp?error=2");
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
