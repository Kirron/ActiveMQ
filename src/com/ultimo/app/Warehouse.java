package com.ultimo.app;
import com.ultimo.util.*;
import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Message;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Warehouse {
	
	
		String name;
		int stock;
		String OrderID;
		private static Session session;
		public static String url = "tcp://localhost:61616";
		public static String user =ActiveMQConnection.DEFAULT_USER;
		public static String password =  ActiveMQConnection.DEFAULT_PASSWORD;
		
		TopicProducer warehouseProducer;
		TopicConsumer warehouseConsumer;

		public Warehouse (String name, int stock) {

			this.name = name; 
			this.stock= stock;

		}
		public Warehouse () {
			
			
			
		}

		public void recieveOrder() { 
			
			 warehouseConsumer = new TopicConsumer();
			 warehouseConsumer.GetConnection(user,password,url);
			 warehouseConsumer.start();
			 
			 	
		}

		public void expressIntent() {
			try {
		
				warehouseProducer = new TopicProducer();
				warehouseProducer.GetConnection(user,password,url);
				Message Accepted = session.createMessage();
		   	 	Accepted.setStringProperty("Order ID", OrderId);
		   	 	Accepted.setStringProperty("Intent","Warehouse will complete Order");
		   	 	warehouseProducer.sendMessage(Accepted);
					
			}
			catch (JMSException re) 
			{
			}
		}

		public void canTakeOrder(int quantity) {
			
			 		
			 	if ( quantity <= this.stock) 
				{
				this.stock = stock-quantity;
				Warehouse.orderAcceptable();
				}
			else 
		{
			Warehouse.orderUnacceptable();
			}
			}	
		
		public  void orderAcceptable () {
		try {
			warehouseProducer = new TopicProducer();
			warehouseProducer.GetConnection(user,password,url);
			Message Accepted = session.createMessage();
	   	 	Accepted.setStringProperty("Order ID", OrderId);
	   	    Accepted.setStringProperty("Order Status", "Accepted");
	   	    warehouseProducer.sendMessage(Accepted);
	
		}
		catch (JMSException re) 
		{
		}
		
	}

		
		public void orderUnacceptable () {
			try {
				warehouseProducer = new TopicProducer();
				warehouseProducer.GetConnection(user,password,url);
				Message NotAccepted = session.createMessage();
		   	 	NotAccepted.setStringProperty("Order ID", OrderId);
		   	    NotAccepted.setStringProperty("Order Status", "Not Accepted");
		   	    warehouseProducer.sendMessage(NotAccepted);
			}
			catch (JMSException re) 
			{
			}
		
		}
}
