package it.engineer.mtbd_backend.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class InvioVolo4 implements Serializable {

    private List<Volo4> volo;
    private int numRes;

    public InvioVolo4(List<Volo4> volo, int numRes) {
        this.volo = volo;
        this.numRes = numRes;
    }

    @Override
    public String toString() {
        return "InvioVolo4{" +
                "volo=" + volo +
                ", numRes=" + numRes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvioVolo4 that = (InvioVolo4) o;
        return numRes == that.numRes && Objects.equals(volo, that.volo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(volo, numRes);
    }

    public List<Volo4> getVolo() {
        return volo;
    }

    public void setVolo(List<Volo4> volo) {
        this.volo = volo;
    }

    public int getNumRes() {
        return numRes;
    }

    public void setNumRes(int numRes) {
        this.numRes = numRes;
    }
}
