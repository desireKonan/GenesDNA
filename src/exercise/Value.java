package exercise;


import java.util.Arrays;
import java.util.stream.Collectors;

public class Value {
    private String str;

    public Value(String str) {
        this.str = str;
    }

    public Value() {
        this.str = "";
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }


    public boolean isNumber() {
        return this.str.matches("\\D+");
    }

    public boolean isString() {
        return this.str.matches("^[+-]?(\\d+([.,][\\d]*)?|[.][0-9]+)$");
    }

    public String toDouble() {
        return Arrays.stream(this.str.split("[,.]")).collect(Collectors.joining("."));
    }
}
