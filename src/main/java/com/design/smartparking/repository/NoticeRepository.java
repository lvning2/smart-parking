package com.design.smartparking.repository;

import com.design.smartparking.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice,Long> {


}
