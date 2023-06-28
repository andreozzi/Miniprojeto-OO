package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;


public class Program {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Locale.setDefault(Locale.US);

        Admin admin = new Admin();
        admin.criarDados();
        admin.mostrarItinerarios();
        admin.mostrarPassagens();




       sc.close();
    }




}
