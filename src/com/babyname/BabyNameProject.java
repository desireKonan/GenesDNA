package com.babyname;

import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;

public class BabyNameProject {

    static final String URL_BABY = "resources/usbabynames/";

    public static void main(String[] args) {
        try {
            testGetRank();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testTotalBirth() throws IOException {
        FileResource fr = new FileResource(URL_BABY + "smalltesting/yob2012short.csv");
        CSVParser parser = fr.getCSVParser();
        totalBirth(parser);
    }

    public static void testGetRank() throws IOException {
        int rank = getRank(2012, "Noah", "M");
        System.out.println("Le rang de Noah est : " + rank);
    }


    public static void totalBirth(CSVParser parser) throws IOException {
        int totalBirth = 0, girltotalBirth = 0, boyTotalBith = 0;
        for (CSVRecord record : parser.getRecords()) {
            totalBirth++;
            if (record.get(1).equals("M"))
                boyTotalBith++;
            else
                girltotalBirth++;
        }
        System.out.println("Le nombre total de naissance est : " + totalBirth);
        System.out.println("Le nombre total de naissance femme est : " + girltotalBirth);
        System.out.println("Le nombre total de naissance homme est : " + boyTotalBith);
    }


    public static int getRank(int year, String name, String gender) throws IOException {
        int rank = -1, count = 0;
        FileResource fr = new FileResource(URL_BABY + "smalltesting/yob" + year + "short.csv");
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record : parser.getRecords()) {
            if(record.get(1).equals(gender)) {
                count++;
                if (record.get(0).equals(name)) {
                    rank = count;
                    break;
                }
            }
        }
        return rank;
    }


}
