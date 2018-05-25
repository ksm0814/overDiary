package overdiary.helper;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import overdiary.service.SimpleService;

import javax.annotation.Resource;


public class SimpleJob implements Job{

    @Resource(name = "simpleService")
    private SimpleService simpleService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        simpleService.processData();
    }
}