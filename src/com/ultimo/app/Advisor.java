package com.ultimo.app;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;

import com.ultimo.util.QConsumer;
import com.ultimo.util.QProducer;
import com.ultimo.util.TopicConsumer;
import com.ultimo.util.TopicProducer;

public class Advisor {

	public static String user= ActiveMQConnection.DEFAULT_USER;
	public static String password= ActiveMQConnection.DEFAULT_PASSWORD;
	public static String url="tcp://localhost:61616";
	private static Session session;
	String name;
	int stock;
	String OrderID;
	int quantity;
	
	TopicConsumer AdvisorTopicConsumer;
	TopicProducer AdvisorTopicProducer;
	QConsumer AdvisorQConsumer; 
	QProducer AdvisorQProducer;
	
	public Advisor() {
			
	}

	public void Listener() { 
		
		 AdvisorTopicConsumer = new TopicConsumer();
		 AdvisorTopicConsumer.GetConnection(user,password,url);
		 AdvisorTopicConsumer.start();
		 Message msg= AdvisorTopicConsumer.getMsg();
		 new Advisor().onMessage(msg);
 	
	}
	
	public void onMessage (Message msg) {
		
		//Implementation to call the pick warehouse method. 
		
	}
	
	public void warehouseStatus() {
		try {
			
			AdvisorTopicProducer = new TopicProducer();
			AdvisorTopicProducer.GetConnection(user,password,url);
			Message Accepted = session.createMessage();
	   	 	Accepted.setStringProperty("Order ID", OrderID);
	   	 	Accepted.setStringProperty("Intent","Warehouse will complete Order");
	   
	   	 		
		}
		catch (JMSException re) 
		{
		}
	}
	}
	
	
}





// listener, pick the warehouse, sendMessage


