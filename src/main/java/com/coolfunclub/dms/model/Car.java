package com.coolfunclub.dms.model;

import java.util.HashSet;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Car {

    @Id
    private String vin;
    private String manufacturer;
    private String model;
    private int carYear;
    private int mileage;
    private String trim;
    private String color;
    private String status;
    private double price;

    @ManyToMany
    @JoinTable(
        name = "car_images",
        joinColumns = @JoinColumn(name = "car_vin"),
        inverseJoinColumns = @JoinColumn(name = "image_id")
    )
    private final Set<Image> images = new HashSet<>();

    public Car(String vin, String manufacturer, String model, int carYear, int mileage, String trim, String color, double price, String status) {
        this.vin = vin;
        this.manufacturer = manufacturer;
        this.model = model;
        this.carYear = carYear;
        this.mileage = mileage;
        this.trim = trim;
        this.color = color;
        this.price = price;
        this.status = status;
    }
    public Set<Image> getImages(){
        return images;
    }

    public void setVIN(String vin) {
        this.vin = vin;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setcarYear(int carYear) {
        this.carYear = carYear;
    }

    public void setMilage(int mileage) {
        this.mileage = mileage;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public void setTrim(String trim) {
        this.trim = trim;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVIN() {
        return vin;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public int getcarYear() {
        return carYear;
    }
    public double getPrice() {
        return price;
    }

    public int getMileage() {
        return mileage;
    }

    public String getTrim() {
        return trim;
    }

    public String getColor() {
        return color;
    }


    public String getStatus() {
        return status;
    }
}
