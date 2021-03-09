package com.design.smartparking.service;

import com.design.smartparking.model.Car;
import com.design.smartparking.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<Car> list(String userId){
        return carRepository.findAllByUserIdOrderByCreateDate(userId);
    }

    @Transactional
    public void save(Car car){
        if (car.getId()==null){  // 添加
            carRepository.save(car);
        }else { // 修改
            Optional<Car> optionalCar = carRepository.findById(car.getId());
            optionalCar.ifPresent(c -> {
                c.setBrand(car.getBrand());
                c.setModel(car.getModel());
                c.setDescription(car.getDescription());
                c.setLicencePlate(car.getLicencePlate());
                c.setName(car.getName());
                c.setUserId(car.getUserId());
                c.setObjectId(car.getObjectId());
                carRepository.save(c);
            });
        }
    }


    @Transactional
    public void delete(Long id){
        carRepository.deleteById(id);
    }

    @Transactional
    public void setCurrent(Long id,String userId){
        List<Car> cars = carRepository.findAllByUserIdOrderByCreateDate(userId);
        for (Car car : cars) {
            if (car.getId()==id){
                car.setCurrent(true);
            }else {
                car.setCurrent(false);
            }
            carRepository.save(car);
        }
    }

    public Car getCurrent(String userId){
        List<Car> cars = carRepository.findAllByUserIdOrderByCreateDate(userId);
        for (Car car : cars) {
            if (car.getCurrent()){
                return car;
            }
        }
        if (cars.size()>0){
            return cars.get(0);
        }
        return null;
    }

}
