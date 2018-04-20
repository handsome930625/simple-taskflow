package testone;

import com.charse.taskflow.taskflow.manager.ITaskFlowManager;


import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * description:
 *
 * @author wangyj on 2018/4/14
 */

@ContextConfiguration(locations = {"classpath:AC-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestMain extends AbstractJUnit4SpringContextTests {

    @Autowired
    private ITaskFlowManager taskFlowManager;

    @Test
    public void taskflow() throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("AC-test.xml");
        ITaskFlowManager taskFlowManager = ctx.getBean("taskFlowManager", ITaskFlowManager.class);
        Map<String, Object> params = new HashMap<>();
        Map<String, Object> result = taskFlowManager.executeFlow("taskId", params);
        for (Object o : params.values()) {
            System.out.println(o.toString());
        }
    }
}
