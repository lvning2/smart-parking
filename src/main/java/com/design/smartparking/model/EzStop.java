package com.design.smartparking.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ez_stop")
public class EzStop {

    @Id
    @Column(name = "id", columnDefinition = "int COMMENT '主键，自动生成'")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long carId;

    private Long parkId;

    private String parkObjectId;

    private Byte type;  // 10 入场 , 20 出场

    private Date intoTime;

    private Date outTime;

    private Double cost; // 费用

    private String parkName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Long getParkId() {
        return parkId;
    }

    public void setParkId(Long parkId) {
        this.parkId = parkId;
    }

    public String getParkObjectId() {
        return parkObjectId;
    }

    public void setParkObjectId(String parkObjectId) {
        this.parkObjectId = parkObjectId;
    }

    public Date getIntoTime() {
        return intoTime;
    }

    public void setIntoTime(Date intoTime) {
        this.intoTime = intoTime;
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }
}
