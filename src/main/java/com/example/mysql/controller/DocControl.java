package com.example.mysql.controller;

import com.example.mysql.req.DocQueryReq;
import com.example.mysql.req.DocSaveReq;
import com.example.mysql.resp.DocQueryResp;
import com.example.mysql.resp.CommonResp;
import com.example.mysql.service.DocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping
public class DocControl {
    @Autowired
    private DocService docService;
    @GetMapping("/doc/query")
    public CommonResp query(@Valid DocQueryReq docQueryReq){
        CommonResp<List<DocQueryResp>> resp=new CommonResp();
        List<DocQueryResp> list=docService.selectall(docQueryReq);
        resp.setContent(list);
        return resp;
    }
    @PostMapping("/doc/save")
    public CommonResp save(@Valid @RequestBody DocSaveReq docSaveReq){
        CommonResp resp=new CommonResp();
        docService.save(docSaveReq);
        return resp;
    }
    @PostMapping("/doc/delete/{id}")
    public CommonResp delete(@PathVariable Long id){
        CommonResp resp=new CommonResp();
        docService.delete(id);
        return resp;
    }
}
