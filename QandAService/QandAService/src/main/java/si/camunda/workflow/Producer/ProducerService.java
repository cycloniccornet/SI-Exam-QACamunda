package si.camunda.workflow.Producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class ProducerService {

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
}
