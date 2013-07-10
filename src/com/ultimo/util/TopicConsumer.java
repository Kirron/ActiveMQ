package com.ultimo.util;



import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.Message;

import org.apache.activemq.ActiveMQConnectionFactory;

public class TopicConsumer extends Thread implements Consumer{
	Connection connection = null;
	private boolean Status = true;
	Message Msg ;
	//Session session ;
	public void GetConnection(String user, String password, String url) {
		try
		{
	        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user, password, url);
	        connection = connectionFactory.createConnection();
	        connectionFactory.setUseAsyncSend(true);
	        connection.start();
	 	}
		catch(JMSException ex)
		{
			
		}
	}
	
	
	public void run() {
        try {
        	Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createTopic(subject);
            MessageConsumer consumer = session.createConsumer(destination);
           String str="subject";
           
            while(Status)
            {
            	Msg = consumer.receive(1000);
            	/*if(Msg != null){
            		System.out.println(Msg);
            	}*/
            }
            
            consumer.close();
         	session.close();
        	connection.close();
        	
        }
		catch(JMSException ex)
		{
			 System.out.println(ex.getMessage());
		}
     }
	
	public void SetStatus(boolean St)
	{
		Status = St;
	}
	
	public Message getMsg()
	{
	  return this.Msg;
	}

}
