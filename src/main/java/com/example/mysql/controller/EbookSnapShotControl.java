package com.example.mysql.controller;

import com.example.mysql.resp.CommonResp;
import com.example.mysql.resp.StatisticResp;
import com.example.mysql.service.EbookSnapShotService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/ebook-snapshot")
public class EbookSnapShotControl {
    @Resource
    EbookSnapShotService ebookSnapShotService;
    @GetMapping("/get-statistic")
    public CommonResp getStatistic(){
        List<StatisticResp> statisticRespList=ebookSnapShotService.getStatistic();
        CommonResp commonResp=new CommonResp();
        commonResp.setContent(statisticRespList);
        return commonResp;
    }
    @GetMapping("/get-30-statistic")
    public CommonResp get30Statistic(){
        List<StatisticResp> statisticRespList=ebookSnapShotService.get30Statistic();
        CommonResp commonResp=new CommonResp();
        commonResp.setContent(statisticRespList);
        return commonResp;
    }
}
