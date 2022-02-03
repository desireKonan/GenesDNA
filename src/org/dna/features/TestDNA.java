package org.dna.features;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestDNA {

    static class Rectangle {
        private double largeur;
        private double hauteur;

        public Rectangle(double largeur, double hauteur) {
            this.hauteur = hauteur;
            this.largeur = largeur;
        }

        public Rectangle() {
            this(0.0, 0.0);
        }

        public void setLargeur(double largeur) {
            this.largeur = largeur;
        }

        public void setHauteur(double hauteur) {
            this.hauteur = hauteur;
        }

        public double getLargeur() {
            return largeur;
        }

        public double getHauteur() {
            return hauteur;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Rectangle rectangle = (Rectangle) o;
            return Double.compare(rectangle.largeur, largeur) == 0 && Double.compare(rectangle.hauteur, hauteur) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(largeur, hauteur);
        }

        @Override
        public String toString() {
            return "Rectangle {" + "largeur = " + largeur + ", hauteur = " + hauteur + "}";
        }
    }

    static abstract class A {
        protected int val;

        protected void setVal(int val) {
            this.val = val;
        }

        protected int getVal() {
            return val;
        }

        public A(int val) {
            this.val = val;
        }

        public abstract void call();

        final int max(){
            return 1;
        }

        @Override
        public String toString() {
            return this.getClass().getSimpleName() + "{" + "val=" + val + '}';
        }
    }

    static class B extends A {

        public B(int val) {
            super(val);
            System.out.println("Appel constructeur ! ");
        }

        @Override
        public void call() {

        }

    }

    static class C extends A {

        public C(int val) {
            super(val);
            System.out.println("Appel constructeur ! ");
        }

        @Override
        public void call() {

        }
    }

    public static void main(String[] args) {
        //Part3 pt = new Part3();
        //pt.testCountGenes();
        //String r = "abcdkfjsksioehgjfhsdjfhksdfhuwabcabcajfieowj";
        //findAbc(r);
        //Rectangle r1 = new Rectangle(3, 5);
        //Rectangle r2 = new Rectangle(3, 5);
        //System.out.println(r1);
        //System.out.println(r2);



        A c = new C(0);
        A b = new B(1);
        System.out.println(c.val);
        System.out.println(b.getVal());
        c.call();
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
