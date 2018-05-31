package overdiary.helper;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import overdiary.service.CrawlService;

import javax.annotation.Resource;


public class RankCrawler implements Job{

    // 실시간 알림
    // 배포 자동화
    // 캐싱

    @Resource(name = "crawlService")
    private CrawlService crawlService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        try {
            crawlService.saveRankBoard();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}