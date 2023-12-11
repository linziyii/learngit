package com.example.mysql.service;

import com.example.mysql.config.WebSocketConfig;
import com.example.mysql.entity.Content;
import com.example.mysql.entity.ContentExample;
import com.example.mysql.entity.Doc;
import com.example.mysql.entity.DocExample;
import com.example.mysql.exception.BusinessException;
import com.example.mysql.exception.BusinessExceptionCode;
import com.example.mysql.mapper.ContentMapper;
import com.example.mysql.mapper.DocMapper;
import com.example.mysql.mapper.DocMapperCust;
import com.example.mysql.req.DocQueryReq;
import com.example.mysql.req.DocSaveReq;
import com.example.mysql.resp.DocQueryResp;
import com.example.mysql.resp.PageResp;
import com.example.mysql.util.RedisUtil;
import com.example.mysql.util.RequestContext;
import com.example.mysql.util.SnowFlake;
import com.example.mysql.websocket.WebSocketServer;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Select;
import org.jboss.logging.MDC;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;

@Service
public class DocService {
    @Resource
    private DocMapper docMapper;
    @Resource
    private ContentMapper contentMapper;
    @Resource
    private DocMapperCust docMapperCust;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    WsService wsService;
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
    @Transactional
    public void save(DocSaveReq docSaveReq){
        DocExample docExample=new DocExample();
        DocExample.Criteria criteria=docExample.createCriteria();
        criteria.andNameEqualTo(docSaveReq.getName());
        Doc doc=new Doc();
        Doc doc1=new Doc();
        Content content=new Content();
        BeanUtils.copyProperties(docSaveReq,doc);
        BeanUtils.copyProperties(docSaveReq,content);
        doc1=docMapper.selectByPrimaryKey(docSaveReq.getId());
        if(ObjectUtils.isEmpty(doc1)){
            doc.setViewCount(0);
            doc.setVoteCount(0);
            docMapper.insert(doc);
            List<Doc> i=docMapper.selectByExample(docExample);
            content.setId(i.get(0).getId());
            contentMapper.insert(content);
        }else{
            docMapper.updateByPrimaryKey(doc);
            contentMapper.updateByPrimaryKeySelective(content);
        }
    }
    public void delete(Long id){
        docMapper.deleteByPrimaryKey(id);
    }
    public void delete(List<String> ids){
        DocExample docExample=new DocExample();
        DocExample.Criteria criteria=docExample.createCriteria();
        criteria.andIdIn(ids);
        docMapper.deleteByExample(docExample);
    }
    public List<DocQueryResp> selectall(Long ebookid){
        DocExample example=new DocExample();
        example.setOrderByClause("sort asc");
        example.createCriteria().andEbookIdEqualTo(ebookid);
        List<Doc> docList=docMapper.selectByExample(example);
        List<DocQueryResp> docQueryResp =new ArrayList<>();
        for(Doc doc:docList){
            DocQueryResp e=new DocQueryResp();
            BeanUtils.copyProperties(doc,e);  //复制对象
            docQueryResp.add(e);   //添加到列表中
        }
        return docQueryResp;
    }
    public String findcontent(Long id){
        Content content=contentMapper.selectByPrimaryKey(id);

        //增加文档阅读数
        docMapperCust.increaseViewCount(id);
        if(ObjectUtils.isEmpty(content)){
            return  "";
        }
        else{
            return content.getContent();
        }
    }
    public void vote(Long id){
        String key= RequestContext.getRemoteAddr();
        if(redisUtil.validateRepeat("DOC_VOTE_"+id+"key",3600*24)){
            docMapperCust.increaseVoteCount(id);
        }else{
            throw new BusinessException(BusinessExceptionCode.VOTE_REPEAT);
        }
        Doc docDb = docMapper.selectByPrimaryKey(id);
        String logid= (String) MDC.get("LOG_ID");
        wsService.senfInfo(docDb.getName(),logid);
    }
    public void updateEbookInfo(){
        docMapperCust.updateEbookInfo();
    }
}
