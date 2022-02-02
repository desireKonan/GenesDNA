package org.dna.features;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestDNA {

    public static void main(String[] args) {
        //Part3 pt = new Part3();
        //pt.testCountGenes();
        String r = "abcdkfjsksioehgjfhsdjfhksdfhuwabcabcajfieowj";
        findAbc(r);
    }

    public static void findAbc(String input) {
        int index = input.indexOf("abc");
        while (true){
            if (index == -1 || index >= input.length() - 3){
                break;
            }
            String found = input.substring(index+1, index+4);
            System.out.println(found);
            index = input.indexOf("abc",index+4);
        }
    }
}
