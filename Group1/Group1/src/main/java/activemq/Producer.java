package activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Producer {


    private static String subject = "twitter";
    public void producer(String text) throws JMSException {

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://10.11.60.144:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false,
                Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createQueue(subject);

        MessageProducer producer = session.createProducer(destination);
        TextMessage message = session.createTextMessage(text);
        producer.send(message);
        System.out.println("Sent '" + message.getText() + "'");

        connection.close();
    }
}
