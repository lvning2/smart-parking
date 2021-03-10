package com.design.smartparking.dto;

public class IntoParkRequest {

    private Long parkId;

    private String parkObjectId;

    private Long carId;

    private String parkName;

    private Byte type;

    private String userId;

    private String parkTitle;

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

    public Long getCarId() {
        return carId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
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

    public String getParkTitle() {
        return parkTitle;
    }

    public void setParkTitle(String parkTitle) {
        this.parkTitle = parkTitle;
    }

    @Override
    public String toString() {
        return "IntoParkRequest{" +
                "parkId=" + parkId +
                ", parkObjectId='" + parkObjectId + '\'' +
                ", carId=" + carId +
                ", parkName='" + parkName + '\'' +
                ", type=" + type +
                ", userId='" + userId + '\'' +
                ", parkTitle='" + parkTitle + '\'' +
                '}';
    }
}
