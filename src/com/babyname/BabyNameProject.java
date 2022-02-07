package com.babyname;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BabyNameProject {

    static final String URL_BABY = "resources/usbabynames/";

    public static void main(String[] args) {
        try {
            testGetTotalBirthsRankedHigher();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void testTotalBirth() throws IOException {
        FileResource fr = new FileResource(URL_BABY + "usbabynamesbyyear/yob1905.csv");
        CSVParser parser = fr.getCSVParser();
        totalBirth(parser);
    }

    public static void testGetRank() throws IOException {
        int rank = getRank(1971, "Frank", "M");
        System.out.println("Le rang de Noah est : " + rank);
    }

    public static void testGetName() throws IOException {
        String name = getName(1982, 450, "M");
        System.out.println("Le nom est du garçon de rang 4 : " + name);
    }

    public static void testWhatIsNameInYear() throws IOException {
        whatIsNameInYear("Owen", 1974, 2014, "M");
    }

    public static void testGetAverageRank() throws IOException {
        double averageRank = getAverageRank("Robert", "M");
        System.out.println("La moyenne est : " + averageRank);
    }

    public static void testGetTotalBirthsRankedHigher() throws IOException {
        int sumBirths = getTotalBirthsRankedHigher(1990, "Drew", "M");
        System.out.println("Le nombre de naissance est : " + sumBirths);
    }

    public static void testYearOfHighestRank() throws IOException {
        int year = yearOfHighestRank("Mich", "M");
        System.out.println("L'année est : " + year);
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
        FileResource fr = new FileResource(URL_BABY + "usbabynamesbyyear/yob" + year + ".csv");
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


    public static String getName(int year, int rank, String gender) throws IOException {
        int currentRank = 0;
        var nom = "NO NAME !";
        FileResource fr = new FileResource(URL_BABY + "usbabynamesbyyear/yob" + year + ".csv");
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record : parser.getRecords()) {
            if(record.get(1).equals(gender)) {
                currentRank++;
                if (currentRank == rank) {
                    nom = record.get(0);
                    break;
                }
            }
        }
        return nom;
    }


    public static void whatIsNameInYear(String name, int year, int newYear, String gender) throws IOException {
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        System.out.format("%s born %d in would be %s if she was born in %d", name, year, newName, newYear);
    }

    public static int yearOfHighestRank(String name, String gender) throws IOException {
        DirectoryResource dr = new DirectoryResource();
        int max = 0, rank = 0, year = -1;
        List<String> years = new ArrayList<>();
        for (File file : dr.selectedFiles()) {
            //Je réccupère les différentes dates de fichiers.
            years.add(
                    Arrays.stream(
                            file.getPath().split("[^0-9]+")
                    )
                            .filter(element -> element != "")
                            .collect(Collectors.joining())
            );
        }
        for (var i = 0; i < years.size(); i++) {
            if (i == 0) {
                max = getRank(Integer.valueOf(years.get(i)), name, gender);
            } else {
                rank = getRank(Integer.valueOf(years.get(i)), name, gender);
            }
            if(max > rank) {
                max = rank;
                year = Integer.valueOf(years.get(i));
            }
        }

        return year;
    }

    public static double getAverageRank(String name, String gender) throws IOException {
        DirectoryResource dr = new DirectoryResource();
        double sum = 0.0;
        List<String> years = new ArrayList<>();
        for (File file : dr.selectedFiles()) {
            //Je réccupère les différentes dates de fichiers.
            years.add(
                    Arrays.stream(
                                    file.getPath().split("[^0-9]+")
                            )
                            .filter(element -> element != "")
                            .collect(Collectors.joining())
            );
        }
        for (var i = 0; i < years.size(); i++) {
            sum += getRank(Integer.valueOf(years.get(i)), name, gender);
        }

        return Math.round((sum / years.size()) * 100.0) / 100.0;
    }


    public static int getTotalBirthsRankedHigher(int year, String name, String gender) throws IOException {
        FileResource fr = new FileResource(URL_BABY + "usbabynamesbyyear/yob" + year + ".csv");
        CSVParser parser = fr.getCSVParser();
        int rank = 0, sumBirths = 0;
        for (CSVRecord record : parser.getRecords()) {
            if(record.get(1).equals(gender)) {
                if (record.get(0).equals(name)) {
                    break;
                }
                rank++;
                sumBirths += Integer.valueOf(record.get(2));
            }
        }
        return sumBirths;
    }
}
