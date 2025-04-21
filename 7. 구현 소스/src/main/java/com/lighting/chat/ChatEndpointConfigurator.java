package com.lighting.chat;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

public class ChatEndpointConfigurator extends ServerEndpointConfig.Configurator {
    @Override
    public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request, HandshakeResponse response) {
        // HandshakeRequest에서 HttpSession 가져오기
        HttpSession httpSession = (HttpSession) request.getHttpSession();
        // HttpSession을 WebSocket Session의 userProperties에 저장
        config.getUserProperties().put("httpSession", httpSession);
    }
}
