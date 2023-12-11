package com.example.mysql.service;

import com.example.mysql.websocket.WebSocketServer;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class WsService {
    @Resource
    private WebSocketServer webSocketServer;
    @Async
    public void senfInfo(String name,String logid){
        MDC.put("LOG_ID",logid);
        webSocketServer.sendInfo("【"+name+"】被点赞!");
    }
}
