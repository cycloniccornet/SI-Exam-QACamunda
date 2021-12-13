package si.camunda.workflow;

import org.apache.tomcat.jni.Time;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import si.camunda.workflow.Consumer.ConsumerService;
import si.camunda.workflow.Producer.ProducerService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;


import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Named
public class Question implements JavaDelegate {

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    ProducerService producerService;

    @Autowired
    ConsumerService consumerService;

    private static final Logger logger = LoggerFactory.getLogger(ConsumerService.class);

    String[] answers = new String[]{"login", "no login", "password"};

    public void execute(DelegateExecution execution) throws Exception {

        String question = (String) execution.getVariable("question");
        String bk = execution.getBusinessKey();
        System.out.println("Message is : " + question + " With Business Key : " + bk);

        producerService.sendMessage(question);


/*
        ProcessInstance startProcess = runtimeService.createMessageCorrelation("externalAnswer")
                .processInstanceBusinessKey(bk)
                .setVariable("question", answers[i])
                .correlateStartMessage();


 */

        /*  TODO - Is this nessesary?
        if (question.contains("login")){
            for (int i = 0; i<answers.length; i++) {
                if (answers[i].contains("login")) {
                    System.out.println("The question contains : " + answers[i]);
                    ProcessInstance startProcess = runtimeService.createMessageCorrelation("externalAnswer")
                            .processInstanceBusinessKey(bk)
                            .setVariable("question", answers[i])
                            .correlateStartMessage();
                }
            }

        }

         */

    }


    List<String> similarQuestionList = new ArrayList<>();

    @KafkaListener(topics = "similar-questions", groupId = "questionable-group")
    public void getSimilarQuestions(String answers){


        System.out.println("Consumed message : " + answers);
        logger.info("&&& Message [{}] consumed", answers);

        similarQuestionList.add(answers);
        System.out.println(similarQuestionList);

        for (int i = 0; i < similarQuestionList.size(); i++) {
            ProcessInstance startProcess = runtimeService.createMessageCorrelation("externalAnswer")
                    .processInstanceBusinessKey("1")
                    .setVariable("question", similarQuestionList.get(i))
                    .correlateStartMessage();
        }
    }
}
