package pl.coreservices.BootCampHashTag;

import javax.jms.JMSException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;


import pl.coreservices.amq.MessageRecieverServiceAMQ;
import pl.coreservices.configuration.WebAppConfiguration;
import pl.coreservices.model.web.NodeServer;
import pl.coreservices.service.FileWatchService;

@SpringBootApplication
@Import({WebAppConfiguration.class})
public class BootCampHashTagApplication {

	public static void main(String[] args) throws JMSException, InterruptedException  {
		ConfigurableApplicationContext ctx = SpringApplication.run(BootCampHashTagApplication.class, args);

		NodeServer.getInstance();

		MessageRecieverServiceAMQ cons = new MessageRecieverServiceAMQ();
		
		FileWatchService fileWatch = new FileWatchService();
		
		// Create two threads:
		Thread thread1 = new Thread() {
		    public void run() {
		       cons.listen();
		    }
		};

		Thread thread2 = new Thread() {
		    public void run() {
		       try {
				fileWatch.watchDirectoryPath();
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    }
		};
		
		// Start the threads
		thread1.start();
		thread2.start();

		// Wait for them both to finish
		thread1.join();
		thread2.join();
		


	}
}
