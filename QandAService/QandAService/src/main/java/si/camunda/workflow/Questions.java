package si.camunda.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

@Named
public class Questions implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String saveQuestion = "";

        saveQuestion = (String) delegateExecution.getVariable(saveQuestion);

        System.out.println("This is the question from Camunda : " + saveQuestion);

    }


}
