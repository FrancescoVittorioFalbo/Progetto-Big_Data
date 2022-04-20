package it.engineer.mtbd_backend.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class Volo1 implements Serializable {

    private Date flight_date;
    private String originAirport;
    private String originCity;
    private String originStateName;
    private String destAirport;
    private String destCity;
    private String destStateName;
    private String tail_number;
    private Double actualElapsedTime;
    private Double flights;
    private Double distances;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Volo1 volo1 = (Volo1) o;
        return Objects.equals(flight_date, volo1.flight_date) && Objects.equals(originAirport, volo1.originAirport) && Objects.equals(originCity, volo1.originCity) && Objects.equals(originStateName, volo1.originStateName) && Objects.equals(destAirport, volo1.destAirport) && Objects.equals(destCity, volo1.destCity) && Objects.equals(destStateName, volo1.destStateName) && Objects.equals(tail_number, volo1.tail_number) && Objects.equals(actualElapsedTime, volo1.actualElapsedTime) && Objects.equals(flights, volo1.flights) && Objects.equals(distances, volo1.distances);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flight_date, originAirport, originCity, originStateName, destAirport, destCity, destStateName, tail_number, actualElapsedTime, flights, distances);
    }

    public Date getFlight_date() {
        return flight_date;
    }

    public void setFlight_date(Date flight_date) {
        this.flight_date = flight_date;
    }

    public String getOriginAirport() {
        return originAirport;
    }

    public void setOriginAirport(String originAirport) {
        this.originAirport = originAirport;
    }

    public String getOriginCity() {
        return originCity;
    }

    public void setOriginCity(String originCity) {
        this.originCity = originCity;
    }

    public String getOriginStateName() {
        return originStateName;
    }

    public void setOriginStateName(String originStateName) {
        this.originStateName = originStateName;
    }

    public String getDestAirport() {
        return destAirport;
    }

    public void setDestAirport(String destAirport) {
        this.destAirport = destAirport;
    }

    public String getDestCity() {
        return destCity;
    }

    public void setDestCity(String destCity) {
        this.destCity = destCity;
    }

    public String getDestStateName() {
        return destStateName;
    }

    public void setDestStateName(String destStateName) {
        this.destStateName = destStateName;
    }

    public String getTail_number() {
        return tail_number;
    }

    public void setTail_number(String tail_number) {
        this.tail_number = tail_number;
    }

    public Double getActualElapsedTime() {
        return actualElapsedTime;
    }

    public void setActualElapsedTime(Double actualElapsedTime) {
        this.actualElapsedTime = actualElapsedTime;
    }

    public Double getFlights() {
        return flights;
    }

    public void setFlights(Double flights) {
        this.flights = flights;
    }

    public Double getDistances() {
        return distances;
    }

    public void setDistances(Double distances) {
        this.distances = distances;
    }

    public Volo1(Date flight_date, String originAirport, String originCity, String originStateName, String destAirport, String destCity, String destStateName, String tail_number, Double actualElapsedTime, Double flights, Double distances) {
        this.flight_date = flight_date;
        this.originAirport = originAirport;
        this.originCity = originCity;
        this.originStateName = originStateName;
        this.destAirport = destAirport;
        this.destCity = destCity;
        this.destStateName = destStateName;
        this.tail_number = tail_number;
        this.actualElapsedTime = actualElapsedTime;
        this.flights = flights;
        this.distances = distances;
    }
}
