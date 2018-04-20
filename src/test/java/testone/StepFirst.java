package testone;

import com.charse.taskflow.taskflow.ITaskHandler;
import com.charse.taskflow.taskflow.TaskFlowContext;
import com.charse.taskflow.transaction.annotation.Commit;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * description:
 *
 * @author wangyj on 2018/4/14
 */
public class StepFirst implements ITaskHandler {

    private StepSecond stepSecond;

    @Override
    public Object handle(Object params, TaskFlowContext taskFlowContext) throws Exception {
        Map<String, Object> map = (Map<String, Object>) params;
        stepSecond.handle(params,taskFlowContext);
        map.put("step_first", 1);
        return "R1";
    }

    public void setStepSecond(StepSecond stepSecond) {
        this.stepSecond = stepSecond;
    }
}
