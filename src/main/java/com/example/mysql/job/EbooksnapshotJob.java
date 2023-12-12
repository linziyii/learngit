package com.example.mysql.job;

import com.example.mysql.service.DocService;
import com.example.mysql.service.EbookSnapShotService;
import com.example.mysql.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class EbooksnapshotJob {
    @Resource
    private SnowFlake snowFlake;
    private static final Logger LOG= LoggerFactory.getLogger(EbooksnapshotJob.class);
    @Resource
    EbookSnapShotService ebookSnapShotService;
    @Scheduled(cron = "5/30 * * * * ?")
    public void cron(){
        MDC.put("LOG_ID", String.valueOf(snowFlake.nextId()));
        LOG.info("生成今日电子书快照开始");
        long start=System.currentTimeMillis();
        ebookSnapShotService.GetEbookShot();
        LOG.info("生成今日电子书快照结束，耗时：{}毫秒",System.currentTimeMillis()-start);
    }
}
