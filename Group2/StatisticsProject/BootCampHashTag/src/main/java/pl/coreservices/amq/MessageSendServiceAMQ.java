package pl.coreservices.amq;


import org.apache.activemq.ActiveMQConnectionFactory;

import pl.coreservices.queueServices.MessageSendService;

import javax.jms.*;

public class MessageSendServiceAMQ implements MessageSendService{
	

    Session session;
    Destination destination;

    public MessageSendServiceAMQ() throws JMSException  {

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin","admin","tcp://127.0.0.1:61616");

        Connection connection = connectionFactory.createConnection();
        connection.start();

        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        destination = session.createQueue("HashTags");

    }

    public void sendMessage(String text) {
        MessageProducer producer = null;
        try {
            producer = session.createProducer(destination);
            TextMessage message = session.createTextMessage(text);
            producer.send(message);
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}

