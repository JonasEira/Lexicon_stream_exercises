package se.lexicon.exercise;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main {



    public static void main(String[] args) {
        List<Product> kakor = new ArrayList<>();
        kakor.add(new Product("Kaka1", 100.0, 1000));
        kakor.add(new Product("Kaka2", 10.0, 500));
        kakor.add(new Product("Kaka3", 150.0, 750));
        kakor.add(new Product("Kaka4", 1000.0, 0));
        kakor.add(new Product("Kaka5", 20.0, 10000));
        System.out.println("Products that are out of stock:");
        findAndFilter(kakor, new Conditional<Product>() {
            @Override
            public boolean test(Product p) {
                if(p.getStock() == 0){
                    return true;
                } else {
                    return false;
                }
            }
        }, new Action<Product>() {
            @Override
            public void execute(Product p) {
                System.out.println(p);
            }
        });

        System.out.println("Products that are in price range 100.0 - 150.0 kr :");
        findAndFilter(kakor, new Conditional<Product>() {
            @Override
            public boolean test(Product p) {
                if (p.getPrice() >= 100.0 && p.getPrice() <= 150.0) {
                    return true;
                } else {
                    return false;
                }
            }
        }, new Action<Product>() {
            @Override
            public void execute(Product p) {
                System.out.println(p);
            }
        });


    }

    public static <F> void findAndFilter(Collection<F> things, Conditional<F> cond, Action<F> ac){
        for(F thing : things){
            if(cond.test(thing)){
                ac.execute(thing);
            }
        }
    }


}
