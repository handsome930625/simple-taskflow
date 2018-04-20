package testone;

import com.charse.taskflow.taskflow.ITaskHandler;
import com.charse.taskflow.taskflow.TaskFlowContext;
import com.charse.taskflow.transaction.annotation.Commit;

import java.util.Map;

/**
 * description:
 *
 * @author wangyj on 2018/4/14
 */
public class StepSecond implements ITaskHandler {

    @Commit()
    @Override
    public Object handle(Object params, TaskFlowContext taskFlowContext) throws Exception {
        Map<String, Object> map = (Map<String, Object>) params;
        map.put("step_second", 2);
        return null;
    }
}
