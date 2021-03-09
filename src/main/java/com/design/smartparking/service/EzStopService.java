package com.design.smartparking.service;

import com.design.smartparking.repository.EzStopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EzStopService {

    @Autowired
    private EzStopRepository ezStopRepository;


}
