package ma.enset;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.List;

public class IncidentApp {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("IncidentApp").setMaster("local[*]");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> data = sc.textFile("incidents.csv");
        JavaRDD<String> lignes = data.filter(line -> !line.startsWith("Id"));

        JavaPairRDD<String, Integer> incidentsParService = lignes
                .mapToPair(line -> {
                    String[] fields = line.split(",");
                    String service = fields[3];
                    return new Tuple2<>(service, 1);
                })
                .reduceByKey(Integer::sum);
        System.out.println("📌 Nombre d'incidents par service :");
        incidentsParService.foreach(tuple ->
                System.out.println(tuple._1 + " : " + tuple._2)
        );
        JavaPairRDD<String, Integer> incidentsParAnnee = lignes
                .mapToPair(line -> {
                    String[] fields = line.split(",");
                    String date = fields[4];
                    String annee = date.split("-")[0];
                    return new Tuple2<>(annee, 1);
                })
                .reduceByKey(Integer::sum);
        List<Tuple2<String, Integer>> top2 = incidentsParAnnee
                .takeOrdered(2, (a, b) -> b._2.compareTo(a._2)); // tri décroissant

        System.out.println("\n📌 Les 2 années avec le plus d’incidents :");
        top2.forEach(tuple ->
                System.out.println(tuple._1 + " : " + tuple._2)
        );

        sc.close();

    }
}
