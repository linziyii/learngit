package com.example.mysql.controller;

import com.example.mysql.req.EbookQueryReq;
import com.example.mysql.req.EbookSaveReq;
import com.example.mysql.resp.CommonResp;
import com.example.mysql.resp.EbookQueryResp;
import com.example.mysql.resp.PageResp;
import com.example.mysql.service.EbookService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping
public class EbookControl {
    @Autowired
    private EbookService ebookService;

    @ApiOperation(value = "电子书信息查询",notes = "传入参数要求：电子书名、页数、每页大小，其中对页数和分页大小进行了参数校验")
    @GetMapping("/ebook/list")
    public CommonResp query(@Valid EbookQueryReq ebookQueryReq){
        CommonResp<PageResp<EbookQueryResp>> resp=new CommonResp();
        PageResp<EbookQueryResp> list=ebookService.list(ebookQueryReq);
        resp.setContent(list);
        return resp;
    }

    @ApiOperation(value = "电子书更新",notes = "传入参数要求：电子书id、书名、分类")
    @PostMapping("/ebook/save")
    public CommonResp save(@Valid @RequestBody EbookSaveReq ebookSaveReq){
        CommonResp resp=new CommonResp();
        ebookService.save(ebookSaveReq);
        return resp;
    }
    @ApiOperation(value = "电子书删除",notes = "传入参数：电子书id")
    @DeleteMapping("/ebook/delete/{id}")
    public CommonResp delete(@PathVariable Long id){
        CommonResp resp=new CommonResp();
        ebookService.delete(id);
        return resp;
    }
}
