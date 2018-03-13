package util;

import java.util.HashMap;

public class MessageUtil {
	public static HashMap<String,String> getMessage(String msg) {
		//数据处理工具类
		HashMap<String,String> map = new HashMap<String,String>();
//		String msgString  = msg.toString();
		System.out.println("message == " + msg.toString());
		
		String m[] = msg.split(",");
		
		System.out.println("chat_obj == " +  m[0]);
		System.out.println("chat_type == " +  m[1]);
		System.out.println("con_message == " +  m[2]);
		map.put("chat_obj", m[0]);
		map.put("chat_type", m[1]);
		map.put("con_message", m[2]);
		return map;
	}
}
