package com.design.smartparking.service;

import com.design.smartparking.model.Car;
import com.design.smartparking.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Car info(Long id){
        return carRepository.findById(id).orElse(null);
    }


    @Transactional
    public void save(Car car){
        if (car.getId()==null){  // 添加
            String userId = car.getUserId();
            List<Car> cars = carRepository.findAllByUserIdOrderByCreateDate(userId);
            if (cars!=null&&cars.size()==0){
                car.setCurrent(true);
            }
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
                c.setCurrent(car.getCurrent());
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
