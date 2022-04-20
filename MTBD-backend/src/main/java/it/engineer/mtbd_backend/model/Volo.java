package it.engineer.mtbd_backend.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class Volo implements Serializable {
    private int id;
    private int year;
    private int month;
    private int day_of_month;
    private int day_of_week;
    private Date flight_date;
    private String IATA_code;
    private String tail_number;
    private int flight_number_Airline;
    private int originAirportID;
    private int originCityMarketID;
    private String originAirport;
    private String originCity;
    private String originStateCode;
    private String originStateName;
    private int destAirportID;
    private int destCityMarketID;
    private String destAirport;
    private String destCity;
    private String destStateCode;
    private String destStateName;
    private int CRSDepTime;
    private int depTime;
    private double depDelay;
    private double taxiOut;
    private int wheelsOff;
    private int wheelsOn;
    private double taxiIn;
    private int CRSArrTime;
    private int arrTime;
    private double arrDelay;
    private boolean cancelled;
    private String cancellationCode;
    private boolean diverted;
    private double CRSElapsedTime;
    private double actualElapsedTime;
    private double flights;
    private double distances;
    private double carrierDelay;
    private double securityDelay;
    private double lateAircraftDelay;
    private int divAirportLandings;
    private boolean divReachedDest;
    private double divActualElapsedTime;
    private double divArrDelay;
    private double divDistance;
    private List<Div> divertedAirport;

    public Volo(int id){
        this.id = id;
    }

    public Volo(int id, int year, int month, int day_of_month, int day_of_week, Date flight_date, String IATA_code, String tail_number, int flight_number_Airline, int originAirportID, int originCityMarketID, String originAirport, String originCity, String originStateCode, String originStateName, int destAirportID, int destCityMarketID, String destAirport, String destCity, String destStateCode, String destStateName, int CRSDepTime, int depTime, double depDelay, double taxiOut, int wheelsOff, int wheelsOn, double taxiIn, int CRSArrTime, int arrTime, double arrDelay, boolean cancelled, String cancellationCode, boolean diverted, double CRSElapsedTime, double actualElapsedTime, double flights, double distances, double carrierDelay, double securityDelay, double lateAircraftDelay, int divAirportLandings, boolean divReachedDest, double divActualElapsedTime, double divArrDelay, double divDistance, List<Div> divertedAirport) {
        this.id = id;
        this.year = year;
        this.month = month;
        this.day_of_month = day_of_month;
        this.day_of_week = day_of_week;
        this.flight_date = flight_date;
        this.IATA_code = IATA_code;
        this.tail_number = tail_number;
        this.flight_number_Airline = flight_number_Airline;
        this.originAirportID = originAirportID;
        this.originCityMarketID = originCityMarketID;
        this.originAirport = originAirport;
        this.originCity = originCity;
        this.originStateCode = originStateCode;
        this.originStateName = originStateName;
        this.destAirportID = destAirportID;
        this.destCityMarketID = destCityMarketID;
        this.destAirport = destAirport;
        this.destCity = destCity;
        this.destStateCode = destStateCode;
        this.destStateName = destStateName;
        this.CRSDepTime = CRSDepTime;
        this.depTime = depTime;
        this.depDelay = depDelay;
        this.taxiOut = taxiOut;
        this.wheelsOff = wheelsOff;
        this.wheelsOn = wheelsOn;
        this.taxiIn = taxiIn;
        this.CRSArrTime = CRSArrTime;
        this.arrTime = arrTime;
        this.arrDelay = arrDelay;
        this.cancelled = cancelled;
        this.cancellationCode = cancellationCode;
        this.diverted = diverted;
        this.CRSElapsedTime = CRSElapsedTime;
        this.actualElapsedTime = actualElapsedTime;
        this.flights = flights;
        this.distances = distances;
        this.carrierDelay = carrierDelay;
        this.securityDelay = securityDelay;
        this.lateAircraftDelay = lateAircraftDelay;
        this.divAirportLandings = divAirportLandings;
        this.divReachedDest = divReachedDest;
        this.divActualElapsedTime = divActualElapsedTime;
        this.divArrDelay = divArrDelay;
        this.divDistance = divDistance;
        this.divertedAirport = divertedAirport;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay_of_month() {
        return day_of_month;
    }

    public void setDay_of_month(int day_of_month) {
        this.day_of_month = day_of_month;
    }

    public int getDay_of_week() {
        return day_of_week;
    }

    public void setDay_of_week(int day_of_week) {
        this.day_of_week = day_of_week;
    }

    public Date getFlight_date() {
        return flight_date;
    }

    public void setFlight_date(Date flight_date) {
        this.flight_date = flight_date;
    }

    public String getIATA_code() { return IATA_code; }

    public void setIATA_code(String IATA_code) { this.IATA_code = IATA_code; }

    public String getTail_number() {
        return tail_number;
    }

    public void setTail_number(String tail_number) {
        this.tail_number = tail_number;
    }

    public int getFlight_number_Airline() {
        return flight_number_Airline;
    }

    public void setFlight_number_Airline(int flight_number_Airline) {
        this.flight_number_Airline = flight_number_Airline;
    }

    public int getOriginAirportID() {
        return originAirportID;
    }

    public void setOriginAirportID(int originAirportID) {
        this.originAirportID = originAirportID;
    }

    public int getOriginCityMarketID() {
        return originCityMarketID;
    }

    public void setOriginCityMarketID(int originCityMarketID) {
        this.originCityMarketID = originCityMarketID;
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

    public String getOriginStateCode() {
        return originStateCode;
    }

    public void setOriginStateCode(String originStateCode) {
        this.originStateCode = originStateCode;
    }

    public String getOriginStateName() {
        return originStateName;
    }

    public void setOriginStateName(String originStateName) {
        this.originStateName = originStateName;
    }

    public int getDestAirportID() {
        return destAirportID;
    }

    public void setDestAirportID(int destAirportID) {
        this.destAirportID = destAirportID;
    }

    public int getDestCityMarketID() {
        return destCityMarketID;
    }

    public void setDestCityMarketID(int destCityMarketID) {
        this.destCityMarketID = destCityMarketID;
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

    public String getDestStateCode() {
        return destStateCode;
    }

    public void setDestStateCode(String destStateCode) {
        this.destStateCode = destStateCode;
    }

    public String getDestStateName() {
        return destStateName;
    }

    public void setDestStateName(String destStateName) {
        this.destStateName = destStateName;
    }

    public int getCRSDepTime() {
        return CRSDepTime;
    }

    public void setCRSDepTime(int CRSDepTime) {
        this.CRSDepTime = CRSDepTime;
    }

    public int getDepTime() {
        return depTime;
    }

    public void setDepTime(int depTime) {
        this.depTime = depTime;
    }

    public double getDepDelay() {
        return depDelay;
    }

    public void setDepDelay(double depDelay) {
        this.depDelay = depDelay;
    }

    public double getTaxiOut() {
        return taxiOut;
    }

    public void setTaxiOut(double taxiOut) {
        this.taxiOut = taxiOut;
    }

    public int getWheelsOff() {
        return wheelsOff;
    }

    public void setWheelsOff(int wheelsOff) {
        this.wheelsOff = wheelsOff;
    }

    public int getWheelsOn() {
        return wheelsOn;
    }

    public void setWheelsOn(int wheelsOn) {
        this.wheelsOn = wheelsOn;
    }

    public double getTaxiIn() {
        return taxiIn;
    }

    public void setTaxiIn(double taxiIn) {
        this.taxiIn = taxiIn;
    }

    public int getCRSArrTime() {
        return CRSArrTime;
    }

    public void setCRSArrTime(int CRSArrTime) {
        this.CRSArrTime = CRSArrTime;
    }

    public int getArrTime() {
        return arrTime;
    }

    public void setArrTime(int arrTime) {
        this.arrTime = arrTime;
    }

    public double getArrDelay() {
        return arrDelay;
    }

    public void setArrDelay(double arrDelay) {
        this.arrDelay = arrDelay;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public String getCancellationCode() {
        return cancellationCode;
    }

    public void setCancellationCode(String cancellationCode) {
        this.cancellationCode = cancellationCode;
    }

    public boolean isDiverted() {
        return diverted;
    }

    public void setDiverted(boolean diverted) {
        this.diverted = diverted;
    }

    public double getCRSElapsedTime() {
        return CRSElapsedTime;
    }

    public void setCRSElapsedTime(double CRSElapsedTime) {
        this.CRSElapsedTime = CRSElapsedTime;
    }

    public double getActualElapsedTime() {
        return actualElapsedTime;
    }

    public void setActualElapsedTime(double actualElapsedTime) {
        this.actualElapsedTime = actualElapsedTime;
    }

    public double getFlights() {
        return flights;
    }

    public void setFlights(double flights) {
        this.flights = flights;
    }

    public double getDistances() {
        return distances;
    }

    public void setDistances(double distances) {
        this.distances = distances;
    }

    public double getCarrierDelay() {
        return carrierDelay;
    }

    public void setCarrierDelay(double carrierDelay) {
        this.carrierDelay = carrierDelay;
    }

    public double getSecurityDelay() {
        return securityDelay;
    }

    public void setSecurityDelay(double securityDelay) {
        this.securityDelay = securityDelay;
    }

    public double getLateAircraftDelay() {
        return lateAircraftDelay;
    }

    public void setLateAircraftDelay(double lateAircraftDelay) {
        this.lateAircraftDelay = lateAircraftDelay;
    }

    public int getDivAirportLandings() {
        return divAirportLandings;
    }

    public void setDivAirportLandings(int divAirportLandings) {
        this.divAirportLandings = divAirportLandings;
    }

    public boolean isDivReachedDest() {
        return divReachedDest;
    }

    public void setDivReachedDest(boolean divReachedDest) {
        this.divReachedDest = divReachedDest;
    }

    public double getDivActualElapsedTime() {
        return divActualElapsedTime;
    }

    public void setDivActualElapsedTime(double divActualElapsedTime) {
        this.divActualElapsedTime = divActualElapsedTime;
    }

    public double getDivArrDelay() {
        return divArrDelay;
    }

    public void setDivArrDelay(double divArrDelay) {
        this.divArrDelay = divArrDelay;
    }

    public double getDivDistance() {
        return divDistance;
    }

    public void setDivDistance(double divDistance) {
        this.divDistance = divDistance;
    }

    public List<Div> getDivertedAirport() {
        return divertedAirport;
    }

    public void setDivertedAirport(List<Div> divertedAirport) {
        this.divertedAirport = divertedAirport;
    }

    @Override
    public String toString() {
        return "Volo{" +
                "id=" + id +
                ", year=" + year +
                ", month=" + month +
                ", day_of_month=" + day_of_month +
                ", day_of_week=" + day_of_week +
                ", flight_date=" + flight_date +
                ", DOT_IT_Reporting_Airlines=" + IATA_code +
                ", tail_number='" + tail_number + '\'' +
                ", flight_number_Airline=" + flight_number_Airline +
                ", originAirportID=" + originAirportID +
                ", originCityMarketID=" + originCityMarketID +
                ", originAirport='" + originAirport + '\'' +
                ", originCity='" + originCity + '\'' +
                ", originStateCode='" + originStateCode + '\'' +
                ", originStateName='" + originStateName + '\'' +
                ", destAirportID=" + destAirportID +
                ", destCityMarketID=" + destCityMarketID +
                ", destAirport='" + destAirport + '\'' +
                ", destCity='" + destCity + '\'' +
                ", destStateCode='" + destStateCode + '\'' +
                ", destStateName='" + destStateName + '\'' +
                ", CRSDepTime=" + CRSDepTime +
                ", depTime=" + depTime +
                ", depDelay=" + depDelay +
                ", taxiOut=" + taxiOut +
                ", wheelsOff=" + wheelsOff +
                ", wheelsOn=" + wheelsOn +
                ", taxiIn=" + taxiIn +
                ", CRSArrTime=" + CRSArrTime +
                ", arrTime=" + arrTime +
                ", arrDelay=" + arrDelay +
                ", cancelled=" + cancelled +
                ", cancellationCode='" + cancellationCode + '\'' +
                ", diverted=" + diverted +
                ", CRSElapsedTime=" + CRSElapsedTime +
                ", actualElapsedTime=" + actualElapsedTime +
                ", flights=" + flights +
                ", distances=" + distances +
                ", carrierDelay=" + carrierDelay +
                ", securityDelay=" + securityDelay +
                ", lateAircraftDelay=" + lateAircraftDelay +
                ", divAirportLandings=" + divAirportLandings +
                ", divReachedDest=" + divReachedDest +
                ", divActualElapsedTime=" + divActualElapsedTime +
                ", divArrDelay=" + divArrDelay +
                ", divDistance=" + divDistance +
                ", divertedAirport=" + divertedAirport +
                '}';
    }
}


