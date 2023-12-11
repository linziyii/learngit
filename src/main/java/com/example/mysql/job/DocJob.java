package com.example.mysql.job;

import com.example.mysql.service.DocService;
import com.example.mysql.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

@Component
public class DocJob {
    @Resource
    private SnowFlake snowFlake;
    private static final Logger LOG= LoggerFactory.getLogger(DocJob.class);
    @Resource
    DocService docService;
    @Scheduled(cron = "5/30 * * * * ?")
    public void cron(){
        MDC.put("LOG_ID", String.valueOf(snowFlake.nextId()));
        LOG.info("每隔三十秒更新一次电子书信息");
        long start=System.currentTimeMillis();
        docService.updateEbookInfo();
        LOG.info("电子书数据更新结束，耗时：{}毫秒",System.currentTimeMillis()-start);
    }
}
