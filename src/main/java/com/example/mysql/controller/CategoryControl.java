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
    @ApiOperation(value = "电子书目录查询",notes = "传入参数：目录名称，目录序列号")
    @GetMapping("/category/list")
    public CommonResp query(@Valid CategoryQueryReq categoryQueryReq){
        CommonResp<List<CategoryQueryResp>> resp=new CommonResp();
        List<CategoryQueryResp> list=categoryService.selectall(categoryQueryReq);
        resp.setContent(list);
        return resp;
    }
    @ApiOperation(value = "目录更新",notes = "传入参数：目录名（不能为空），目录序列号（不能为空）")
    @PostMapping("/category/save")
    public CommonResp save(@Valid @RequestBody CategorySaveReq categorySaveReq){
        CommonResp resp=new CommonResp();
        categoryService.save(categorySaveReq);
        return resp;
    }
    @ApiOperation(value = "目录删除",notes = "传入参数：目录id")
    @DeleteMapping("/category/delete/{id}")
    public CommonResp delete(@PathVariable Long id){
        CommonResp resp=new CommonResp();
        categoryService.delete(id);
        return resp;
    }
}
