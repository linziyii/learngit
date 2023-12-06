package com.example.mysql.controller;

import com.example.mysql.entity.Ebook;
import com.example.mysql.req.EbookReq;
import com.example.mysql.resp.CommonResp;
import com.example.mysql.resp.EbookResp;
import com.example.mysql.service.EbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping
public class EbookControl {
    @Autowired
    private EbookService ebookService;
    @PostMapping("/ebook")
    public CommonResp list(EbookReq ebookReq){
        CommonResp<List<EbookResp>> resp=new CommonResp();
        List<EbookResp>list=ebookService.list(ebookReq);
        resp.setContent(list);
        return resp;
    }
}
