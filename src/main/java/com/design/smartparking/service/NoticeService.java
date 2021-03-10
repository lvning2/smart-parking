package com.design.smartparking.service;

import com.design.smartparking.model.Notice;
import com.design.smartparking.repository.NoticeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    public Page<Notice> list(Integer page,Integer size){
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by("createDate").descending());
        return noticeRepository.findAll(pageRequest);
    }

    @Transactional
    public void save(Notice notice){
        if (notice.getId()==null){ // 保存
            noticeRepository.save(notice);
        }else { // 修改
            Optional<Notice> optional = noticeRepository.findById(notice.getId());
            optional.ifPresent(n->{
                BeanUtils.copyProperties(notice,n);
                noticeRepository.save(n);
            });
        }
    }

    @Transactional
    public void delete(Long id){
        noticeRepository.deleteById(id);
    }


}
