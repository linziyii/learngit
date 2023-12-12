package com.example.mysql.service;

import com.example.mysql.mapper.Ebook_snapshotMapperCust;
import com.example.mysql.resp.StatisticResp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service

public class EbookSnapShotService {
    @Resource
    Ebook_snapshotMapperCust ebook_snapshotMapperCust;
    public void GetEbookShot(){
        ebook_snapshotMapperCust.getEbookshot();
    }
    public List<StatisticResp> getStatistic(){
        return ebook_snapshotMapperCust.getStatistic();
    }
    public List<StatisticResp> get30Statistic(){
        return ebook_snapshotMapperCust.get30Statistic();
    }
}
