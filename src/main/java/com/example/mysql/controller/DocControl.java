package com.example.mysql.controller;

import com.example.mysql.req.DocQueryReq;
import com.example.mysql.req.DocSaveReq;
import com.example.mysql.resp.DocQueryResp;
import com.example.mysql.resp.CommonResp;
import com.example.mysql.service.DocService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping
public class DocControl {
    @Autowired
    private DocService docService;

    @GetMapping("/doc/list/{ebookid}")
    public CommonResp query(@PathVariable Long ebookid){
        CommonResp<List<DocQueryResp>> resp=new CommonResp();
        List<DocQueryResp> list=docService.selectall(ebookid);
        resp.setContent(list);
        return resp;
    }
    @ApiOperation(value = "文档更新",notes = "参数要求：文档名称，电子书id，父文档，文档序列号")
    @PostMapping("/doc/save")
    public CommonResp save(@Valid @RequestBody DocSaveReq docSaveReq){
        CommonResp resp=new CommonResp();
        docService.save(docSaveReq);
        return resp;
    }
    @ApiOperation(value = "文档删除",notes = "参数要求：文档序列号（删除文档序列需要序列号字符串，以逗号隔开）")
    @DeleteMapping("/doc/delete/{idsStr}")
    public CommonResp delete(@PathVariable String idsStr){
        CommonResp resp=new CommonResp();
        List<String> list=Arrays.asList(idsStr.split(","));
        docService.delete(list);
        return resp;
    }
    @ApiOperation(value = "查询文档文本内容",notes = "参数要求：传入文档id")
    @GetMapping("/doc/find-content/{id}")
    public CommonResp findcontent(@PathVariable Long id){
        CommonResp<String> resp=new CommonResp();
        String list=docService.findcontent(id);
        resp.setContent(list);
        return resp;
    }
    @ApiOperation(value = "查询文档",notes = "参数要求：传入文档电子书")
    @GetMapping("/doc/all/{ebookId}")
    public CommonResp all(@PathVariable Long ebookId){
        CommonResp<List<DocQueryResp>> commonResp=new CommonResp<>();
        List<DocQueryResp>list=docService.selectall(ebookId);
        commonResp.setContent(list);
        return commonResp;
    }
}
