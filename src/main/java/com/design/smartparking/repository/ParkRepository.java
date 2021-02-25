package com.design.smartparking.repository;

import com.design.smartparking.model.Park;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkRepository extends JpaRepository<Park, Long> {

    Park findParkByObjectId(String objectId);

    void deleteByObjectId(String objectId);

}
