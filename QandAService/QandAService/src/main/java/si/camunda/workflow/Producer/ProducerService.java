package si.camunda.workflow.Producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rabbitmq.client.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;


@Service
public class ProducerService {

    private static String queueName = null; // never used here
    private final static String EXCHANGE_NAME = "direct-exchange";
    private static String routingKey = "spam";

    private static Logger logger = LoggerFactory.getLogger(ProducerService.class);

    public void createQueue(String message) throws Exception
    {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel())
        {
            // channel.queueDeclare(queueName, false, false, false, null);
            channel.exchangeDeclare(EXCHANGE_NAME, "direct");
            channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes("UTF-8"));
        }

    }

}
