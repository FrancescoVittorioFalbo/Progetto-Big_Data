package it.engineer.mtbd_backend.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class GeneralQuery2 implements Serializable {
    private Date daOra;
    private Date aOra;
    private String tempoMedioVolo;
    private String tipoRichiesta;
    private int numRisultati;

    public GeneralQuery2(Date daOra, Date aOra, String tempoMedioVolo, String tipoRichiesta, int numRisultati) {
        this.daOra = daOra;
        this.aOra = aOra;
        this.tempoMedioVolo = tempoMedioVolo;
        this.tipoRichiesta = tipoRichiesta;
        this.numRisultati = numRisultati;
    }

    @Override
    public String toString() {
        return "GeneralQuery2{" +
                "daOra=" + daOra +
                ", aOra=" + aOra +
                ", tempoMedioVolo='" + tempoMedioVolo + '\'' +
                ", tipoRichiesta='" + tipoRichiesta + '\'' +
                ", numRisultati=" + numRisultati +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeneralQuery2 that = (GeneralQuery2) o;
        return numRisultati == that.numRisultati && Objects.equals(daOra, that.daOra) && Objects.equals(aOra, that.aOra) && Objects.equals(tempoMedioVolo, that.tempoMedioVolo) && Objects.equals(tipoRichiesta, that.tipoRichiesta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(daOra, aOra, tempoMedioVolo, tipoRichiesta, numRisultati);
    }

    public Date getDaOra() {
        return daOra;
    }

    public Date getaOra() {
        return aOra;
    }

    public String getTempoMedioVolo() {
        return tempoMedioVolo;
    }

    public String getTipoRichiesta() {
        return tipoRichiesta;
    }

    public int getNumRisultati() {
        return numRisultati;
    }
}
