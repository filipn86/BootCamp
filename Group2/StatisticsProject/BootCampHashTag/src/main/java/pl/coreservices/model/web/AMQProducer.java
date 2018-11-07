package pl.coreservices.model.web;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Configuration
@Profile("AMQ")
public class AMQProducer implements Producer {

    Session session;
    Destination destination;

    public AMQProducer() throws JMSException {

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin","admin","tcp://127.0.0.1:61616");

        Connection connection = connectionFactory.createConnection();
        connection.start();

        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        destination = session.createQueue("HashTags");


        //FileWatchService fileWatch = new FileWatchService();
        //fileWatch.watchDirectoryPath();
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
