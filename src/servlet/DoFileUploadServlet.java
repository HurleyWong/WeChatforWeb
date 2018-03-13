package servlet;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.FileDao;
import dao.UserDao;
import ov.Mfile;
import ov.User;

/**
 * Servlet implementation class DoFileUploadServlet
 */
@WebServlet("/DoFileUploadServlet")
public class DoFileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoFileUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DoFileUploadServlet");
		System.out.println("c_id:"+request.getSession().getAttribute("userid")+" g_id:"+request.getParameter("g_id"));
		String c_id = (String) request.getSession().getAttribute("userid");
		UserDao dao = new UserDao();
		User user = dao.getUserById(c_id);
		int g_id = Integer.parseInt(request.getParameter("g_id"));
		FileDao dao2 = new FileDao();
		
		boolean isFileUpload = ServletFileUpload.isMultipartContent(request);
		if(isFileUpload){
			try {
				//得到文件上传工厂
				FileItemFactory factory = new DiskFileItemFactory();
				//处理文件上传核心类
				ServletFileUpload fileUpload = new ServletFileUpload(factory);
				//设置文件上传类的编码格式
				fileUpload.setHeaderEncoding("UTF-8");
				// 集合数据 :  FileItem对象 注意: 每一个表单域 对应一个 FileItem对象(封装)
				List<FileItem> fileItemList = fileUpload.parseRequest(request);
				//遍历fileItemList
				for(FileItem item: fileItemList){
				//如果这个文本域是文件类型的
				if(!item.isFormField()){
				//得到文本域的value值，即 路径+文件名
				String value = item.getName();
				//得到文件名
				String fileName = value.substring(value.lastIndexOf("\\")+1);
				//得到上传的文件类型
				String fileType = fileName.substring(fileName.lastIndexOf("."));
				//给文件重新命名 日期+文件名 
				System.out.println("fileName="+fileName);
				/* 	fileName = new Date().getTime() + fileName;  */
				//得到服务器的根路径
				String rootPath = request.getRealPath("/");
				//指定文件存放路径
				String realPath = rootPath+"/"+"upload"+"/"+g_id;
				//定义文件存放的目录，注意 目录也是文件
				File file = new File(realPath);
				Mfile myfile = new Mfile();
				Date date = new Date();
				myfile.setName(fileName);
				myfile.setC_date(date);
				myfile.setType(fileType);
				myfile.setContent(""+realPath.toString()+"");
				myfile.setC_id(user.getId());
				myfile.setG_id(g_id+"");
				myfile.setC_name(user.getNickName());
				dao2.insertFile(myfile);
				System.out.println(realPath);
				//如果目录不存在
				if(!file.isDirectory()){
					//创建文件上传目录
					file.mkdirs();
				}
				File newFile = new File(realPath+"/"+fileName);
				//向newFile文件中写入数据
				item.write(newFile);
				}else {//如果不是文件上传的文本域，把输入的内容显示在页面上
					System.out.println("不是文件上传的文本域");
				}
			}
				response.sendRedirect("GroupInformationServlet?info_type=1&flag=1&g_id="+g_id);
			} catch (FileUploadException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				System.out.println("文件上传失败");
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				System.out.println("文件上传失败");
			}
		}else {
			System.out.println("不是文件上传的类型");
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
