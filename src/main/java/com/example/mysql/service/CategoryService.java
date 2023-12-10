package com.example.mysql.service;

import com.example.mysql.entity.Category;
import com.example.mysql.entity.CategoryExample;
import com.example.mysql.mapper.CategoryMapper;
import com.example.mysql.req.CategoryQueryReq;
import com.example.mysql.req.CategorySaveReq;
import com.example.mysql.resp.CategoryQueryResp;
import com.example.mysql.resp.PageResp;
import com.example.mysql.util.CopyUtil;
import com.example.mysql.util.SnowFlake;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    public List<CategoryQueryResp> all() {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.setOrderByClause("sort asc");
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);

        // 列表复制
        List<CategoryQueryResp> list = CopyUtil.copyList(categoryList, CategoryQueryResp.class);

        return list;
    }
    public PageResp<CategoryQueryResp> list(CategoryQueryReq categoryQueryReq){
        CategoryExample example=new CategoryExample();
        CategoryExample.Criteria criteria=example.createCriteria();
        if(!ObjectUtils.isEmpty(categoryQueryReq.getName())){
            criteria.andNameLike("%"+ categoryQueryReq.getName()+"%");  //作用：定义select规则，将其传入mapper的select可起到作用
        }
        PageHelper.startPage(categoryQueryReq.getPage(), categoryQueryReq.getSize());
        List<Category> categoryList=categoryMapper.selectByExample(example);
        PageInfo<Category> pageInfo=new PageInfo<>(categoryList);
        List<CategoryQueryResp> categoryQueryResp =new ArrayList<>();
        for(Category category:categoryList){
            CategoryQueryResp e=new CategoryQueryResp();
            BeanUtils.copyProperties(category,e);  //复制对象
            categoryQueryResp.add(e);   //添加到列表中
        }
        PageResp<CategoryQueryResp> pageResp=new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(categoryQueryResp);
        return pageResp;
    }
    public void save(CategorySaveReq categorySaveReq){
        Category category=new Category();
        SnowFlake snowFlake=new SnowFlake(1,1);
        BeanUtils.copyProperties(categorySaveReq,category);
        if(ObjectUtils.isEmpty(category.getId())){
            category.setId(snowFlake.nextId());
            categoryMapper.insert(category);
        }else{
            categoryMapper.updateByPrimaryKey(category);
        }
    }
    public void delete(Long id){
        categoryMapper.deleteByPrimaryKey(id);
    }
    public List<CategoryQueryResp> selectall(CategoryQueryReq categoryQueryReq){
        CategoryExample example=new CategoryExample();
        example.setOrderByClause("sort asc");
        List<Category> categoryList=categoryMapper.selectByExample(example);
        List<CategoryQueryResp> categoryQueryResp =new ArrayList<>();
        for(Category category:categoryList){
            CategoryQueryResp e=new CategoryQueryResp();
            BeanUtils.copyProperties(category,e);  //复制对象
            categoryQueryResp.add(e);   //添加到列表中
        }
        return categoryQueryResp;
    }
}
