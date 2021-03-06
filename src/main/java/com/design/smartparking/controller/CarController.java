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
    private Result list(@PathVariable Long userId){
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



}
