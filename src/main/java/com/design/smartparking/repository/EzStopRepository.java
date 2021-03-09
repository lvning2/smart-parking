package com.design.smartparking.repository;

import com.design.smartparking.model.EzStop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EzStopRepository extends JpaRepository<EzStop,Long> {

    Page<EzStop> findAllByUserId(String userId, Pageable pageable);


}
