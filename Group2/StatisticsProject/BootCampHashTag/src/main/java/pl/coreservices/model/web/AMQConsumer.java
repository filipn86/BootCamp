package pl.coreservices.model.web;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Configuration
@Profile("AMQ")
public class AMQConsumer implements Consumer {

    Session session;
    Destination destination;

    public AMQConsumer() throws JMSException {
        // Create a ConnectionFactory
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");

        // Create a Connection
        Connection connection = connectionFactory.createConnection();
        connection.start();

        //connection2.setExceptionListener(this);

        // Create a Session
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Create the destination (Topic or Queue)
        destination = session.createQueue("HashTags");



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
                } else {
                    System.out.println("Received: " + message2);
                }
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
