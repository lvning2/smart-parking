package com.design.smartparking.controller;

import com.design.smartparking.dto.Result;
import com.design.smartparking.model.Notice;
import com.design.smartparking.service.NoticeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notice")
@Api(tags = "公告")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer size){
        Page<Notice> list = noticeService.list(page, size);
        return Result.success(list);
    }

    @PostMapping("/save")
    public Result save(@RequestBody Notice notice){
        noticeService.save(notice);
        return Result.success();
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam Long id){
        noticeService.delete(id);
        return Result.success();
    }

}
