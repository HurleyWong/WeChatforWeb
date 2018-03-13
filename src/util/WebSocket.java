package util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.websocket.EncodeException;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import dao.GroupDao;
import dao.MessageDao;
import dao.UserDao;
import net.sf.json.JSONArray;
import ov.Group;
import ov.SendMessage;
import ov.User;

//配置Configurator，使得EndpointConfig的config携带HttpSession
@ServerEndpoint(value = "/websocket" , configurator = HttpSessionConfigurator.class)
public class WebSocket extends HttpServlet{
	/**
	 * 
	 */
	ArrayList<String> record = new ArrayList<String>();
	
	
	    private static final long serialVersionUID = 1L;

		//静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
		private static int onlineCount = 0;

		//concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
		private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<WebSocket>();

		//与某个客户端的连接会话，需要通过它来给客户端发送数据
		private Session session;
		
		//登录的用户id
		private String user;
		
		//客户端的session
		private HttpSession httpSession;
		/**
		 * 连接建立成功调用的方法
		 * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
		 */
		@OnOpen
		public void onOpen(Session session, EndpointConfig config){
			System.out.println("触发连接建立成功调用的方法");
			this.session = session;
			//将httpsession注入到该线程的属性中
			this.httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
			this.user = httpSession.getAttribute("userid").toString();
			
			System.out.println("当前登录的用户id为"+user);
			if (isLogin(user)) {
				System.out.println("当前用户已经加入！当前在线人数为" + getOnlineCount());
			}else{
				webSocketSet.add(this);     //加入set中
				addOnlineCount();           //在线数加1
				System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
			}
		}

		/**
		 * 连接关闭调用的方法
		 */
		@OnClose
		public void onClose(){
			System.out.println("触发连接关闭调用的方法");
			webSocketSet.remove(this);  //从set中删除
			subOnlineCount();           //在线数减1
			System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
		}

		/**
		 * 收到客户端消息后调用的方法
		 * @param message 客户端发送过来的消息
		 * @param session 可选的参数
		 */
		@OnMessage
		public void onMessage(String message, Session session) {

			System.out.println("触发收到客户端消息后调用的方法");
			HashMap<String,String> map = MessageUtil.getMessage(message);
			String chat_obj = map.get("chat_obj");
			String chat_type = map.get("chat_type");
			String con_message = map.get("con_message");
			System.out.println("chat_obj="+chat_obj+" chat_type="+chat_type+" con_message="+con_message);
			MessageDao dao = new MessageDao();
			if(chat_type.equals("1")) {
				System.out.println("接收到群消息");
				dao.addGroupMessage(this.user, chat_obj, con_message);
				toGroupMessage(chat_obj,chat_type,con_message);
			}else {
				System.out.println("接收到个人消息");
				dao.addPersonMessage(this.user, chat_obj, con_message);
				toPersonMessage(this.user, chat_obj, con_message);
			}
			/*String fromname = map.get("froName");//?
			String toname = map.get("toName");
			String content = map.get("content");
			System.out.println("来自客户端" + user + "的消息:" + message);
			System.out.println("--"+"User == "+user+"fromname == "+fromname );
			if (this.user.equals(fromname)) {
				try {
					this.sendMessage("客户端异常");
					onClose();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (isLogin(toname)) {
				//发送给指定好友的消息
				for(WebSocket item: webSocketSet){
					try {
						if (item.user.equals(toname)) {
							this.sendMessage("发送给"+toname+"的消息:"+content);
							item.sendMessage("来自"+user+"的消息:"+content);
						}
					} catch (IOException e) {
						e.printStackTrace();
						continue;
					}
				}
			}else{
				try {
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}*/
			
		}

		/**
		 * 发生错误时调用
		 * @param session
		 * @param error
		 */
		@OnError
		public void onError(Session session, Throwable error){
			System.out.println("发生错误");
			error.printStackTrace();
		}

		public boolean isLogin(String user) {
			//判定是否已经登录
			for(WebSocket item: webSocketSet){
				if (item.user.equals(user)) {
					item = this;
					return true;
				}
			}	
			return false;
		}
		
		public boolean addPMessage(String m_id,String t_id) {
			
			return false;
		}
		
		public boolean addGMessage(String g_id) {
			
			return false;
		}
		/**
		 * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
		 * @param message
		 * @throws IOException
		 */
		public void sendMessage(ArrayList<String> list) throws IOException{
//			this.session.getAsyncRemote().sendObject(JSONArray.fromObject(list).toString());
			this.session.getAsyncRemote().sendText(list.get(3));
		}

		public static synchronized int getOnlineCount() {
			return onlineCount;
		}

		public static synchronized void addOnlineCount() {
			WebSocket.onlineCount++;
		}

		public static synchronized void subOnlineCount() {
			WebSocket.onlineCount--;
		}

		public HttpSession getHttpSession() {
			return httpSession;
		}

		public void setHttpSession(HttpSession httpSession) {
			this.httpSession = httpSession;
		}

		public void toGroupMessage(String chat_obj, String chat_type, String con_message) {
			GroupDao dao = new GroupDao();
			int g_id = Integer.parseInt(chat_obj);
			ArrayList<User> list = dao.getGroupPerson(g_id);
			for(int i=0;i<list.size();i++) {
				if(list.get(i).getId().equals(this.user)) {
					list.remove(i);
				}
			}
			for(User u:list) {

					for(WebSocket item: webSocketSet){
						try {
							if (item.user.equals(u.getId())) {
							
								ArrayList<String> datas = new ArrayList<String>();
								datas.add(this.user);
								Group group = dao.getGroupById(g_id);
								datas.add(group.getName());
								datas.add(group.getImage());
								datas.add(con_message);
								System.out.println("即将发送消息:"+datas.toString());

								item.sendMessage(datas);
							}
						} catch (IOException e) {
							e.printStackTrace();
							continue;
						}
					}

			}
		}
		public void toPersonMessage(String user2, String chat_obj, String con_message) {
			for(WebSocket item: webSocketSet){
				try {
					if (item.user.equals(chat_obj)) {
//						this.sendMessage("发送给"+user.getId()+"的消息:"+con_message);								
						ArrayList<String> datas = new ArrayList<String>();
						datas.add("");
						datas.add("");
						datas.add("");
						datas.add(con_message);

						item.sendMessage(datas);
						System.out.println(user2+" 给 "+chat_obj+"发送了:"+con_message);
					}
				} catch (IOException e) {
					e.printStackTrace();
					continue;
				}
			}
			
		}
}

