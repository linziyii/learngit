package com.example.mysql.service;

import com.example.mysql.entity.Ebook;
import com.example.mysql.entity.EbookExample;
import com.example.mysql.mapper.EbookMapper;
import com.example.mysql.req.EbookReq;
import com.example.mysql.resp.EbookResp;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@Service
public class EbookService {
    @Resource
    private EbookMapper ebookMapper;
    public List<EbookResp> list(EbookReq ebookReq){
        EbookExample example=new EbookExample();
        EbookExample.Criteria criteria=example.createCriteria();
        //criteria.andNameLike("%"+ebookReq.getName()+"%");  //作用：定义select规则，将其传入mapper的select可起到作用
        List<Ebook> ebookList=ebookMapper.selectByExample(example);
        List<EbookResp> ebookResp=new ArrayList<>();
        for(Ebook ebook:ebookList){
            EbookResp e=new EbookResp();
            BeanUtils.copyProperties(ebook,e);  //复制对象
            ebookResp.add(e);   //添加到列表中
        }
        return ebookResp;
    }
}
