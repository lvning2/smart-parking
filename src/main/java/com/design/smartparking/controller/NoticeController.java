package com.design.smartparking.controller;

import com.design.smartparking.service.NoticeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notice")
@Api(tags = "公告")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;



}
