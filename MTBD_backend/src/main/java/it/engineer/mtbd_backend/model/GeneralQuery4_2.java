package it.engineer.mtbd_backend.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class GeneralQuery4_2 implements Serializable {

    private Date daOra;
    private Date aOra;
    private String tipologia;
    private boolean destinazioneRaggiunta;
    private int arrivalDelayRange;
    private String numPage;
    private int numDiv;

    // 0 => indifferente
    // 1 => 1
    // 2 => 2
    // 3 => >=3

    public GeneralQuery4_2() {
    }

    public GeneralQuery4_2(Date daOra, Date aOra, String tipologia, boolean destinazioneRaggiunta, int arrivalDelayRange, String numPage, int numDiv) {
        this.daOra = daOra;
        this.aOra = aOra;
        this.tipologia = tipologia;
        this.destinazioneRaggiunta = destinazioneRaggiunta;
        this.arrivalDelayRange = arrivalDelayRange;
        this.numPage = numPage;
        this.numDiv = numDiv;
    }

    public int getNumDiv() {
        return numDiv;
    }

    public void setNumDiv(int numDiv) {
        this.numDiv = numDiv;
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

    public String getNumPage() {
        return numPage;
    }

    public void setNumPage(String numPage) {
        this.numPage = numPage;
    }

    @Override
    public String toString() {
        return "GeneralQuery4_2{" +
                "daOra=" + daOra +
                ", aOra=" + aOra +
                ", tipologia='" + tipologia + '\'' +
                ", destinazioneRaggiunta=" + destinazioneRaggiunta +
                ", arrivalDelayRange=" + arrivalDelayRange +
                ", numPage='" + numPage + '\'' +
                ", numDiv='" + numDiv + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeneralQuery4_2 that = (GeneralQuery4_2) o;
        return destinazioneRaggiunta == that.destinazioneRaggiunta &&
                arrivalDelayRange == that.arrivalDelayRange &&
                Objects.equals(daOra, that.daOra) &&
                Objects.equals(aOra, that.aOra) &&
                Objects.equals(tipologia, that.tipologia) &&
                Objects.equals(numDiv, that.numDiv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(daOra, aOra, tipologia, destinazioneRaggiunta, arrivalDelayRange, numPage, numDiv);
    }
}
