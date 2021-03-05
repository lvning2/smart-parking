package com.design.smartparking.controller;

import com.design.smartparking.dto.Result;
import com.design.smartparking.model.Park;
import com.design.smartparking.service.ParkService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/park")
@Api(tags = "停车场")
public class ParkController {

    @Autowired
    private ParkService parkService;

    @PostMapping("/save")
    public Result save(@RequestBody Park park){
        parkService.save(park);
        return Result.success();
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam(required = false) Long id,
                         @RequestParam(required = false) String objectId){
        parkService.delete(id,objectId);
        return Result.success();
    }

    @GetMapping("/info")
    public Result getParkInfo(@RequestParam String objectId){
        return Result.success(parkService.getParkInfo(objectId));
    }




}
