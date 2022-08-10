package se.lexicon.vxo;


import se.lexicon.vxo.model.Person;
import se.lexicon.vxo.service.People;
import se.lexicon.vxo.service.PeopleImpl;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public class App 
{
    public static void main( String[] args ) {
        People ppl = PeopleImpl.getInstance();
        Collection<Person> people = ppl.getPeople();
        Function<String,List<Person>> filter = PeopleImpl::FilterFirstName;
        Collection<Person> eriks = filter.apply("Erik");
        for(Person p : eriks){
            System.out.println(p);
        }
    }
}
