package it.engineer.mtbd_backend.model;

import scala.Tuple2;

import java.io.Serializable;
import java.util.*;

public class Volo2 implements Serializable {

    private List<Tuple2<String,Double>> tipoDouble = new LinkedList<>();
    private List<Tuple2<String,Long>> tipoLong = new LinkedList<>();

    @Override
    public String toString() {
        return "Volo2{" +
                "tipoDouble=" + tipoDouble +
                ", tipoLong=" + tipoLong +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Volo2 volo2 = (Volo2) o;
        return Objects.equals(tipoDouble, volo2.tipoDouble) && Objects.equals(tipoLong, volo2.tipoLong);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipoDouble, tipoLong);
    }

    public List<Tuple2<String, Double>> getTipoDouble() {
        return tipoDouble;
    }

    public void setTipoDouble(List<Tuple2<String, Double>> tipoDouble) {
        this.tipoDouble = tipoDouble;
    }

    public List<Tuple2<String, Long>> getTipoLong() {
        return tipoLong;
    }

    public void setTipoLong(List<Tuple2<String, Long>> tipoLong) {
        this.tipoLong = tipoLong;
    }

    public List<Tuple2<String, Long>> getTipoLong2() {
        return tipoLong;
    }

    public void setTipoLong2(List<Tuple2<String, Long>> tipoLong) {
        if(tipoLong.size()<=4){
            this.tipoLong = tipoLong;
        }else if(tipoLong.size()>4 && tipoLong.size()<=17){
            List<Tuple2<String, Long>> nuova = new LinkedList<>(tipoLong.subList(0,tipoLong.size()-5));//tipoLong.subList(0,12);
            Long l = tipoLong
                    .subList(tipoLong.size()-5, tipoLong.size())
                    .stream()
                    .map(x->x._2)
                    .reduce((a,b)->a+b)
                    .get();
            Tuple2<String, Long> t2 = new Tuple2<>("Altre", l);
            nuova.add(t2);
            this.tipoLong = nuova;
        }else{
            List<Tuple2<String, Long>> nuova = new LinkedList<>(tipoLong.subList(0,30)); //tipoLong.subList(0,30);
            Long l = tipoLong.subList(30, tipoLong.size()).stream().map(x->x._2).reduce((a,b)->a+b).get();
            Tuple2<String, Long> t2 = new Tuple2<>("Altre ("+(tipoLong.size()-30)+")", l);
            nuova.add(t2);
            this.tipoLong = nuova;
        }
    }
}
