package com.arek00.timezone.content;

/**
 * Created by Admin on 2015-01-18.
 */
public enum City {

    LONDON("Londyn", 0),
    WARSAW("Warszawa", 1),
    KIEV("Kijów", 2),
    MOSCOW("Moskwa", 3),
    DUBAI("Dubaj", 4),
    ASTANA("Astana", 6),
    BEIJING("Pekin", 8),
    TOKYO("Tokio", 9),
    SYDNEY("Sydney", 10),
    LOS_ANGELES("Los Angeles", -8),
    DENVER("Denver", -7),
    CHICAGO("Chicago", -6),
    NEW_YORK("Nowy Jork", -5),
    BUENOS_AIRES("Buenos Aires", -3);


    private String name;
    private int utcOffset;

    City(String name, int utcOffset) {
        this.name = name;
        this.utcOffset = utcOffset;
    }

    public String getName() {
        return this.name;
    }

    public int getUTCOffset() {
        return this.utcOffset;
    }

}
