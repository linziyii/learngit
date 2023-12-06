package com.example.mysql.controller;

import com.example.mysql.entity.Ebook;
import com.example.mysql.service.EbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping
public class EbookControl {
    @Autowired
    private EbookService ebookService;
    @GetMapping("/ebook")
    public List<Ebook> list(){
        return ebookService.list();
    }
}
