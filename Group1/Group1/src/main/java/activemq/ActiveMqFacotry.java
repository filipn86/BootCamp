package activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.advisory.DestinationSource;
import org.apache.activemq.command.ActiveMQQueue;

import javax.jms.JMSException;
import java.util.Set;


public class ActiveMqFacotry {


    public void Connection(){
        try {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://DESKTOP-FBL2M22:61616/");

        ActiveMQConnection connection = (ActiveMQConnection) connectionFactory.createConnection();
        DestinationSource ds = connection.getDestinationSource();

        connection.start();

        Set<ActiveMQQueue> queues = ds.getQueues();

        for (ActiveMQQueue activeMQQueue : queues) {
            try {
                System.out.println(activeMQQueue.getQueueName());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
        connection.close();
    } catch (Exception e) {
        e.printStackTrace();
    }}

}
