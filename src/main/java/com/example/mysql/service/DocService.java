package com.example.mysql.service;

import com.example.mysql.entity.Doc;
import com.example.mysql.entity.DocExample;
import com.example.mysql.mapper.DocMapper;
import com.example.mysql.req.DocQueryReq;
import com.example.mysql.req.DocSaveReq;
import com.example.mysql.resp.DocQueryResp;
import com.example.mysql.resp.PageResp;
import com.example.mysql.util.SnowFlake;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class DocService {
    @Resource
    private DocMapper docMapper;
    public PageResp<DocQueryResp> list(DocQueryReq docQueryReq){
        DocExample example=new DocExample();
        DocExample.Criteria criteria=example.createCriteria();
        if(!ObjectUtils.isEmpty(docQueryReq.getName())){
            criteria.andNameLike("%"+ docQueryReq.getName()+"%");  //作用：定义select规则，将其传入mapper的select可起到作用
        }
        PageHelper.startPage(docQueryReq.getPage(), docQueryReq.getSize());
        List<Doc> docList=docMapper.selectByExample(example);
        PageInfo<Doc> pageInfo=new PageInfo<>(docList);
        List<DocQueryResp> docQueryResp =new ArrayList<>();
        for(Doc doc:docList){
            DocQueryResp e=new DocQueryResp();
            BeanUtils.copyProperties(doc,e);  //复制对象
            docQueryResp.add(e);   //添加到列表中
        }
        PageResp<DocQueryResp> pageResp=new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(docQueryResp);
        return pageResp;
    }
    public void save(DocSaveReq docSaveReq){
        Doc doc=new Doc();
        SnowFlake snowFlake=new SnowFlake(1,1);
        BeanUtils.copyProperties(docSaveReq,doc);
        if(ObjectUtils.isEmpty(doc.getId())){
            doc.setId(snowFlake.nextId());
            docMapper.insert(doc);
        }else{
            docMapper.updateByPrimaryKey(doc);
        }
    }
    public void delete(Long id){
        docMapper.deleteByPrimaryKey(id);
    }
    public List<DocQueryResp> selectall(DocQueryReq docQueryReq){
        DocExample example=new DocExample();
        example.setOrderByClause("sort asc");
        List<Doc> docList=docMapper.selectByExample(example);
        List<DocQueryResp> docQueryResp =new ArrayList<>();
        for(Doc doc:docList){
            DocQueryResp e=new DocQueryResp();
            BeanUtils.copyProperties(doc,e);  //复制对象
            docQueryResp.add(e);   //添加到列表中
        }
        return docQueryResp;
    }
}
