package it.engineer.mtbd_backend.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class GeneralQuery3 implements Serializable {
    private Date daOra;
    private Date aOra;
    private String tipologia;

    public GeneralQuery3(Date daOra, Date aOra, String tipologia) {
        this.daOra = daOra;
        this.aOra = aOra;
        this.tipologia = tipologia;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeneralQuery3 that = (GeneralQuery3) o;
        return Objects.equals(daOra, that.daOra) && Objects.equals(aOra, that.aOra) && Objects.equals(tipologia, that.tipologia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(daOra, aOra, tipologia);
    }

    @Override
    public String toString() {
        return "GeneralQuery3{" +
                "daOra=" + daOra +
                ", aOra=" + aOra +
                ", tipologia='" + tipologia + '\'' +
                '}';
    }
}
