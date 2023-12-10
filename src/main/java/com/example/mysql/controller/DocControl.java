package com.example.mysql.controller;

import com.example.mysql.req.DocQueryReq;
import com.example.mysql.req.DocSaveReq;
import com.example.mysql.resp.DocQueryResp;
import com.example.mysql.resp.CommonResp;
import com.example.mysql.service.DocService;
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
    @PostMapping("/doc/save")
    public CommonResp save(@Valid @RequestBody DocSaveReq docSaveReq){
        CommonResp resp=new CommonResp();
        docService.save(docSaveReq);
        return resp;
    }
    @DeleteMapping("/doc/delete/{idsStr}")
    public CommonResp delete(@PathVariable String idsStr){
        CommonResp resp=new CommonResp();
        List<String> list=Arrays.asList(idsStr.split(","));
        docService.delete(list);
        return resp;
    }
    @GetMapping("/doc/find-content/{id}")
    public CommonResp findcontent(@PathVariable Long id){
        CommonResp<String> resp=new CommonResp();
        String list=docService.findcontent(id);
        resp.setContent(list);
        return resp;
    }
}
