package si.camunda.workflow.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConsumerService {
/*
    private static final Logger logger = LoggerFactory.getLogger(ConsumerService.class);
    List<String> similarQuestionList = new ArrayList<>();

    @KafkaListener(topics = "similar-questions", groupId = "questionable-group")
    public List<String> getSimilarQuestions(String answers){


        System.out.println("Consumed message : " + answers);
        logger.info("&&& Message [{}] consumed", answers);

        similarQuestionList.add(answers);
        System.out.println(similarQuestionList);

        return similarQuestionList;
    }

 */
}
