package com.example.mysql.controller;

import com.example.mysql.req.EbookQueryReq;
import com.example.mysql.req.EbookSaveReq;
import com.example.mysql.resp.CommonResp;
import com.example.mysql.resp.EbookQueryResp;
import com.example.mysql.resp.PageResp;
import com.example.mysql.service.EbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class EbookControl {
    @Autowired
    private EbookService ebookService;
    @GetMapping("/ebook/query")
    public CommonResp query(EbookQueryReq ebookQueryReq){
        CommonResp<PageResp<EbookQueryResp>> resp=new CommonResp();
        PageResp<EbookQueryResp> list=ebookService.list(ebookQueryReq);
        resp.setContent(list);
        return resp;
    }
    @PostMapping("/ebook/save")
    public CommonResp save(EbookSaveReq ebookSaveReq){
        CommonResp resp=new CommonResp();
        ebookService.save(ebookSaveReq);
        return resp;
    }
}
