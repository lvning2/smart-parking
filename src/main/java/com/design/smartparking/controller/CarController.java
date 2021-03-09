package com.design.smartparking.controller;

import com.design.smartparking.dto.Result;
import com.design.smartparking.model.Car;
import com.design.smartparking.service.CarService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car")
@Api(tags = "车")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/list/{userId}")
    public Result list(@PathVariable String userId){
        List<Car> list = carService.list(userId);
        return Result.success(list);
    }

    @PostMapping("/save")
    public Result save(@RequestBody Car car){
        carService.save(car);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(Long id){
        carService.delete(id);
        return Result.success();
    }

    @PostMapping("/setCurrent")
    public Result setCurrent(Long id,String userId){
        carService.setCurrent(id,userId);
        return Result.success();
    }

    @GetMapping("/getCurrent")
    public Result getCurrent(String userId){
        Car car = carService.getCurrent(userId);
        return Result.success(car);
    }



}
