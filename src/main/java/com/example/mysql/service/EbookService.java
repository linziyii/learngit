package com.example.mysql.service;

import com.example.mysql.entity.Ebook;
import com.example.mysql.entity.EbookExample;
import com.example.mysql.mapper.EbookMapper;
import com.example.mysql.req.EbookQueryReq;
import com.example.mysql.req.EbookSaveReq;
import com.example.mysql.resp.EbookQueryResp;
import com.example.mysql.resp.PageResp;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@Service
public class EbookService {
    @Resource
    private EbookMapper ebookMapper;
    public PageResp<EbookQueryResp> list(EbookQueryReq ebookQueryReq){
        EbookExample example=new EbookExample();
        EbookExample.Criteria criteria=example.createCriteria();
        if(!ObjectUtils.isEmpty(ebookQueryReq.getName())){
            criteria.andNameLike("%"+ ebookQueryReq.getName()+"%");  //作用：定义select规则，将其传入mapper的select可起到作用
        }
        PageHelper.startPage(ebookQueryReq.getPage(), ebookQueryReq.getSize());
        List<Ebook> ebookList=ebookMapper.selectByExample(example);
        PageInfo<Ebook> pageInfo=new PageInfo<>(ebookList);
        List<EbookQueryResp> ebookQueryResp =new ArrayList<>();
        for(Ebook ebook:ebookList){
            EbookQueryResp e=new EbookQueryResp();
            BeanUtils.copyProperties(ebook,e);  //复制对象
            ebookQueryResp.add(e);   //添加到列表中
        }
        PageResp<EbookQueryResp> pageResp=new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(ebookQueryResp);
        return pageResp;
    }
    public void save(EbookSaveReq ebookSaveReq){
        Ebook ebook=new Ebook();
        BeanUtils.copyProperties(ebookSaveReq,ebook);
        if(ObjectUtils.isEmpty(ebook.getId())){
            ebookMapper.insert(ebook);
        }else{
            ebookMapper.updateByPrimaryKey(ebook);
        }
    }
}
