package com.csv;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;

public class CSVTest {

    public static void main(String[] args) {
        try {
            FileResource fr = new FileResource("resources/2014/weather-2014-03-30.csv");
            CSVParser parser = fr.getCSVParser();

            //double record = averageTemperatureWithHighHumidityInFile(parser, 80);
            double result = averageTemperatureWithHighHumidityInFile(parser, 80);
            System.out.println("Moyenne : " + result);
            //String record = fileWithColdestTemperature();
            //FileResource fr = new FileResource(record);
            //CSVParser parser = fr.getCSVParser();
            //CSVRecord result = coldestHourInFile(parser);
            //System.out.println("Le fichier ayant la température la plus froide : " + result.get("DateUTC") + "  " + result.get("Humidity"));
            //printAllTemp(record);
            //System.out.println("La température la plus froide est : " + result.get("TemperatureF"));
            //System.out.println("Average temperature in file is " + record.get("TemperatureF"));
            //String country = "Côte d'Ivoire";
            //listExportersTwoProducts(parser, "gold", "diamonds");
            //
            //System.out.println("Résultat : " + result.get("TimeEST"));
            //System.out.println(countryInfo(parser, "Nauru"));
            //System.out.println("Le nombre d'exportateurs de l'or sont : " + numberOfExporters(parser, "sugar"));

            //String fileWithColdestTemp = fileWithColdestTemperature();
            //System.out.println("Le jour le plus froid dans un fichier est : " + fileWithColdestTemp);
            //FileResource fr = new FileResource(fileWithColdestTemp);
            //CSVParser parser = fr.getCSVParser();
            //System.out.println("La température la plus basse est : " + coldestHourInFile(parser).get("TemperatureF"));
            //parser.close();
            //printAllTemp(fileWithColdestTemp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void tester() throws IOException {
        FileResource fr = new FileResource("resource/exportdata.csv");
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record : parser.getRecords()) {
            System.out.println(record.get("Country") + "  " + record.get("Exports") + "  " + record.get("Value (dollars)"));
        }
    }

    public static void printAllTemp(String file) throws IOException {
        FileResource fr = new FileResource(file);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record : parser.getRecords()) {
            System.out.println(record.get("DateUTC") + "  " + record.get("TemperatureF"));
        }
    }


    public static String countryInfo(CSVParser parser, String country) throws IOException {
        for (CSVRecord record : parser.getRecords()) {
            if (record.get("Country").contains(country))
                return record.get("Country") + ": " + record.get("Exports") + ": " + record.get("Value (dollars)");
        }
        return "NOT FOUND";
    }

    public static void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) throws IOException {
        for (CSVRecord record : parser.getRecords()) {
            if (record.get("Exports").contains(exportItem1) && record.get("Exports").contains(exportItem2))
                System.out.println(record.get("Country"));
        }
    }

    public static int numberOfExporters(CSVParser parser, String exportItem) throws IOException {
        var countExporters = 0;
        for (CSVRecord record : parser.getRecords()) {
            if (record.get("Exports").contains(exportItem))
                countExporters++;
        }
        return countExporters;
    }

    public static void bigExporters(CSVParser parser, String amount) throws IOException {
        int countExporters = 0;
        for (CSVRecord record : parser.getRecords()) {
            if (amount.length() < record.get("Value (dollars)").length())
                System.out.println(record.get("Country") + " " + record.get("Value (dollars)"));
        }
    }


    public static CSVRecord coldestHourInFile(CSVParser parser) throws IOException {
        return coldestValueInFile(parser, "TemperatureF");
    }

    private static CSVRecord coldestValueInFile(CSVParser parser, String header) throws IOException {
        CSVRecord coldest = null;
        for (CSVRecord record : parser.getRecords()) {
            coldest = minTempRecord(record, coldest, header);
        }
        return coldest;
    }


    public static String fileWithColdestTemperature() throws IOException {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord coldestDataSet = null;
        String filePath = "";
        for (File file : dr.selectedFiles()) {
            CSVRecord record = coldestHourInFile((new FileResource(file)).getCSVParser());
            coldestDataSet = minTempRecord(record, coldestDataSet, "TemperatureF");
            if(coldestDataSet != null) {
                filePath = file.getPath();
            }
        }
        return filePath;
    }

    public static CSVRecord lowestHumidityInManyFiles () throws IOException {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord coldestDataSet = null;
        for (File file : dr.selectedFiles()) {
            CSVRecord record = coldestValueInFile((new FileResource(file)).getCSVParser(), "Humidity");
            coldestDataSet = minTempRecord(record, coldestDataSet, "Humidity");
        }
        return coldestDataSet;
    }


    public static double averageTemperatureInFile(CSVParser parser) throws IOException {
        double sum = 0;
        int count = 0;
        for (CSVRecord record : parser.getRecords()) {
            sum += Double.parseDouble(record.get("TemperatureF"));
            count++;
        }
        return (float) sum / count;
    }

    public static double averageTemperatureWithHighHumidityInFile(CSVParser parser, double value) throws IOException {
        double sum = 0;
        int count = 0;
        for (CSVRecord record : parser.getRecords()) {
            double humidity = Double.valueOf(record.get("Humidity"));
            if(humidity >= value) {
                sum += Double.valueOf(record.get("TemperatureF"));
                count++;
            }
        }
        return (float) sum / count;
    }

    public static CSVRecord lowestHumidityInFile(CSVParser parser) throws IOException {
        CSVRecord coldest = null;
        for (CSVRecord record : parser.getRecords()) {
            coldest = minTempRecord(record, coldest, "Humidity");
        }
        return coldest;
    }




    private static CSVRecord minTempRecord(CSVRecord record, CSVRecord result, String headerName) {
        String currentValue = "";
        if(result == null) {
            return record;
        } else {
            double minimum = Double.parseDouble(result.get(headerName));
            currentValue = record.get(headerName).equals("N/A") ? "0" : record.get(headerName);
            System.out.println(currentValue);
            double current = Double.parseDouble(currentValue);
            if (minimum > current) {
                return record;
            }
        }
        return result;
    }



}
