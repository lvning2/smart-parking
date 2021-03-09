package com.design.smartparking.service;

import com.design.smartparking.dto.EzStopDto;
import com.design.smartparking.dto.IntoParkRequest;
import com.design.smartparking.mapper.CGlibMapper;
import com.design.smartparking.model.Car;
import com.design.smartparking.model.EzStop;
import com.design.smartparking.model.Park;
import com.design.smartparking.repository.CarRepository;
import com.design.smartparking.repository.EzStopRepository;
import com.design.smartparking.repository.ParkRepository;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.boot.model.source.spi.Orderable;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.Table;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ParkService {

    @Autowired
    private ParkRepository parkRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private EzStopRepository ezStopRepository;

    @Transactional
    public void save(Park park){
        if (park.getId()==null){ // 增加
            parkRepository.save(park);
        }else {  // 修改
            Optional<Park> optional = parkRepository.findById(park.getId());
            optional.ifPresent(p->{
                BeanUtils.copyProperties(park,p);
                parkRepository.save(p);
            });
        }
    }

    @Transactional
    public void delete(Long id, String objectId){
        if (id!=null){
            parkRepository.deleteById(id);
            return;
        }
        if (StringUtils.isNoneBlank(objectId)){
            parkRepository.deleteByObjectId(objectId);
            return;
        }
    }

    public Park getParkInfo(String objectId){
        return parkRepository.findParkByObjectId(objectId);
    }

    @Transactional
    public String intoPark(IntoParkRequest intoParkRequest){

        Park parkByObjectId = parkRepository.findParkByObjectId(intoParkRequest.getParkObjectId());


        EzStop ezStop = new EzStop();
        ezStop.setParkId(intoParkRequest.getParkId());
        ezStop.setCarId(intoParkRequest.getCarId());
        ezStop.setParkName(intoParkRequest.getParkName());
        ezStop.setParkObjectId(intoParkRequest.getParkObjectId());
        ezStop.setType(intoParkRequest.getType());
        ezStop.setCreateDate(new Date());
        if (intoParkRequest.getType()==(byte)10){ // 入场
            ezStop.setIntoTime(new Date());
            if (parkByObjectId!=null){
                if (parkByObjectId.getRemainSpace()!=null) {
                    if (parkByObjectId.getRemainSpace()>0){
                        parkByObjectId.setRemainSpace(parkByObjectId.getRemainSpace()-1);
                    }else {
                        return "该停车场已经停满";
                    }
                }
            }
        }
        if (intoParkRequest.getType()==(byte)20){ // 出场
            ezStop.setOutTime(new Date());
            if (parkByObjectId!=null){
                if (parkByObjectId.getRemainSpace()!=null) {
                    if (parkByObjectId.getRemainSpace()<=parkByObjectId.getTotalSpace()){
                        parkByObjectId.setRemainSpace(parkByObjectId.getRemainSpace()+1);
                    }
                }
            }
        }
        parkRepository.save(parkByObjectId);
        ezStopRepository.save(ezStop);
        return "成功";
    }

    public Page<EzStopDto> record(Integer page, Integer size, String userId){
        PageRequest pageRequest = PageRequest.of(page-1, size, Sort.by("createDate"));
        Page<EzStop> ezStopPage = ezStopRepository.findAllByUserId(userId, pageRequest);

        long totalElements = ezStopPage.getTotalElements();
        List<EzStop> content = ezStopPage.getContent();

        List<EzStopDto> list = new ArrayList<>();
        for (EzStop ezStop : content) {
            Long carId = ezStop.getCarId();
            EzStopDto dto = CGlibMapper.mapper(ezStop, EzStopDto.class);
            Optional<Car> optionalCar = carRepository.findById(carId);
            optionalCar.ifPresent(car -> {
                dto.setLicencePlate(car.getLicencePlate());
                list.add(dto);
            });
        }

        PageImpl<EzStopDto> dtoPage = new PageImpl<>(list,ezStopPage.getPageable(), totalElements);
        return dtoPage;
    }

}
