package com.ultimo.util;


import org.apache.activemq.ActiveMQConnection;

public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
			TopicConsumer TS= new TopicConsumer();
			TopicProducer TP= new TopicProducer();
			QConsumer QS= new QConsumer();
			QProducer QP= new QProducer();
			
			TS.GetConnection(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD,"tcp://localhost:61616" );
	 		TP.GetConnection(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD,"tcp://localhost:61616" );
	 		QS.GetConnection(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD,"tcp://localhost:61616" );
	 		QP.GetConnection(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD,"tcp://localhost:61616" );
	 		
	 		TS.start();
	 		QS.start();
	 		for(int i=0; i<50; i++)
			{
				TP.sendMessage();
	 			QP.sendMessage();
			}
	 		TS.SetStatus(false);
	 		QS.SetStatus(false);
	 		
		
		}

	}
