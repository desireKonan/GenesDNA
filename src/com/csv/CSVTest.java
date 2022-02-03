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
            //FileResource fr = new FileResource("resources/2012/weather-2012-01-01.csv");
            //CSVParser parser = fr.getCSVParser();
            //String country = "Côte d'Ivoire";
            //listExportersTwoProducts(parser, "gold", "diamonds");
            //CSVRecord result = coldestHourInFile(parser);
            //System.out.println("Résultat : " + result.get("TimeEST"));
            //System.out.println(countryInfo(parser, "Nauru"));
            //System.out.println("Le nombre d'exportateurs de l'or sont : " + numberOfExporters(parser, "sugar"));

            String fileWithColdestTemp = fileWithColdestTemperature();
            System.out.println("Le jour le plus froid dans un fichier est : " + fileWithColdestTemp);
            FileResource fr = new FileResource(fileWithColdestTemp);
            CSVParser parser = fr.getCSVParser();
            System.out.println("La température la plus basse est : " + coldestHourInFile(parser).get("TemperatureF"));
            parser.close();
            printAllTemp(fileWithColdestTemp);
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

    public static void bigExporters (CSVParser parser, String amount) throws IOException {
        int countExporters = 0;
        for (CSVRecord record : parser.getRecords()) {
            if (amount.length()< record.get("Value (dollars)").length())
                System.out.println(record.get("Country") + " " + record.get("Value (dollars)"));
        }
    }


    public static CSVRecord coldestHourInFile(CSVParser parser) throws IOException {
        CSVRecord coldest = null;
        for (CSVRecord record : parser.getRecords()) {
            if(coldest == null) {
                coldest = record;
            } else {
                double coldestHour = Double.parseDouble(coldest.get("TemperatureF"));
                double recordHour = Double.parseDouble(record.get("TemperatureF"));
                if (coldestHour > recordHour) {
                    coldest = record;
                }
            }
        }
        parser.close();
        return coldest;
    }


    public static String fileWithColdestTemperature() throws IOException {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord coldestDataSet = null;
        String filePath = "";
        for (File file : dr.selectedFiles()) {
            CSVRecord record = coldestHourInFile((new FileResource(file)).getCSVParser());
            if(coldestDataSet == null) {
                coldestDataSet = record;
                filePath = file.getPath();
            } else {
                double coldestHour = Double.parseDouble(coldestDataSet.get("TemperatureF"));
                double recordHour = Double.parseDouble(coldestDataSet.get("TemperatureF"));
                if (coldestHour > recordHour) {
                    coldestDataSet = record;
                    filePath = file.getPath();
                }
            }
        }
        return filePath;
    }
}
