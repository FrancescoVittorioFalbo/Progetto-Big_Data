package it.engineer.mtbd_backend.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class GeneralQuery implements Serializable {
    private Date daOra;
    private Date aOra;
    private String daAereoporto;
    private String aAereoporto;
    private String daStato;
    private String aStato;
    private String tempoMedioVolo;
    private String numPage;

    public GeneralQuery(){

    }

    public GeneralQuery(Date daOra, Date aOra){
        this.daOra = daOra;
        this.aOra = aOra;
    }

    public GeneralQuery(Date daOra, Date aOra, String tempoMedioVolo){
        this.daOra = daOra;
        this.aOra = aOra;
        this.tempoMedioVolo = tempoMedioVolo;
    }

    public GeneralQuery(Date daOra, Date aOra, String daAereoporto, String aAereoporto, String daStato, String aStato, String tempoMedioVolo, String numPage) {
        this.daOra = daOra;
        this.aOra = aOra;
        this.daAereoporto = daAereoporto;
        this.aAereoporto = aAereoporto;
        this.daStato = daStato;
        this.aStato = aStato;
        this.tempoMedioVolo = tempoMedioVolo;
        this.numPage = numPage;
    }

    @Override
    public String toString() {
        return "GeneralQuery{" +
                "daOra=" + daOra +
                ", aOra=" + aOra +
                ", daAereoporto='" + daAereoporto + '\'' +
                ", aAereoporto='" + aAereoporto + '\'' +
                ", daStato='" + daStato + '\'' +
                ", aStato='" + aStato + '\'' +
                ", tempoMedioVolo='" + tempoMedioVolo + '\'' +
                ", numPage='" + numPage + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeneralQuery that = (GeneralQuery) o;
        return Objects.equals(daOra, that.daOra) && Objects.equals(aOra, that.aOra) && Objects.equals(daAereoporto, that.daAereoporto) && Objects.equals(aAereoporto, that.aAereoporto) && Objects.equals(daStato, that.daStato) && Objects.equals(aStato, that.aStato) && Objects.equals(tempoMedioVolo, that.tempoMedioVolo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(daOra, aOra, daAereoporto, aAereoporto, daStato, aStato, tempoMedioVolo, numPage);
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

    public String getDaAereoporto() {
        return daAereoporto;
    }

    public void setDaAereoporto(String daAereoporto) {
        this.daAereoporto = daAereoporto;
    }

    public String getaAereoporto() {
        return aAereoporto;
    }

    public void setaAereoporto(String aAereoporto) {
        this.aAereoporto = aAereoporto;
    }

    public String getDaStato() {
        return daStato;
    }

    public void setDaStato(String daStato) {
        this.daStato = daStato;
    }

    public String getaStato() {
        return aStato;
    }

    public void setaStato(String aStato) {
        this.aStato = aStato;
    }

    public String getTempoMedioVolo() {
        return tempoMedioVolo;
    }

    public void setTempoMedioVolo(String tempoMedioVolo) {
        this.tempoMedioVolo = tempoMedioVolo;
    }

    public String getNumPage() {
        return numPage;
    }

    public void setNumPage(String numPage) {
        this.numPage = numPage;
    }
}
