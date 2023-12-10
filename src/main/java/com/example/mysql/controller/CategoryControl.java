package com.example.mysql.controller;

import com.example.mysql.req.CategoryQueryReq;
import com.example.mysql.req.CategorySaveReq;
import com.example.mysql.resp.CommonResp;
import com.example.mysql.resp.CategoryQueryResp;
import com.example.mysql.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping
public class CategoryControl {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/category/list")
    public CommonResp query(@Valid CategoryQueryReq categoryQueryReq){
        CommonResp<List<CategoryQueryResp>> resp=new CommonResp();
        List<CategoryQueryResp> list=categoryService.selectall(categoryQueryReq);
        resp.setContent(list);
        return resp;
    }
    @PostMapping("/category/save")
    public CommonResp save(@Valid @RequestBody CategorySaveReq categorySaveReq){
        CommonResp resp=new CommonResp();
        categoryService.save(categorySaveReq);
        return resp;
    }
    @DeleteMapping("/category/delete/{id}")
    public CommonResp delete(@PathVariable Long id){
        CommonResp resp=new CommonResp();
        categoryService.delete(id);
        return resp;
    }
}
