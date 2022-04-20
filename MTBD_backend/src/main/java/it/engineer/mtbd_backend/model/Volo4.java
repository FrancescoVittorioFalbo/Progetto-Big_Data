package it.engineer.mtbd_backend.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class Volo4 implements Serializable {

    private Date flight_date;
    private String originAirport;
    private String originCity;
    private String originStateName;
    private String destAirport;
    private String destCity;
    private Double ritardo;
    private boolean destRaggiunta;
    private int numeroDiScali;
    private Double distanza;

    public Volo4(Date flight_date, String originAirport, String originCity, String originStateName, String destAirport, String destCity, Double ritardo, int numeroDitorramenti, Double distanza, boolean destRaggiunta) {
        this.flight_date = flight_date;
        this.originAirport = originAirport;
        this.originCity = originCity;
        this.originStateName = originStateName;
        this.destAirport = destAirport;
        this.destCity = destCity;
        this.ritardo = ritardo;
        this.destRaggiunta = destRaggiunta;
        this.numeroDiScali = numeroDitorramenti;
        this.distanza = distanza;
    }

    @Override
    public String toString() {
        return "Volo4{" +
                "flight_date=" + flight_date +
                ", originAirport='" + originAirport + '\'' +
                ", originCity='" + originCity + '\'' +
                ", originStateName='" + originStateName + '\'' +
                ", destAirport='" + destAirport + '\'' +
                ", destCity='" + destCity + '\'' +
                ", ritardo=" + ritardo +
                ", destRaggiunta=" + destRaggiunta +
                ", numeroDitorramenti=" + numeroDiScali +
                ", distanza=" + distanza +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Volo4 volo4 = (Volo4) o;
        return destRaggiunta == volo4.destRaggiunta && numeroDiScali == volo4.numeroDiScali && Objects.equals(flight_date, volo4.flight_date) && Objects.equals(originAirport, volo4.originAirport) && Objects.equals(originCity, volo4.originCity) && Objects.equals(originStateName, volo4.originStateName) && Objects.equals(destAirport, volo4.destAirport) && Objects.equals(destCity, volo4.destCity) && Objects.equals(ritardo, volo4.ritardo) && Objects.equals(distanza, volo4.distanza);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flight_date, originAirport, originCity, originStateName, destAirport, destCity, ritardo, destRaggiunta, numeroDiScali, distanza);
    }

    public boolean isDestRaggiunta() {
        return destRaggiunta;
    }

    public void setDestRaggiunta(boolean destRaggiunta) {
        this.destRaggiunta = destRaggiunta;
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

    public Double getRitardo() {
        return ritardo;
    }

    public void setRitardo(Double ritardo) {
        this.ritardo = ritardo;
    }

    public int getNumeroDiScali() {
        return numeroDiScali;
    }

    public void setNumeroDiScali(int numeroDitorramenti) {
        this.numeroDiScali = numeroDitorramenti;
    }

    public Double getDistanza() {
        return distanza;
    }

    public void setDistanza(Double distanza) {
        this.distanza = distanza;
    }
}
