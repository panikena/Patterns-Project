package com.panikena.Models;

import javax.persistence.*;

/**
 * Created by Artem on 18.04.2016.
 */

@Entity
@Table(name = "cars", schema = "", catalog = "cars")
public class Car {

    @Id
    @Column(name = "VIN")
    private String VIN;

    @Basic
    @Column(name = "license_plate")
    private String license_plate;
    @Basic
    @Column(name = "owner")
    private String owner;

    @Basic
    @Column(name = "color")
    private String color;

    public Car() {
    }

    private Car(CarBuilder builder){
        this.VIN = builder.VIN;
        this.license_plate = builder.license_plate;
        this.color = builder.color;
        this.owner = builder.owner;
    }

    public static class CarBuilder{


        private final String VIN;
        private String license_plate;
        private String owner;
        private String color;

        public CarBuilder(String VIN){
            this.VIN = VIN;
        }

        public CarBuilder addOwner(String owner){
            this.owner = owner;
            return this;
        }

        public CarBuilder addPlate(String license_plate){
            this.license_plate = license_plate;
            return this;
        }
        public CarBuilder addColor(String color){
            this.color = color;
            return this;
        }
        public Car build(){
            return new Car(this);
        }
    }

    public String getVIN() {
        return VIN;
    }

    public String getOwner() {
        return owner;
    }

    public String getColor() {
        return color;
    }

    public String getLicense_plate() {
        return license_plate;
    }

    private void setVIN(String VIN) {
        this.VIN = VIN;
    }

    private void setLicense_plate(String license_plate) {
        this.license_plate = license_plate;
    }

    private void setOwner(String owner) {
        this.owner = owner;
    }

    private void setColor(String color) {
        this.color = color;
    }
}
