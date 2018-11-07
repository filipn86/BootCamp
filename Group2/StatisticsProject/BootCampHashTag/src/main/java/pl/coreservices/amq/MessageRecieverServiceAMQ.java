package pl.coreservices.amq;

import org.apache.activemq.ActiveMQConnectionFactory;

import pl.coreservices.model.web.Statistic;
import pl.coreservices.model.web.StatisticsList;
import pl.coreservices.queueServices.MessageRecieverService;

import javax.jms.*;


public class MessageRecieverServiceAMQ implements MessageRecieverService  {
	

    Session session;
    Destination destination;
    
    public static final String connectionAddress = "tcp://127.0.0.1:61616";
    public static final String queueName = "HashTags";
    
    

    public MessageRecieverServiceAMQ() throws JMSException {
        // Create a ConnectionFactory
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(connectionAddress);

        // Create a Connection
        Connection connection = connectionFactory.createConnection();
        connection.start();

        //connection2.setExceptionListener(this);

        // Create a Session
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Create the destination (Topic or Queue)
        destination = session.createQueue(queueName);


    }

    public void listen() {
    	
        // Create a MessageConsumer from the Session to the Topic or Queue
        MessageConsumer consumer = null;
        try {
            consumer = session.createConsumer(destination);
            while(true) {
                // Wait for a message
                Message message2 = consumer.receive(1000);

                if (message2 instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message2;
                    String text = textMessage.getText();
                    System.out.println("Received: " + text);
                    addStatistic(text);
                    
                } else {
                    System.out.println("Received: " + message2);
                }
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
    
    private void addStatistic(String hashtag) {
    	
    	StatisticsList stats = StatisticsList.getInstance();
    	
    	if(!(stats.containsStatistic(hashtag))) {
    	stats.addStatistic(new Statistic.Builder()
                .name(hashtag)
                .count(1)
                .build());
    	}
    	else stats.incrementStatistic(hashtag);
    }
}
