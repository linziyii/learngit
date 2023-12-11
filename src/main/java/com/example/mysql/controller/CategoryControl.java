package com.example.mysql.controller;

import com.example.mysql.req.CategoryQueryReq;
import com.example.mysql.req.CategorySaveReq;
import com.example.mysql.resp.CommonResp;
import com.example.mysql.resp.CategoryQueryResp;
import com.example.mysql.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping
public class CategoryControl {
    @Autowired
    private CategoryService categoryService;
    @ApiOperation(value = "目录查询")
    @GetMapping("/category/all")
    public CommonResp all() {
        CommonResp<List<CategoryQueryResp>> resp = new CommonResp<>();
        List<CategoryQueryResp> list = categoryService.all();
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/category/list")
    public CommonResp query(@Valid CategoryQueryReq categoryQueryReq){
        CommonResp<List<CategoryQueryResp>> resp=new CommonResp();
        List<CategoryQueryResp> list=categoryService.selectall(categoryQueryReq);
        resp.setContent(list);
        return resp;
    }
    @ApiOperation(value = "目录更新")
    @PostMapping("/category/save")
    public CommonResp save(@Valid @RequestBody CategorySaveReq categorySaveReq){
        CommonResp resp=new CommonResp();
        categoryService.save(categorySaveReq);
        return resp;
    }
    @ApiOperation(value = "目录删除")
    @DeleteMapping("/category/delete/{id}")
    public CommonResp delete(@PathVariable Long id){
        CommonResp resp=new CommonResp();
        categoryService.delete(id);
        return resp;
    }
}
