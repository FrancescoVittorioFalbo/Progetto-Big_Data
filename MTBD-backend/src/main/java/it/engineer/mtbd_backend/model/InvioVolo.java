package it.engineer.mtbd_backend.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class InvioVolo implements Serializable {
    private List<Volo1> volo;
    private int numRes;

    public InvioVolo(List<Volo1> volo, int numRes) {
        this.volo = volo;
        this.numRes = numRes;
    }

    @Override
    public String toString() {
        return "InvioVolo{" +
                "volo=" + volo +
                ", numRes=" + numRes +
                '}';
    }

    public List<Volo1> getVolo() {
        return volo;
    }

    public void setVolo(List<Volo1> volo) {
        this.volo = volo;
    }

    public int getNumRes() {
        return numRes;
    }

    public void setNumRes(int numRes) {
        this.numRes = numRes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvioVolo invioVolo = (InvioVolo) o;
        return numRes == invioVolo.numRes && Objects.equals(volo, invioVolo.volo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(volo, numRes);
    }
}
