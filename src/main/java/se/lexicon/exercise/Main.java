package se.lexicon.exercise;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.PriorityQueue;

public class Main {


    public static void main(String[] args) {
        List<Product> kakor = new ArrayList<>();
        kakor.add(new Product("Kaka1", 100.0, 1000));
        kakor.add(new Product("Kaka2", 10.0, 500));
        kakor.add(new Product("Kaka3", 150.0, 750));
        kakor.add(new Product("Kaka4", 1000.0, 0));
        kakor.add(new Product("Kaka5", 20.0, 10000));
        System.out.println("Products that are out of stock:");
        findAndFilter(kakor, p -> p.getStock() == 0, p -> System.out.println(p));

        System.out.println("Products that are in price range 100.0 - 150.0 kr :");
        findAndFilter(kakor, p -> p.getPrice() >= 100.0 && p.getPrice() <= 150.0, (Product p) -> System.out.println(p));

        System.out.println("\nPrices before: ");
        for (Product p : kakor) {
            System.out.println(p.getProductName() + ": " + p.getPrice());
        }
        findAndFilter(kakor, p -> p.getPrice() <= 50.0, p -> p.setPrice(p.getPrice() * 1.50d));
        System.out.println("\nPrices after: ");
        for (Product p : kakor) {
            System.out.println(p.getProductName() + ": " + p.getPrice());
        }

    }

    public static <F> void findAndFilter(Collection<F> things, Conditional<F> cond, Action<F> ac) {
        for (F thing : things) {
            if (cond.test(thing)) {
                ac.execute(thing);
            }
        }
    }


}
