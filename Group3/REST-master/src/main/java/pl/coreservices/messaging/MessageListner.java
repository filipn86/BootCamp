package pl.coreservices.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import pl.coreservices.model.web.StatisticsListEnum;

import javax.jms.JMSException;

@Component
public class MessageListner {
    private static final String ORDER_QUEUE = "order-queue";

    @JmsListener(destination = ORDER_QUEUE)
    public void receiveMessage(final Message<String> message) throws JMSException {
        if (StatisticsListEnum.statlist.contains(message.getPayload())) {
            StatisticsListEnum.statlist.addCount(message.getPayload());
        } else {
            StatisticsListEnum.statlist.addHash(message.getPayload());
        }
        System.out.println("Otrzymano: " + message.getPayload());
    }
//    @JmsListener(destination = "mailbox", containerFactory = "myFactory")
//    public void receiveMessage(String string) {
//        if (StatisticsListEnum.statlist.contains(s)) {
//            StatisticsListEnum.statlist.addCount(s);
//        }
//        else {
//            StatisticsListEnum.statlist.addHash(s);
//        }
//        System.out.println("Odebrano:" + string);
//    }
}
