package com.ultimo.util;

import javax.jms.Message;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;


public interface Producer{
//Create GetConnection	
	//
    public static String url = "tcp://localhost:61616";
    public static String user =ActiveMQConnection.DEFAULT_USER;
    public static String password =  ActiveMQConnection.DEFAULT_PASSWORD;
    public static String subject = "subject";
    public static String WEBSOCKET_URL = "ws://localhost:8001/jms";
	 public void GetConnection(String user,String password,String url);
	 //public void sendMessage(Message Msg, Session session, MessageProducer producer);
	 public void sendMessage(TextMessage Msg);
	 public void sendMessage(Message Msg);   
	 public void sendMessage();
}