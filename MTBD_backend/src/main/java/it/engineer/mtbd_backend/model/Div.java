package it.engineer.mtbd_backend.model;

import java.io.Serializable;

public class Div implements Serializable {
    private String DivAirport;
    private int DivAirportID;
    private int DivWheelsOn;
    private double DivTotalGTime;
    private double DivLongestGTime;
    private int DivWheelsOff;
    private String DivTailNum;

    public Div(String divAirport, int divAirportID, int divWheelsOn, double divTotalGTime, double divLongestGTime, int divWheelsOff, String divTailNum) {
        DivAirport = divAirport;
        DivAirportID = divAirportID;
        DivWheelsOn = divWheelsOn;
        DivTotalGTime = divTotalGTime;
        DivLongestGTime = divLongestGTime;
        DivWheelsOff = divWheelsOff;
        DivTailNum = divTailNum;
    }

    @Override
    public String toString() {
        return "Div{" +
                "DivAirport='" + DivAirport + '\'' +
                ", DivAirportID=" + DivAirportID +
                ", DivWheelsOn=" + DivWheelsOn +
                ", DivTotalGTime=" + DivTotalGTime +
                ", DivLongestGTime=" + DivLongestGTime +
                ", DivWheelsOff=" + DivWheelsOff +
                ", DivTailNum='" + DivTailNum + '\'' +
                '}';
    }

    public String getDivAirport() {
        return DivAirport;
    }

    public void setDivAirport(String divAirport) {
        DivAirport = divAirport;
    }

    public int getDivAirportID() {
        return DivAirportID;
    }

    public void setDivAirportID(int divAirportID) {
        DivAirportID = divAirportID;
    }

    public int getDivWheelsOn() {
        return DivWheelsOn;
    }

    public void setDivWheelsOn(int divWheelsOn) {
        DivWheelsOn = divWheelsOn;
    }

    public double getDivTotalGTime() {
        return DivTotalGTime;
    }

    public void setDivTotalGTime(double divTotalGTime) {
        DivTotalGTime = divTotalGTime;
    }

    public double getDivLongestGTime() {
        return DivLongestGTime;
    }

    public void setDivLongestGTime(double divLongestGTime) {
        DivLongestGTime = divLongestGTime;
    }

    public int getDivWheelsOff() {
        return DivWheelsOff;
    }

    public void setDivWheelsOff(int divWheelsOff) {
        DivWheelsOff = divWheelsOff;
    }

    public String getDivTailNum() {
        return DivTailNum;
    }

    public void setDivTailNum(String divTailNum) {
        DivTailNum = divTailNum;
    }
}


