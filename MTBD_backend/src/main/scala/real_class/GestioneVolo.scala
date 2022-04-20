package real_class

import it.engineer.mtbd_backend.model.{City, Div, GeneralQuery, GeneralQuery2, GeneralQuery3, GeneralQuery4, GeneralQuery4_2, Volo, Volo1, Volo2, Volo4}
import org.apache.spark.SparkConf
import org.apache.spark.sql.functions.{col, concat, desc, lit}
import org.apache.spark.sql.{DataFrame, Encoders, Row, SparkSession}

import scala.collection.mutable.ListBuffer
import scala.collection.JavaConverters._
import org.apache.spark.sql.types.{DoubleType, StringType, StructField, StructType}

import java.io.File
import java.sql.Date
import java.util

import  org.apache.spark.ml.Pipeline
import  org.apache.spark.ml.evaluation.RegressionEvaluator
import  org.apache.spark.ml.feature.VectorIndexer
import  org.apache.spark.ml.regression. { GBTRegressionModel ,  GBTRegressor }

object GestioneVolo{

  var id: Integer = -1

  var DF: DataFrame = null
  var DF_ok: DataFrame = null
  var DF_cancelled: DataFrame = null
  var DF_diverted: DataFrame = null

  var cityList: ListBuffer[City] = new ListBuffer[City]()
  var voloList: ListBuffer[Volo] = new ListBuffer[Volo]()
  var voli: java.util.List[Volo1] = new util.LinkedList
  var listOfFiles: List[File] = null
  var spark: SparkSession = null
  var pathCompagnie = ""
  var deleteMotivation: DataFrame = null

  def start(path : String, pathCompagnie: String): Unit = {

    val conf = new SparkConf().setMaster("local[*]").setAppName("MTBD")

      spark = SparkSession
      .builder()
      .appName("MTBD")
      .config(conf)
      .getOrCreate()

    this.pathCompagnie = pathCompagnie
    selectFolder(path)


    DF = spark.read.option("header", "true").csv(path)
    val DFN = DF.select("Origin", "OriginCityName", "OriginStateName",
                             "Dest", "DestCityName", "DestStateName")

    DFN.collect().foreach(row => {
      val c1 = new City(row.get(0).toString, row.get(1).toString, row.get(2).toString)
      val c2 = new City(row.get(3).toString, row.get(4).toString, row.get(5).toString)
      cityList.append(c1)
      cityList.append(c2)
      print("")
    })

    deleteMotivation = spark.createDataFrame(
      spark.sparkContext
      .parallelize(Array(
        ("A", "Decisione della compagnia aerea"),
        ("B" , "Condizioni metereologiche"),
        ("C" , "Motivi di aviazione nazionale"),
        ("D" , "Problemi di sicurezza"))))
      .toDF("CCode","Motivation")

    println("READY!")

  }

  def switch(): Unit = {
    val list = new Array[File](12)
    list(0) = listOfFiles(0)
    list(1) = listOfFiles(4)
    list(2) = listOfFiles(5)
    list(3) = listOfFiles(6)
    list(4) = listOfFiles(7)
    list(5) = listOfFiles(8)
    list(6) = listOfFiles(9)
    list(7) = listOfFiles(10)
    list(8) = listOfFiles(11)
    list(9) = listOfFiles(1)
    list(10) = listOfFiles(2)
    list(11) = listOfFiles(3)
    listOfFiles = list.toList
  }

  def selectFolder(path: String): Unit = {
    val folder = new File(path)
    listOfFiles = folder.listFiles().toList
    switch()
  }

  def selectCSV(gq: GeneralQuery): List[String] = {
    val list = listOfFiles
    println("RICEVUTO: "+gq)
    val partenza = gq.getDaOra.getMonth
    val arrivo = gq.getaOra().getMonth
    println("MESE START: "+partenza+"; STOP: "+arrivo)
    list.slice(partenza,arrivo+1).map(file => file.getPath)
  }

  def castDF(lista : List[String]): Unit = {
    val set: Array[DataFrame] = new Array[DataFrame](lista.size)

    for( i <- 0 to lista.size-1)
      set(i) = spark.read.option("header", "true").csv(lista(i))

    DF = set(0)
    for( i <- 1 to lista.size-1)
      DF = DF.union(set(i))
  }

  def castDF_ok(lista : List[String]): Unit = {
    val set: Array[DataFrame] = new Array[DataFrame](lista.size)

    for( i <- 0 to lista.size-1)
      set(i) = spark.read.option("header", "true").csv(lista(i))

    DF_ok = set(0)
    for( i <- 1 to lista.size-1)
      DF_ok = DF_ok.union(set(i))

    DF_ok = DF_ok.filter(row => {
      val v = parse(row)
      !v.isDiverted && !v.isDiverted
    })
  }

  def castDF_canc(lista : List[String]): Unit = {
    val set: Array[DataFrame] = new Array[DataFrame](lista.size)

    for( i <- 0 to lista.size-1)
      set(i) = spark.read.option("header", "true").csv(lista(i))

    DF_cancelled = set(0)
    for( i <- 1 to lista.size-1)
      DF_cancelled = DF_cancelled.union(set(i))

    DF_cancelled = DF_cancelled.filter(row => {
      val v = parse(row)
      v.isCancelled
    })
  }

  def castDF_div(lista : List[String]): Unit = {
    val set: Array[DataFrame] = new Array[DataFrame](lista.size)

    for( i <- 0 to lista.size-1)
      set(i) = spark.read.option("header", "true").csv(lista(i))

    DF_diverted = set(0)
    for( i <- 1 to lista.size-1)
      DF_diverted = DF_diverted.union(set(i))

    DF_diverted = DF_diverted.filter(row => {
      val v = parse(row)
      v.isDiverted
    })
  }

  def parse(cols: Row): Volo = {
    id=id+1

    new Volo(id,
      cols.getAs(0).toString.toInt,
      cols.getAs(2).toString.toInt,
      cols.getAs(3).toString.toInt,
      cols.getAs(4).toString.toInt,
      Date.valueOf(cols.getAs(5).toString),
      cols.getAs(8).toString,
      if(cols.getAs(9)==null) ""
      else cols.getAs(9).toString,
      cols.getAs(10).toString.toInt,
      cols.getAs(11).toString.toInt,
      cols.getAs(13).toString.toInt,
      cols.getAs(14).toString,
      cols.getAs(15).toString,
      cols.getAs(16).toString,
      cols.getAs(18).toString,
      cols.getAs(20).toString.toInt,
      cols.getAs(22).toString.toInt,
      cols.getAs(23).toString,
      cols.getAs(24).toString,
      cols.getAs(25).toString,
      cols.getAs(27).toString,
      cols.getAs(29).toString.toInt,
      if(cols.getAs(30)==null) -1
      else cols.getAs(30).toString.toInt,
      if(cols.getAs(31)==null) -1
      else cols.getAs(31).toString.toDouble,
      if(cols.getAs(36)==null) -1
      else cols.getAs(36).toString.toDouble,
      if(cols.getAs(37)==null) -1
      else cols.getAs(37).toString.toInt,
      if(cols.getAs(38) == null) -1
      else cols.getAs(38).toString.toInt,
      if(cols.getAs(39) == null) -1
      else cols.getAs(39).toString.toDouble,
      if(cols.getAs(40) == null) -1
      else cols.getAs(40).toString.toInt,
      if(cols.getAs(41) == null) -1
      else cols.getAs(41).toString.toInt,
      if(cols.getAs(42) == null) -1
      else cols.getAs(42).toString.toDouble,
      !cols.getAs(47).toString.equals("0.00"),
      if(cols.getAs(48) == null) ""
      else cols.getAs(48).toString,
      if(cols.getAs(49) == null) false
      else !cols.getAs(49).toString.equals("0.00"),
      if(cols.getAs(50) == null) -1
      else cols.getAs(50).toString.toDouble,
      if(cols.getAs(51) == null) -1
      else cols.getAs(51).toString.toDouble,
      if(cols.getAs(53) == null) -1
      else cols.getAs(53).toString.toDouble,
      if(cols.getAs(54) == null) -1
      else cols.getAs(54).toString.toDouble,
      if(cols.getAs(56) == null) -1
      else cols.getAs(56).toString.toDouble,
      if(cols.getAs(59) == null) -1
      else cols.getAs(59).toString.toDouble,
      if(cols.getAs(60) == null) -1
      else cols.getAs(60).toString.toDouble,
      cols.getAs(64).toString.toInt,
      if(cols.getAs(65) == null) false
      else cols.getAs(65).toString.equals("0"),
      if(cols.getAs(66) == null) -1
      else cols.getAs(66).toString.toDouble,
      if(cols.getAs(67) == null) -1
      else cols.getAs(67).toString.toDouble,
      if(cols.getAs(68) == null) -1
      else cols.getAs(68).toString.toDouble,
      List(new Div(
        if(cols.getAs(69) == null) ""
        else cols.getAs(69).toString,

        if(cols.getAs(70) == null) -1
        else cols.getAs(70).toString.toInt,

        if(cols.getAs(72) == null) -1
        else cols.getAs(72).toString.toInt,

        if(cols.getAs(73) == null) -1
        else cols.getAs(73).toString.toDouble,

        if(cols.getAs(74) == null) -1
        else cols.getAs(74).toString.toDouble,

        if(cols.getAs(75) == null) -1
        else cols.getAs(75).toString.toInt,

        if(cols.getAs(76) == null) ""
        else cols.getAs(76).toString),
        new Div(
          if(cols.getAs(77) == null) ""
          else cols.getAs(77).toString,

          if(cols.getAs(78) == null) -1
          else cols.getAs(78).toString.toInt,

          if(cols.getAs(80) == null) -1
          else cols.getAs(80).toString.toInt,

          if(cols.getAs(81) == null) -1
          else cols.getAs(81).toString.toDouble,

          if(cols.getAs(82) == null) -1
          else cols.getAs(82).toString.toDouble,

          if(cols.getAs(83) == null) -1
          else cols.getAs(83).toString.toInt,

          if(cols.getAs(84) == null) ""
          else cols.getAs(84).toString),

        new Div(
          if(cols.getAs(85) == null) ""
          else cols.getAs(85).toString,

          if(cols.getAs(86) == null) -1
          else cols.getAs(86).toString.toInt,

          if(cols.getAs(88) == null) -1
          else cols.getAs(88).toString.toInt,

          if(cols.getAs(89) == null) -1
          else cols.getAs(89).toString.toDouble,

          if(cols.getAs(90) == null) -1
          else cols.getAs(90).toString.toDouble,

          if(cols.getAs(91) == null) -1
          else cols.getAs(91).toString.toInt,

          if(cols.getAs(92) == null) ""
          else cols.getAs(92).toString),

        new Div(
          if(cols.getAs(93) == null) ""
          else cols.getAs(93).toString,

          if(cols.getAs(94) == null) -1
          else cols.getAs(94).toString.toInt,

          if(cols.getAs(96) == null) -1
          else cols.getAs(96).toString.toInt,

          if(cols.getAs(97) == null) -1
          else cols.getAs(97).toString.toDouble,

          if(cols.getAs(98) == null) -1
          else cols.getAs(98).toString.toDouble,

          if(cols.getAs(99) == null) -1
          else cols.getAs(99).toString.toInt,

          if(cols.getAs(100) == null) ""
          else cols.getAs(100).toString),

        new Div(
          if(cols.getAs(101) == null) ""
          else cols.getAs(101).toString,

          if(cols.getAs(102) == null) -1
          else cols.getAs(102).toString.toInt,

          if(cols.getAs(104) == null) -1
          else cols.getAs(104).toString.toInt,

          if(cols.getAs(105) == null) -1
          else cols.getAs(105).toString.toDouble,

          if(cols.getAs(106) == null) -1
          else cols.getAs(106).toString.toDouble,

          if(cols.getAs(107) == null) -1
          else cols.getAs(107).toString.toInt,

          if(cols.getAs(108) == null) ""
          else cols.getAs(108).toString)).asJava)
  }

  def getCity: util.List[City] = {
    mutableSeqAsJavaList(cityList)
  }

  def repair(s: String): String = {
    val s1 = s.replaceAll("[\\[\\](){}]", "").trim
    s1
  }

  def verificaTempoVolo(tempo: String, rc: Volo): Boolean ={
    if(tempo.equals("")) return true
    val tempoInt: Int = tempo.toInt
    if(tempoInt == -1) return true
    if(tempoInt == 0 && rc.getActualElapsedTime <= 60) return true
    if(tempoInt == 1 && rc.getActualElapsedTime >= 60 && rc.getActualElapsedTime <= 120) return true
    if(tempoInt == 2 && rc.getActualElapsedTime >= 120 && rc.getActualElapsedTime <= 300) return true
    if(tempoInt == 3 && rc.getActualElapsedTime >= 300) return true
    false
  }

  def verificaTempoVolo1(tempo: String, rc: Volo1): Boolean ={
    if(tempo.equals("")) return true
    val tempoInt: Int = tempo.toInt
    if(tempoInt == -1) return true
    if(tempoInt == 0 && rc.getActualElapsedTime <= 60) return true
    if(tempoInt == 1 && rc.getActualElapsedTime >= 60 && rc.getActualElapsedTime <= 120) return true
    if(tempoInt == 2 && rc.getActualElapsedTime >= 120 && rc.getActualElapsedTime <= 300) return true
    if(tempoInt == 3 && rc.getActualElapsedTime >= 300) return true
    false
  }

  def parse2(row: Row): Volo1 = {
    new Volo1(
        if(row.getAs(0) == null) Date.valueOf("1-1-1970")
        else Date.valueOf(row.getAs(0).toString),
        if(row.getAs(1) == null) ""
        else row.get(1).toString,
        if(row.getAs(2) == null) ""
        else row.get(2).toString,
        if(row.getAs(3) == null) ""
        else row.get(3).toString,
        if(row.getAs(4) == null) ""
        else row.get(4).toString,
        if(row.getAs(5) == null) ""
        else row.get(5).toString,
        if(row.getAs(6) == null) ""
        else row.get(6).toString,
        if(row.getAs(7) == null) ""
        else row.get(7).toString,
        if(row.getAs(8) == null) -1
        else row.get(8).toString.toDouble,
        if(row.getAs(9) == null) -1
        else row.get(9).toString.toDouble,
        if(row.getAs(10) == null) -1
        else row.get(10).toString.toDouble,
    )
  }

  def query1(gq: GeneralQuery): java.util.List[Volo1] = {
    castDF(selectCSV(gq))
    import Volo1Encoders._
    DF.select("FlightDate", "Origin", "OriginCityName", "OriginStateName", "Dest",
      "DestCityName", "DestStateName", "Tail_Number", "ActualElapsedTime",
      "Flights", "Distance")
      .filter(row => {
        val v = parse2(row)
        (gq.getDaOra.getYear == 70 || (v.getFlight_date.compareTo(gq.getDaOra) >= 0)) &&
          (gq.getaOra.getYear == 70 || v.getFlight_date.compareTo(gq.getaOra) <= 0) &&
          verificaTempoVolo1(gq.getTempoMedioVolo, v) &&
          (gq.getDaAereoporto.equals("") || v.getOriginAirport == gq.getDaAereoporto) &&
          (gq.getaAereoporto.equals("") || v.getDestAirport == gq.getaAereoporto) &&
          (gq.getaStato.equals("") || v.getDestStateName == gq.getaStato) &&
          (gq.getDaStato.equals("") || v.getOriginStateName == gq.getDaStato) &&
          v.getActualElapsedTime != -1
      }).map[Volo1](row => parse2(row)).collectAsList
  }

  def query2_voliAndatiBene_analisi(gq2: GeneralQuery2): Volo2 = {
    val gq = new GeneralQuery(gq2.getDaOra, gq2.getaOra, gq2.getTempoMedioVolo)
    castDF_ok(selectCSV(gq))
    val res: Volo2 = new Volo2
    val encoder1 = Encoders.product[(String, java.lang.Double)]
    val encoder2 = Encoders.product[(String, java.lang.Long)]

    var DF_tmp = DF_ok.filter(row => {
      val v = parse(row)
      (gq.getDaOra.getYear == 70 || (v.getFlight_date.compareTo(gq.getDaOra) >= 0)) &&
        (gq.getaOra.getYear == 70 || v.getFlight_date.compareTo(gq.getaOra) <= 0) &&
        verificaTempoVolo(gq.getTempoMedioVolo, v) &&
        v.getActualElapsedTime != -1
    })
    println(gq2)
    if(gq2.getTipoRichiesta == "AereoportiPerPuntualità"){
      res.setTipoDouble(
      DF_tmp
        .withColumn("DepDelayMinutes", col("DepDelayMinutes").cast(DoubleType))
        .withColumn("output", concat(col("Origin"), lit(" - "), col("OriginCityName")))
        .groupBy("output")
        .avg("DepDelayMinutes")
        .sort("avg(DepDelayMinutes)")
        .filter(row => {row.getAs("avg(DepDelayMinutes)").toString.toDouble > 0})
        .limit(gq2.getNumRisultati)
        .as[(String, java.lang.Double)](encoder1).rdd.collect().toList.asJava
      )
    }
    else if (gq2.getTipoRichiesta == "CittaPerVoliInPartenza"){
      res.setTipoLong(DF_tmp
        .groupBy("OriginCityName")
        .count()
        .sort(desc("count"))
        .limit(gq2.getNumRisultati)
        .as[(String, java.lang.Long)](encoder2).rdd.collect().toList.asJava)
    }
    else if(gq2.getTipoRichiesta == "CompagnieAereePerNumeroDiVoli"){

      val customSchema = StructType(Array(
        StructField("IATA", StringType, true),
        StructField("NomeCompagnia", StringType, true)
      ))

      val DF_compagnie = spark.read.option("header", "true").option("delimiter",";").schema(customSchema)
        .csv(pathCompagnie)

      res.setTipoLong(
        DF_tmp
        .groupBy("IATA_CODE_Reporting_Airline")
        .count()
        .sort(desc("count"))
        .limit(gq2.getNumRisultati)
        .join(DF_compagnie, DF_tmp("IATA_CODE_Reporting_Airline") === DF_compagnie("IATA"))
        .select("NomeCompagnia","count")
        .sort(desc("count"))
        .as[(String, java.lang.Long)](encoder2).rdd.collect().toList.asJava)
    }
    res
  }

  def query3_voliCancellati_analisi(gq3: GeneralQuery3): Volo2 = {
    val gq = new GeneralQuery(gq3.getDaOra, gq3.getaOra)
    castDF_canc(selectCSV(gq))
    val res: Volo2 = new Volo2
    val encoder2 = Encoders.product[(String, java.lang.Long)]

    val DF_tmp = DF_cancelled
      .filter(row =>{
        val v = parse(row)
        (gq.getDaOra.getYear == 70 || (v.getFlight_date.compareTo(gq.getDaOra) >= 0)) &&
          (gq.getaOra.getYear == 70 || v.getFlight_date.compareTo(gq.getaOra) <= 0)
      })
      .select(
        col("IATA_CODE_Reporting_Airline"),
        col("Dest"),
        col("DestCityName"),
        col("CancellationCode"))

    if(gq3.getTipologia == "IATA"){

      val customSchema = StructType(Array(
        StructField("IATA", StringType, true),
        StructField("NomeCompagnia", StringType, true)
      ))

      val DF_compagnie = spark.read.option("header", "true").option("delimiter",";").schema(customSchema)
        .csv(pathCompagnie)

      res.setTipoLong2(
        DF_tmp
        .groupBy("IATA_CODE_Reporting_Airline")
        .count()
        .sort("count")
        .join(DF_compagnie, DF_tmp("IATA_CODE_Reporting_Airline") === DF_compagnie("IATA"))
        .select("NomeCompagnia","count")
        .sort(desc("count"))
        .as[(String, java.lang.Long)](encoder2).rdd.collect().toList.asJava)

    }else if(gq3.getTipologia == "Dest"){
      res.setTipoLong2(DF_tmp
        .withColumn("output", concat(col("Dest"), lit(" - "), col("DestCityName")))
        .groupBy("output")
        .count()
        .sort(desc("count"))
        .as[(String, java.lang.Long)](encoder2).rdd.collect().toList.asJava)
    }else if(gq3.getTipologia == "CancellationCode"){
      res.setTipoLong2(DF_tmp
        .groupBy("CancellationCode")
        .count()
        .join(deleteMotivation,col("CancellationCode") === col("CCode"))
        .select("Motivation","count")
        .sort(desc("count"))
        .as[(String, java.lang.Long)](encoder2).rdd.collect().toList.asJava)
    }
    res
  }

  def query4_voliDirottati_analisi(gq4: GeneralQuery4): Volo2 = {
    val gq = new GeneralQuery(gq4.getDaOra, gq4.getaOra)
    castDF_div(selectCSV(gq))
    val res: Volo2 = new Volo2
    val encoder1 = Encoders.product[(String, java.lang.Double)]
    val encoder2 = Encoders.product[(String, java.lang.Long)]

    val DF_tmp = DF_diverted.filter(
      row =>{
      val v = parse(row)
      (gq.getDaOra.getYear == 70 || (v.getFlight_date.compareTo(gq.getDaOra) >= 0)) &&
        (gq.getaOra.getYear == 70 || v.getFlight_date.compareTo(gq.getaOra) <= 0)
    })

    if(gq4.getTipologia == "IATA"){
      val customSchema = StructType(Array(
        StructField("IATA", StringType, true),
        StructField("NomeCompagnia", StringType, true)
      ))
      val DF_compagnie = spark.read.option("header", "true").option("delimiter",";").schema(customSchema)
        .csv(pathCompagnie)

      res.setTipoLong2(
        DF_tmp
          .filter(row => {
            val v = parse(row)
            if(gq4.isDestinazioneRaggiunta){
              v.isDivReachedDest &&
                (if(v.getDivActualElapsedTime == -1) {
                  false
              }
              else if(gq4.getArrivalDelayRange == 0){
                true
              }else if(gq4.getArrivalDelayRange == 1){
                v.getDivActualElapsedTime < 100
              } else if(gq4.getArrivalDelayRange == 2){
                v.getDivActualElapsedTime >= 100 && v.getDivActualElapsedTime <300
              } else if(gq4.getArrivalDelayRange == 3){
                v.getDivActualElapsedTime >= 300 && v.getDivActualElapsedTime <1000
              } else {
                v.getDivActualElapsedTime >= 1000
              }) &&
                (if(gq4.getNumDiv == 0) true
              else if(row.getAs("DivAirportLandings") == null) false
              else if(gq4.getNumDiv == 1) row.getAs("DivAirportLandings").toString.toInt == 1
              else if(gq4.getNumDiv == 2) row.getAs("DivAirportLandings").toString.toInt == 2
              else row.getAs("DivAirportLandings").toString.toInt >= 3)
            }
            !v.isDivReachedDest &&
              (if(v.getDivActualElapsedTime == -1) {
                false
              }
              else if(gq4.getArrivalDelayRange == 0){
                true
              }else if(gq4.getArrivalDelayRange == 1){
                v.getDivActualElapsedTime < 100
              } else if(gq4.getArrivalDelayRange == 2){
                v.getDivActualElapsedTime >= 100 && v.getDivActualElapsedTime <300
              } else if(gq4.getArrivalDelayRange == 3){
                v.getDivActualElapsedTime >= 300 && v.getDivActualElapsedTime <1000
              } else {
                v.getDivActualElapsedTime >= 1000
              }) &&
              (if(gq4.getNumDiv == 0) true
            else if(row.getAs("DivAirportLandings") == null) false
            else if(gq4.getNumDiv == 1) row.getAs("DivAirportLandings").toString.toInt == 1
            else if(gq4.getNumDiv == 2) row.getAs("DivAirportLandings").toString.toInt == 2
            else row.getAs("DivAirportLandings").toString.toInt >= 3)
          })
          .groupBy("IATA_CODE_Reporting_Airline")
          .count()
          .sort("count")
          .join(DF_compagnie, DF_tmp("IATA_CODE_Reporting_Airline") === DF_compagnie("IATA"))
          .select("NomeCompagnia","count")
          .sort(desc("count"))
          .as[(String, java.lang.Long)](encoder2).rdd.collect().toList.asJava)

    }else if(gq4.getTipologia == "Dest"){

      res.setTipoLong2(DF_tmp
        .filter(row => {
          val v = parse(row)
          if(gq4.isDestinazioneRaggiunta){
            v.isDivReachedDest &&
              (if(v.getDivActualElapsedTime == -1) {
                false
              }
              else if(gq4.getArrivalDelayRange == 0){
                true
              }else if(gq4.getArrivalDelayRange == 1){
                v.getDivActualElapsedTime < 100
              } else if(gq4.getArrivalDelayRange == 2){
                v.getDivActualElapsedTime >= 100 && v.getDivActualElapsedTime <300
              } else if(gq4.getArrivalDelayRange == 3){
                v.getDivActualElapsedTime >= 300 && v.getDivActualElapsedTime <1000
              } else {
                v.getDivActualElapsedTime >= 1000
              }) &&
              (if(gq4.getNumDiv == 0) true
            else if(row.getAs("DivAirportLandings") == null) false
            else if(gq4.getNumDiv == 1) row.getAs("DivAirportLandings").toString.toInt == 1
            else if(gq4.getNumDiv == 2) row.getAs("DivAirportLandings").toString.toInt == 2
            else row.getAs("DivAirportLandings").toString.toInt >= 3)
          }
          !v.isDivReachedDest &&
            (if(v.getDivActualElapsedTime == -1) {
              false
            }
            else if(gq4.getArrivalDelayRange == 0){
              true
            }else if(gq4.getArrivalDelayRange == 1){
              v.getDivActualElapsedTime < 100
            } else if(gq4.getArrivalDelayRange == 2){
              v.getDivActualElapsedTime >= 100 && v.getDivActualElapsedTime <300
            } else if(gq4.getArrivalDelayRange == 3){
              v.getDivActualElapsedTime >= 300 && v.getDivActualElapsedTime <1000
            } else {
              v.getDivActualElapsedTime >= 1000
            }) &&
            (if(gq4.getNumDiv == 0) true
          else if(row.getAs("DivAirportLandings") == null) false
          else if(gq4.getNumDiv == 1) row.getAs("DivAirportLandings").toString.toInt == 1
          else if(gq4.getNumDiv == 2) row.getAs("DivAirportLandings").toString.toInt == 2
          else row.getAs("DivAirportLandings").toString.toInt >= 3)
        })
        .withColumn("output", concat(col("Dest"), lit(" - "), col("DestCityName")))
        .groupBy("output")
        .count()
        .sort(desc("count"))
        .as[(String, java.lang.Long)](encoder2).rdd.collect().toList.asJava)
    }
    res
  }

  def query4_lista(gq4: GeneralQuery4_2): java.util.List[Volo4] = {
    val gq = new GeneralQuery(gq4.getDaOra, gq4.getaOra)
    val gq4_2 = new GeneralQuery4()
    gq4_2.setArrivalDelayRange(gq4.getArrivalDelayRange)
    castDF_div(selectCSV(gq))
    import Volo4Encoders._
    val DF_tmp = DF_diverted.filter(row =>{
      val v = parse(row)
      (gq.getDaOra.getYear == 70 || (v.getFlight_date.compareTo(gq.getDaOra) >= 0)) &&
        (gq.getaOra.getYear == 70 || v.getFlight_date.compareTo(gq.getaOra) <= 0)
    })

    DF_tmp
      .select("FlightDate", "Origin", "OriginCityName", "OriginStateName",
        "Dest", "DestCityName", "DivArrDelay", "DivAirportLandings","DivDistance","DivReachedDest")
      .filter(row => {
        val v = parse4(row)
        if(gq4.isDestinazioneRaggiunta){
          (if(v.getRitardo == -1) {
            true
          } else if(gq4_2.getArrivalDelayRange == 0){
            true
          }else if(gq4_2.getArrivalDelayRange == 1){
            v.getRitardo < 100
          } else if(gq4_2.getArrivalDelayRange == 2){
            v.getRitardo >= 100 && v.getRitardo <300
          } else if(gq4_2.getArrivalDelayRange == 3){
            v.getRitardo >= 300 && v.getRitardo <1000
          } else {
            v.getRitardo >= 1000
          }) &&
          (row.getAs("DivReachedDest") == null || row.getAs("DivReachedDest").toString.equals("1.00")) && (
            if(gq4.getNumDiv == 0) true
            else if(row.getAs("DivAirportLandings") == null) false
            else if(gq4.getNumDiv == 1) row.getAs("DivAirportLandings").toString.toInt == 1
            else if(gq4.getNumDiv == 2) row.getAs("DivAirportLandings").toString.toInt == 2
            else row.getAs("DivAirportLandings").toString.toInt >= 3)
        } else {
          (if(v.getRitardo == -1) {
            true
          } else if(gq4_2.getArrivalDelayRange == 0){
            true
          }else if(gq4_2.getArrivalDelayRange == 1){
            v.getRitardo < 100
          } else if(gq4_2.getArrivalDelayRange == 2){
            v.getRitardo >= 100 && v.getRitardo <300
          } else if(gq4_2.getArrivalDelayRange == 3){
            v.getRitardo >= 300 && v.getRitardo <1000
          } else {
            v.getRitardo >= 1000
          }) &&
            !(row.getAs("DivReachedDest") == null || row.getAs("DivReachedDest").toString.equals("1.00")) && (
            if(gq4.getNumDiv == 0) true
            else if(row.getAs("DivAirportLandings") == null) false
            else if(gq4.getNumDiv == 1) row.getAs("DivAirportLandings").toString.toInt == 1
            else if(gq4.getNumDiv == 2) row.getAs("DivAirportLandings").toString.toInt == 2
            else row.getAs("DivAirportLandings").toString.toInt >= 3)
        }})
      .sort(desc("DivArrDelay"))
      .show()

    DF_tmp
      .select("FlightDate", "Origin", "OriginCityName", "OriginStateName",
      "Dest", "DestCityName", "DivArrDelay", "DivAirportLandings","DivDistance","DivReachedDest")
      .filter(row => {
        val v = parse4(row)
        if(gq4.isDestinazioneRaggiunta){
          (if(v.getRitardo == -1) {
            true
          } else if(gq4_2.getArrivalDelayRange == 0){
            true
          }else if(gq4_2.getArrivalDelayRange == 1){
            v.getRitardo < 100
          } else if(gq4_2.getArrivalDelayRange == 2){
            v.getRitardo >= 100 && v.getRitardo <300
          } else if(gq4_2.getArrivalDelayRange == 3){
            v.getRitardo >= 300 && v.getRitardo <1000
          } else {
            v.getRitardo >= 1000
          }) &&
            (row.getAs("DivReachedDest") == null || row.getAs("DivReachedDest").toString.equals("1.00")) && (
              if(gq4.getNumDiv == 0) true
              else if(row.getAs("DivAirportLandings") == null) false
              else if(gq4.getNumDiv == 1) row.getAs("DivAirportLandings").toString.toInt == 1
              else if(gq4.getNumDiv == 2) row.getAs("DivAirportLandings").toString.toInt == 2
              else row.getAs("DivAirportLandings").toString.toInt >= 3)
        } else {
          (if(v.getRitardo == -1) {
            true
          } else if(gq4_2.getArrivalDelayRange == 0){
            true
          }else if(gq4_2.getArrivalDelayRange == 1){
            v.getRitardo < 100
          } else if(gq4_2.getArrivalDelayRange == 2){
            v.getRitardo >= 100 && v.getRitardo <300
          } else if(gq4_2.getArrivalDelayRange == 3){
            v.getRitardo >= 300 && v.getRitardo <1000
          } else {
            v.getRitardo >= 1000
          }) &&
          !(row.getAs("DivReachedDest") == null || row.getAs("DivReachedDest").toString.equals("1.00")) && (
        if(gq4.getNumDiv == 0) true
        else if(row.getAs("DivAirportLandings") == null) false
        else if(gq4.getNumDiv == 1) row.getAs("DivAirportLandings").toString.toInt == 1
        else if(gq4.getNumDiv == 2) row.getAs("DivAirportLandings").toString.toInt == 2
        else row.getAs("DivAirportLandings").toString.toInt >= 3)
      }})
      .sort(desc("DivArrDelay"))
      .map[Volo4](row => parse4(row)).collectAsList()
  }

  def parse4(row: Row): Volo4 = {
    new Volo4(
      if(row.getAs(0) == null) Date.valueOf("1-1-1970")
      else Date.valueOf(row.getAs(0).toString),
      if(row.getAs(1) == null) ""
      else row.get(1).toString,
      if(row.getAs(2) == null) ""
      else row.get(2).toString,
      if(row.getAs(3) == null) ""
      else row.get(3).toString,
      if(row.getAs(4) == null) ""
      else row.get(4).toString,
      if(row.getAs(5) == null) ""
      else row.get(5).toString,
      if(row.getAs(6) == null) -1
      else row.get(6).toString.toDouble,
      if(row.getAs(7) == null) -1
      else row.get(7).toString.toInt,
      if(row.getAs(8) == null) -1
      else row.get(8).toString.toDouble,
      if(row.getAs(9) == null) false
      else if(row.get(9).toString.toDouble == 1.00)  true
      else false
    )
  }

  object Volo1Encoders {
    implicit def Volo1Encoder: org.apache.spark.sql.Encoder[Volo1] =
      org.apache.spark.sql.Encoders.kryo[Volo1]
  }

  object Volo4Encoders {
    implicit def Volo1Encoder: org.apache.spark.sql.Encoder[Volo4] =
      org.apache.spark.sql.Encoders.kryo[Volo4]
  }
}