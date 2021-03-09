package com.design.smartparking.service;

import com.design.smartparking.dto.IntoParkRequest;
import com.design.smartparking.model.EzStop;
import com.design.smartparking.model.Park;
import com.design.smartparking.repository.EzStopRepository;
import com.design.smartparking.repository.ParkRepository;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.boot.model.source.spi.Orderable;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ParkService {

    @Autowired
    private ParkRepository parkRepository;

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
    public void intoPark(IntoParkRequest intoParkRequest){
        EzStop ezStop = new EzStop();
        ezStop.setParkId(intoParkRequest.getParkId());
        ezStop.setCarId(intoParkRequest.getCarId());
        ezStop.setParkName(intoParkRequest.getParkName());
        ezStop.setParkObjectId(intoParkRequest.getParkObjectId());
        ezStop.setType(intoParkRequest.getType());
        ezStop.setCreateDate(new Date());
        if (intoParkRequest.getType()==(byte)10){ // 入场
            ezStop.setIntoTime(new Date());
        }
        if (intoParkRequest.getType()==(byte)20){ // 出场
            ezStop.setOutTime(new Date());
        }
        ezStopRepository.save(ezStop);
    }

    public Page<EzStop> record(Integer page, Integer size, String userId){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("createDate"));
        return ezStopRepository.findAllByUserId(userId, pageRequest);
    }

}
