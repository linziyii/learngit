package com.example.mysql.mapper;

import com.example.mysql.entity.Ebook_snapshot;
import com.example.mysql.entity.Ebook_snapshotExample;
import com.example.mysql.resp.StatisticResp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Ebook_snapshotMapperCust {
    public void getEbookshot();
    public List<StatisticResp> getStatistic();
    public List<StatisticResp> get30Statistic();
}