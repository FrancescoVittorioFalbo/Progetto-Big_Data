package it.engineer.mtbd_backend.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class GeneralQuery4 implements Serializable {
    private Date daOra;
    private Date aOra;
    private String tipologia;
    private boolean destinazioneRaggiunta;
    private int arrivalDelayRange;
    private int numDiv;

    //<100 (1)
    //>=100 && <300 (2)
    //>=300 && 1000 (3)
    //>=1000 (4)

    public GeneralQuery4(){

    }

    public GeneralQuery4(Date daOra, Date aOra, String tipologia, boolean destinazioneRaggiunta, int arrivalDelayRange, int numDiv) {
        this.daOra = daOra;
        this.aOra = aOra;
        this.tipologia = tipologia;
        this.destinazioneRaggiunta = destinazioneRaggiunta;
        this.arrivalDelayRange = arrivalDelayRange;
        this.numDiv = numDiv;
    }

    public int getNumDiv(){
        return numDiv;
    }

    public void setNumDiv(int numDiv){
        this.numDiv = numDiv;
    }

    @Override
    public String toString() {
        return "GeneralQuery4{" +
                "daOra=" + daOra +
                ", aOra=" + aOra +
                ", tipologia='" + tipologia + '\'' +
                ", destinazioneRaggiunta=" + destinazioneRaggiunta +
                ", arrivalDelayRange=" + arrivalDelayRange +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeneralQuery4 that = (GeneralQuery4) o;
        return destinazioneRaggiunta == that.destinazioneRaggiunta &&
                Objects.equals(daOra, that.daOra) &&
                Objects.equals(aOra, that.aOra) &&
                Objects.equals(tipologia, that.tipologia) &&
                Objects.equals(arrivalDelayRange, this.arrivalDelayRange);
    }

    @Override
    public int hashCode() {
        return Objects.hash(daOra, aOra, tipologia, destinazioneRaggiunta, arrivalDelayRange);
    }

    public Date getDaOra() {
        return daOra;
    }

    public void setDaOra(Date daOra) {
        this.daOra = daOra;
    }

    public Date getaOra() {
        return aOra;
    }

    public void setaOra(Date aOra) {
        this.aOra = aOra;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public boolean isDestinazioneRaggiunta() {
        return destinazioneRaggiunta;
    }

    public void setDestinazioneRaggiunta(boolean destinazioneRaggiunta) {
        this.destinazioneRaggiunta = destinazioneRaggiunta;
    }

    public int getArrivalDelayRange() {
        return arrivalDelayRange;
    }

    public void setArrivalDelayRange(int arrivalDelayRange) {
        this.arrivalDelayRange = arrivalDelayRange;
    }
}
