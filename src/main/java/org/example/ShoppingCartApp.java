package org.example;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ShoppingCartApp {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        Locale enUS = new Locale("en", "US");
        Locale fiFI = new Locale("fi", "FI");
        Locale jaJP = new Locale("ja", "JP");
        Locale svSE = new Locale("sv", "SE");

        ResourceBundle rbEn = ResourceBundle.getBundle("ShoppingCartBundle", enUS);
        ResourceBundle rbFi = ResourceBundle.getBundle("ShoppingCartBundle", fiFI);
        ResourceBundle rbJa = ResourceBundle.getBundle("ShoppingCartBundle", jaJP);
        ResourceBundle rbSv = ResourceBundle.getBundle("ShoppingCartBundle", svSE);

        System.out.println(rbEn.getString("langselector"));
        System.out.println(rbFi.getString("langselector"));
        System.out.println(rbJa.getString("langselector"));
        System.out.println(rbSv.getString("langselector"));

        Locale locale;
        int lang;

        try {
            lang = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println(rbEn.getString("errorlang"));
            return;
        }

        switch (lang) {
            case 1 -> locale = enUS;
            case 2 -> locale = fiFI;
            case 3 -> locale = jaJP;
            case 4 -> locale = svSE;
            default -> {
                System.out.println(rbEn.getString("errorlang"));
                return;
            }
        }

        ResourceBundle rb = ResourceBundle.getBundle("ShoppingCartBundle", locale);

        System.out.println(rb.getString("itemcount"));
        int itemCount;
        try {
            itemCount = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println(rb.getString("invalidnumber"));
            return;
        }

        for (int i = 1; i <= itemCount; i++) {
            System.out.println(rb.getString("itemlabel") + " " + i);

            System.out.print(rb.getString("name"));
            String name = scanner.nextLine().trim();

            System.out.print(rb.getString("price"));
            double price;
            try {
                price = Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(rb.getString("invalidnumber"));
                return;
            }

            System.out.print(rb.getString("quantity"));
            int quantity;
            try {
                quantity = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(rb.getString("invalidnumber"));
                return;
            }

            cart.addToCart(new Item(name, price, quantity));
        }

        System.out.println(rb.getString("total") + " " + cart.totalValue());
    }
}