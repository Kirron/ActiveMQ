package com.ultimo.util;

import org.apache.activemq.ActiveMQConnection;


public interface Consumer{
		
		public static String user = ActiveMQConnection.DEFAULT_USER;
		public static String password = ActiveMQConnection.DEFAULT_PASSWORD;
		public static String url = "tcp://localhost:61616";
		public static String subject = "subject";
		public void GetConnection(String user,String password,String url);
		//public void ReceiveMessage();
		
		

}
