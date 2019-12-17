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
//import java.util.ArrayList;
//import java.util.List;

//public class Category {
  // private int id;
//private String name;
 //private String image;

 //public Category(int id, String name, String image) {
  //this.id = id;
 //this.name = name;
 //this.image = image;
// }

 //public Category(int id, String name) {
       // this.id = id;
     //   this.name = name;
   //  this.image = "default";
 //   }

   // public static List<Category> getDefault(){
     //   List<Category> categories = new ArrayList<Category>();
   //     categories;
 //       categories.add(new Category(123,"Music", "My image"));
  //categories.add(new Category(124,"Not music", "My image"));
//        categories.add(new Category(125,"Games", "My image"));
//        categories.add(new Category(126,"History", "My image"));
//        categories.add(new Category(123,"Music", "My image"));
//        categories.add(new Category(124,"Not music", "My image"));
//        categories.add(new Category(125,"Games", "My image"));
//        categories.add(new Category(126,"History", "My image"));
//        categories.add(new Category(123,"Music", "My image"));
//        categories.add(new Category(124,"Not music", "My image"));
//        categories.add(new Category(125,"Games", "My image"));
//        categories.add(new Category(126,"History", "My image"));
//        categories.add(new Category(123,"Music", "My image"));
//        categories.add(new Category(124,"Not music", "My image"));
//        categories.add(new Category(125,"Games", "My image"));
//        categories.add(new Category(126,"History", "My image"));
//
//        return categories;
//    }
//}
