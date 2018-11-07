package activemq;

import com.coreservices.database.Database;
import com.coreservices.model.Statistic;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Consumer {

    private static String subject = "twitter";

    public String consumer() throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://10.11.60.144:61616");
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
        } catch (JMSException e) {
            e.printStackTrace();
        }
        try {
            connection.start();
        } catch (JMSException e) {
            e.printStackTrace();
        }

        Session session = null;
        try {
            session = connection.createSession(false,
                    Session.AUTO_ACKNOWLEDGE);
        } catch (JMSException e) {
            e.printStackTrace();
        }

        Destination destination = session.createQueue(subject);

        MessageConsumer consumer = session.createConsumer(destination);

        Message message = consumer.receive();


        Database database = new Database();
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            System.out.println("Received '" + textMessage.getText()
                    + "'");

        }

        connection.close();
        return null;

    }
}
