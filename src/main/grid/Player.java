package main.grid;

import java.util.Scanner;

public class Player {
    public String[] playersName = new String[2];
    public String[] playersType = new String[2];

    public Player(String text) {
        this.playersName[0] = text;
        this.playersName[1] = text;
        this.playersType[0] = text;
        this.playersType[1] = text;
    }

    public String getPlayers(int x,History h) {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        boolean nameUsed = true;
        while (nameUsed) {
            System.out.println("Joueur " + x + "?");

            String Player = myObj.nextLine();  // Read user input
            String[] wordsPlayer = Player.split(" ");
            if (wordsPlayer.length == 2) {
                if (wordsPlayer[0].equals("human") || wordsPlayer[0].equals("ia:random") || wordsPlayer[0].equals("ia:monkey") || wordsPlayer[0].equals("ia:gagner")) {
                    playersType[x - 1] = wordsPlayer[0];
                    playersName[x - 1] = wordsPlayer[1];
                    int var = x-1;
                    nameUsed = false;
                    while((var--)!=0) {
                        if (playersName[x - 1].equals(playersName[var])) {
                            System.out.println("Another user has this name");
                            nameUsed = true;
                        }
                    }
                    if(!nameUsed) {
                        h.histPlayer(" " + playersType[x - 1] + " " + playersName[x - 1], x);
                        return wordsPlayer[1];
                    }
                } else{

                    System.out.println("sorry! the first argument should be ia:monkey , ia:random , ia:gagner or human ");
                }
            }
            else {
                System.out.println("The number of arguments is different from two");
            }
            h.errorPlayer(x);
        }
        return null;
    }

}