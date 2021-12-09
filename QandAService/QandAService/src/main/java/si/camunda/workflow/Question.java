package si.camunda.workflow;

import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.engine.ExternalTaskService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;


import javax.inject.Named;


@Named
public class Question implements JavaDelegate {

    @Autowired
    RuntimeService runtimeService;


    String[] answers = new String[]{"login", "no login", "password"};

    public void execute(DelegateExecution execution) throws Exception {

        String question = (String) execution.getVariable("question");
        String bk = execution.getBusinessKey();
        System.out.println("Message is : " + question + " With Business Key : " + bk);

        if (question.contains("login")){
            for (int i = 0; i<answers.length; i++) {
                if (answers[i].contains("login")) {
                    System.out.println("The question contains : " + answers[i]);
                    ProcessInstance startProcess = runtimeService.createMessageCorrelation("externalAnswer")
                            .processInstanceBusinessKey("2")
                            .setVariable("question", answers[i])
                            .correlateStartMessage();
                }
            }

        }

    }
}
