package DTO;

import entity.Car;
import entity.Owner;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rasmu
 */
public class OwnerDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    private String club;
    private String carBrand;
    private String carColor;

    public OwnerDTO(Owner owner, Car car) {
        this.id = owner.getId();
        this.firstName = owner.getFirstName();
        this.lastName = owner.getLastName();
        this.club = owner.getClub();
        this.carBrand = car.getBrand();
        this.carColor = car.getColor();
    }

    public OwnerDTO(String firstName, String lastName, String club, String carBrand, String carColor) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.club = club;
        this.carBrand = carBrand;
        this.carColor = carColor;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

}
