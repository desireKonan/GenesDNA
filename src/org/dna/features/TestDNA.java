package org.dna.features;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestDNA {

    public static void main(String[] args) {
        //Part3 pt = new Part3();
        //pt.testCountGenes();
        String r = "ATCTGA";
        System.out.println(r.matches("[AGCT]+"));
    }
}
