package pl.coreservices.model.web;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Producer {

    Session session;
    Destination destination;

    public Producer() throws JMSException {

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin","admin","tcp://127.0.0.1:61616");

        Connection connection = connectionFactory.createConnection();
        connection.start();

        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        destination = session.createQueue("HashTags");


        //FileWatchService fileWatch = new FileWatchService();
        //fileWatch.watchDirectoryPath();
    }

    public void sendMessage(String text) throws JMSException {
        MessageProducer producer = session.createProducer(destination);
        TextMessage message = session.createTextMessage(text);
        producer.send(message);
    }
}
