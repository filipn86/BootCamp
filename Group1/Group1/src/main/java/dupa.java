import activemq.ActiveMqFacotry;
import activemq.Consumer;
import activemq.Producer;

import javax.jms.JMSException;

public class dupa {
    public static void main(String[] args) throws JMSException {
      Producer producer = new Producer();
      Consumer customer = new Consumer();

      customer.consumer();
    }
}
