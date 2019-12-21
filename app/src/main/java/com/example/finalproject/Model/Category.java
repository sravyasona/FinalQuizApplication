package com.example.finalproject.Model;


public enum Category{
    MUSIC(12),
    BOOKS(10),
    FILMS(11),
    TELEVISION(14),
    GAMES(15),
    MATHEMATICS(19),
    COMPUTER(18),
    GENERAL(9),
    SPORTS(21),
    ART(25),
    ANIMALS(27),
    POLITICS(24),
    ANIME(31);

    public int id;

    Category(int id) {
        this.id = id;
    }
}

