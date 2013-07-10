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

		public Warehouse (String name, int stock) {

			this.name = name; 
			this.stock= stock;

		}
		public Warehouse () {
			
		}

		public String recieveOrder() { 
			try{
			 	ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, ActiveMQConnection.DEFAULT_BROKER_URL);
	            Connection connection = connectionFactory.createConnection();
	            connection.start();
	            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	            Destination destination = session.createTopic("Warehouse Confirmation");
	            MessageConsumer consumer = session.createConsumer(destination);
	            Message recieved = consumer.receive();
	            String OrderID = recieved.getStringProperty("Order ID").toString();
	            return OrderID;
	            new Warehouse.
				}
			
			catch(JMSException ex)
			{
				return ex.toString();
			}
		}

		public void expressIntent() {
			try {
				ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, ActiveMQConnection.DEFAULT_BROKER_URL);
				Connection connection = null;
		        connection = connectionFactory.createConnection();
		        connection.start();
		        connectionFactory.setUseAsyncSend(false);
		         session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		        Destination destination = session.createQueue("Intent Queue");
		        MessageProducer producer = session.createProducer(destination);
			    producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		   	 	Message Accepted = session.createMessage();
		   	 	Accepted.setStringProperty("Order ID", OrderId);
		   	 	Accepted.setStringProperty("Intent","Warehouse will complete Order");
	
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
		
		
		public static void orderAcceptable () {
		try {
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, ActiveMQConnection.DEFAULT_BROKER_URL);
			Connection connection = null;
	        connection = connectionFactory.createConnection();
	        connection.start();
	        connectionFactory.setUseAsyncSend(false);
	         session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	        Destination destination = session.createTopic("Warehouse Confirmation");
	        MessageProducer producer = session.createProducer(destination);
		    producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
	   	 	Message Accepted = session.createMessage();
	   	 	Accepted.setStringProperty("Order ID", OrderId);
	   	    Accepted.setStringProperty("Order Status", "Accepted");
	
		}
		catch (JMSException re) 
		{
		}
		
	}

		
		public static void orderUnacceptable () {
			try {
				ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, ActiveMQConnection.DEFAULT_BROKER_URL);
				Connection connection = null;
		        connection = connectionFactory.createConnection();
		        connection.start();
		        connectionFactory.setUseAsyncSend(false);
		         session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		        Destination destination = session.createTopic("Warehouse Confirmation");
		        MessageProducer producer = session.createProducer(destination);
			    producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		   	 	Message NotAccepted = session.createMessage();
		   	 	NotAccepted.setStringProperty("Order ID", OrderId);
		   	    NotAccepted.setStringProperty("Order Status", "Not Accepted");
		   	    producer.send(NotAccepted);
			}
			catch (JMSException re) 
			{
			}
		
		}
			
		/* public static  void warehouseProducer () {
			
			try{	
				ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, ActiveMQConnection.DEFAULT_BROKER_URL);
				Connection connection = null;
		        connection = connectionFactory.createConnection();
		        connection.start();
		        connectionFactory.setUseAsyncSend(false);
		         session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		        Destination destination = session.createTopic("Warehouse Confirmation");
		        MessageProducer producer = session.createProducer(destination);
			    producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			       
			    

			}
			catch(JMSException ex)
			{
			}
			
		}
		*/

		/* public void warehouseConsumer () {

			try{
			 	ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, ActiveMQConnection.DEFAULT_BROKER_URL);
	            Connection connection = connectionFactory.createConnection();
	            connection.start();
	             session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	            Destination destination = session.createTopic("Warehouse Confirmation");
	            MessageConsumer consumer = session.createConsumer(destination);
			}
			
			catch(JMSException ex)
			{
			}
		}
		
		*/

}
