package it.engineer.mtbd_backend.model;

import java.io.Serializable;
import java.util.Objects;

public class City implements Serializable {
    private String airportName;
    private String cityName;
    private String stateName;

    public City(String airportName, String cityName, String stateName) {
        this.airportName = airportName;
        this.cityName = cityName;
        this.stateName = stateName;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    @Override
    public String toString() {
        return "City{" +
                "airportName='" + airportName + '\'' +
                ", cityName='" + cityName + '\'' +
                ", stateName='" + stateName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return airportName.equals(city.airportName) &&
                cityName.equals(city.cityName) &&
                stateName.equals(city.stateName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(airportName, cityName, stateName);
    }
}