package com.design.smartparking.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "park")
public class Park {

    @Id
    @Column(name = "id", columnDefinition = "int COMMENT '主键，自动生成'")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String objectId;  // 腾讯地图 停车场 ID

    @Column
    private Integer totalSpace; // 总车位数

    @Column
    private Integer remainSpace; // 剩余车位

    @Column
    private Double price;  // 单价 3元/小时

    @Column
    private String description; // 描述

    @Column
    private String city;

    @Column
    private String district;

    @Column
    private String province;

    @Column
    private String address;

    @Column
    private String category;

    @Column
    private String lat;

    @Column
    private String lng;

    @Column
    private String tel;

    @Column
    private String title;

    @CreationTimestamp
    @Column(name = "create_date", columnDefinition = "datetime COMMENT '创建时间'")
    private Date createDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getTotalSpace() {
        return totalSpace;
    }

    public void setTotalSpace(Integer totalSpace) {
        this.totalSpace = totalSpace;
    }

    public Integer getRemainSpace() {
        return remainSpace;
    }

    public void setRemainSpace(Integer remainSpace) {
        this.remainSpace = remainSpace;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}


//{
//        "_distance": 141.53999999999999,
//        "ad_info": {
    //        "adcode": 410103,
    //        "city": "郑州市",
    //        "district": "二七区",
    //        "province": "河南省"
//        },
//        "address": "河南省郑州市二七区政通路",
//        "category": "汽车:停车场",
//        "id": "9440654831185350547",
//        "location": {
    //        "lat": 34.724555000000002,
    //        "lng": 113.638795
//        },
//        "tel": "",
//        "title": "升龙国际中心E区23号楼停车场",
//        "type": 0
//        }


