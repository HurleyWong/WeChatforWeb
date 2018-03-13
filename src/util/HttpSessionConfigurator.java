package util;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

public class HttpSessionConfigurator extends Configurator{
	//通过该类将httpSession注入到ServerEndpointConfig中的sec中
	@Override
	public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
			HttpSession httpSession = (HttpSession) request.getHttpSession();
			sec.getUserProperties().put(HttpSession.class.getName(), httpSession);
	}
}
