package com.design.smartparking.controller;

import com.design.smartparking.dto.IntoParkRequest;
import com.design.smartparking.dto.Result;
import com.design.smartparking.model.EzStop;
import com.design.smartparking.model.Park;
import com.design.smartparking.service.ParkService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @PostMapping("/into")  // 出入场
    public Result intoPark(@RequestBody IntoParkRequest intoParkRequest){
        parkService.intoPark(intoParkRequest);
        return Result.success();
    }

    @GetMapping("/record") // 出入场记录查询
    public Result record(@RequestParam(defaultValue = "1") Integer page,
                         @RequestParam(defaultValue = "10") Integer size,
                         @RequestParam String userId){
        Page<EzStop> record = parkService.record(page, size, userId);
        return Result.success(record);
    }

}
