package com.design.smartparking.service;

import com.design.smartparking.model.Park;
import com.design.smartparking.repository.ParkRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ParkService {

    @Autowired
    private ParkRepository parkRepository;

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

}
