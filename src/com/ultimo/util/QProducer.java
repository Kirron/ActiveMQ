package com.ultimo.util;


import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class QProducer implements Producer {
	Connection connection = null;
	public void sendMessage(Message Msg) {
		// TODO Auto-generated method stub
		try{
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	        Destination destination = session.createQueue(subject);
	        MessageProducer producer = session.createProducer(destination);
	        producer.send(Msg);
		}
		catch(JMSException ex)
		{
		}
	}
	
	public void sendMessage(TextMessage Msg)
	{
		// TODO Auto-generated method stub
		try{
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	        Destination destination = session.createQueue(subject);  
	        MessageProducer producer = session.createProducer(destination);
	        producer.send(Msg);
	        
	        /*producer.close();
	        session.close();
        	connection.close();*/
		}
		catch(JMSException ex)
		{
		}
	}
	
	public void sendMessage()
	{
		// TODO Auto-generated method stub
		try{
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	        Destination destination = session.createQueue(subject);  
	        MessageProducer producer = session.createProducer(destination);
	        TextMessage Msg = session.createTextMessage("123");
	        producer.send(Msg);
		}
		catch(JMSException ex)
		{
		}
	}
	
	@Override
	public void GetConnection(String user, String password, String url) {
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
