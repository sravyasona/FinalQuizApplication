package com.example.finalproject.Model;


public enum Category {
    ANIMATON(31),
    GAMES(15),
    ART(25),
    MATHS(19),
    ANIMALS(27),
    COMPUTER(18),
    FILMS(11),
    BOOKS(10),
    GENERAL(9),
    POLITICS(24),
    SPORTS(21),
    TELEVISION(14),
    MUSIC(12);

    public int id;

    Category(int id) {
        this.id = id;
    }
}

