package cookbook.domain;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Category {
    SALTY("SALTY"), SWEET("SWEET"), SOUP("SOUP"), MAIN_COURSE("MAIN_COURSE"), DESSERT("DESERT");

    final private String name;

    private Category(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public static Map<Category, String> toMap(){
        return Stream.of(values()).collect(Collectors.toMap(c -> c, c -> c.name));
    }
}
