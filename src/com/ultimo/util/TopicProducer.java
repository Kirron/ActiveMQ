package com.ultimo.util;



import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class TopicProducer  implements Producer
{
	private Connection connection = null;
	private Session session;
	
	public void sendMessage(Message Msg) {
			try{
				session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		        Destination destination = session.createTopic(subject);
		        MessageProducer producer = session.createProducer(destination);
		        producer.send(Msg);
		       // connection.close();
			}
			catch(JMSException ex)
			{
			}
		}
		public void sendMessage(TextMessage Msg) {
			try{
				session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		        Destination destination = session.createTopic(subject);
		        MessageProducer producer = session.createProducer(destination);
		        producer.send(Msg);
		       
		    	
			}
			catch(JMSException ex)
			{
			}
		}
		
		public void sendMessage() {
			
			try{
				session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		        Destination destination = session.createTopic(subject);
		        MessageProducer producer = session.createProducer(destination);
		        TextMessage Msg = session.createTextMessage("123");
		        producer.send(Msg);
		        //connection.close();
			}
			catch(JMSException ex)
			{
				System.out.println(ex.getMessage());
			}
			/*finally
			{
				try{
					
					connection.close();
				}
				catch(Throwable ignore)
				{
				}
			}*/
			
		}
		
		@Override
		public void GetConnection(String user, String password, String url)
		{
			try{	
				ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user, password, url);
		        connection = connectionFactory.createConnection();
		        connectionFactory.setUseAsyncSend(false);
		        connection.start();
			}
			catch(JMSException ex)
			{
			}
		}
		
		
			
}
