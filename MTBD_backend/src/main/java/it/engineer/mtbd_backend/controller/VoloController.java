package it.engineer.mtbd_backend.controller;

import it.engineer.mtbd_backend.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import real_class.GestioneVolo;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
public class VoloController {

    private GeneralQuery4_2 queryDiv = new GeneralQuery4_2();
    private List<Volo4> voliDiv = new LinkedList<>();
    private int sizeDiv = -1;

    @GetMapping("/getCities")
    public ResponseEntity<Set<City>> getCities() {
        return new ResponseEntity<>( GestioneVolo.getCity().stream().collect(Collectors.toSet()), HttpStatus.OK);
    }



    private GeneralQuery richiediVoliQuery = new GeneralQuery();
    private int sizeListVolo = -1;
    private List<Volo1> generalVolo = new LinkedList<>();

    @PostMapping("/richiediVoli")
    public ResponseEntity<InvioVolo> richiediVoli(@RequestBody GeneralQuery gq){
        System.out.println(gq);
        List<Volo1> res;
        if(this.richiediVoliQuery.equals(gq)){
            int n = Math.min(Integer.parseInt(gq.getNumPage()) * 15 + 15, sizeListVolo);
            res = this.generalVolo.size()>0 ? this.generalVolo.subList(Integer.parseInt(gq.getNumPage())*15, n): this.generalVolo;
        }else{
            this.richiediVoliQuery = gq;
            this.generalVolo = GestioneVolo.query1(gq);
            this.sizeListVolo = this.generalVolo.size();
            res=this.generalVolo.subList(0, Math.min(this.generalVolo.size(), 15));
        }
        return new ResponseEntity<>(new InvioVolo(res, this.sizeListVolo), HttpStatus.OK);
    }

    @PostMapping("/query2")
    public ResponseEntity<Volo2> query2(@RequestBody GeneralQuery2 gq2){
        System.out.println(gq2);
        return new ResponseEntity<>(GestioneVolo.query2_voliAndatiBene_analisi(gq2), HttpStatus.OK);
    }

    @PostMapping("/query3")
    public ResponseEntity<Volo2> query3(@RequestBody GeneralQuery3 gq){
        System.out.println(gq);
        return new ResponseEntity<>(GestioneVolo.query3_voliCancellati_analisi(gq), HttpStatus.OK);
    }

    @PostMapping("/query4")
    public ResponseEntity<Volo2> query4(@RequestBody GeneralQuery4 gq){
        System.out.println(gq);
        return new ResponseEntity<>(GestioneVolo.query4_voliDirottati_analisi(gq), HttpStatus.OK);
    }

    @PostMapping("/query4_2")
    public ResponseEntity<InvioVolo4> query4_2(@RequestBody GeneralQuery4_2 gq){
        System.out.println(gq);
        List<Volo4> res;
        if(this.queryDiv.equals(gq)){
            int n = Math.min(Integer.parseInt(gq.getNumPage()) * 15 + 15, sizeDiv);
            res = this.voliDiv.size()>0 ? this.voliDiv.subList(Integer.parseInt(gq.getNumPage())*15, n): this.voliDiv;
        }else{
            this.queryDiv = gq;
            this.voliDiv = GestioneVolo.query4_lista(gq);
            this.sizeDiv = this.voliDiv.size();
            res=this.voliDiv.subList(0, Math.min(this.voliDiv.size(), 15));
        }
        return new ResponseEntity<>(new InvioVolo4(res, this.sizeDiv), HttpStatus.OK);
    }
}
