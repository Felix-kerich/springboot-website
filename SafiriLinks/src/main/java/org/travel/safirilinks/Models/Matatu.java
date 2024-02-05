package org.travel.safirilinks.Models;

import jakarta.persistence.*;



@Entity
public class Matatu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String departureTime;
    private String arrivalTime;
    private  String name;

    private String  departureDate;

    private String  numberOfSeatsAvailable;

    @Override
    public String toString() {
        return "Matatu{" +
                "id=" + id +
                ", departureTime='" + departureTime + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                ", name='" + name + '\'' +
                ", departureDate=" + departureDate +
                ", numberOfSeatsAvailable=" + numberOfSeatsAvailable +
                '}';
    }




    public Matatu() {
    }

    public Matatu(String departureTime, String arrivalTime,String name,String departureDate,String numberOfSeatsAvailable) {
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.name=name;
        this.departureDate=departureDate;
        this.numberOfSeatsAvailable=numberOfSeatsAvailable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }
    public String getNumberOfSeatsAvailable() {
        return numberOfSeatsAvailable;
    }

    public void setNumberOfSeatsAvailable(String numberOfSeatsAvailable) {
        this.numberOfSeatsAvailable = numberOfSeatsAvailable;
    }


}