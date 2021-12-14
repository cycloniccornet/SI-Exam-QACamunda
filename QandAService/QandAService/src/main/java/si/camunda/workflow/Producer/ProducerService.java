package si.camunda.workflow.Producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@Service
public class ProducerService {

    private static Logger logger = LoggerFactory.getLogger(ProducerService.class);

    private static String queueName = null; // never used here
    private final static String EXCHANGE_NAME = "direct-exchange-camunda";
    private static String routingKey = "question";

//        String message = args.length < 1 ? "Hello World!" : String.join(" ", args);
  //      createQueue(message);
    //    System.out.println(" [3] Sent routing key '" + routingKey + "' for message '" + message + "'");

    public void createQueue(String message) throws Exception
    {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        logger.info("This is the message : " + message + " - Now sending through RabbitMQ Producer with exchange name : " + EXCHANGE_NAME);
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel())
        {
            channel.exchangeDeclare(EXCHANGE_NAME, "direct");
            channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes("UTF-8"));
        }

    }








/*
    //Sending to SI-Exam-QACategorization ConsumerService to search for similar topics.
    private static final String TOPIC = "question-messages";

    private static Logger logger = LoggerFactory.getLogger(ProducerService.class);

    @Autowired
    private KafkaTemplate<String, String> template;

    public void sendMessage(String message) {
        template.send(TOPIC, message);
        logger.info("### ProducerService sends message [{}]", message);
        template.flush();
    }


 */


}
