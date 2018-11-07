package pl.coreservices.model.web;

import javax.jms.JMSException;

public interface Producer {

    abstract void  sendMessage(String text);

}