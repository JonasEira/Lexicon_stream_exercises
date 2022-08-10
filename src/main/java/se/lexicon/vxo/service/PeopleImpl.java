package se.lexicon.vxo.service;

import se.lexicon.vxo.data.JsonReader;
import se.lexicon.vxo.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PeopleImpl implements People {

    private static final PeopleImpl INSTANCE;

    static {
        INSTANCE = new PeopleImpl();
    }

    public static PeopleImpl getInstance(){
        return INSTANCE;
    }

    private List<Person> people;

    private PeopleImpl(){
        people = JsonReader.getInstance().read();
    }

    @Override
    public List<Person> getPeople(){
        return people;
    }


    public static List<Person> FilterFirstName(String firstName) {
        List<Person> contains = new ArrayList<>();
        for(Person p : PeopleImpl.getInstance().getPeople()){
            if(p.getFirstName().equalsIgnoreCase(firstName)){
                contains.add(p);
            }
        }
        return contains;
    }
}