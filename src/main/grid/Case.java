package main.grid;

import java.util.Scanner;

import static main.grid.Main.columns;
import static main.grid.Main.lines;

public class Case{
    public static final int EMPTY = 0;
    public static final int PLAYER1 = 1;
    public static final int PLAYER2 = 2;

    private static int last_case = 0;

    public int[][] grid;

    public Case(){
        this.grid = new int[lines][columns];
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                grid[i][j]=EMPTY;
            }
        }
    }

    private int returnToDomain(int x){
        if (x < 1)
            return 0;
        if (x > columns)
            return columns - 1;
        return x - 1;
    }


    public int playGrid(Player p, int indix, History h) {
        int play1;
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        if (p.playersType[indix].equals("ia:random")) {
            double number = Math.floor(Math.random() * columns) + 1;
            play1 = (int) number;
            play1 = returnToDomain(play1);
            while (grid[0][play1] != EMPTY) {
                number = Math.floor(Math.random() * columns) + 1;
                play1 = (int) number;
                play1 = returnToDomain(play1);
            }
        }
        else if (p.playersType[indix].equals("ia:monkey")) {
            //on vise autour de last_case
            int min = last_case - 1;
            min = returnToDomain(min);

            int max = last_case + 1;
            max = returnToDomain(max);

            last_case = returnToDomain(last_case);

            if (grid[0][last_case] != EMPTY && grid[0][min] != EMPTY && grid[0][max] != EMPTY){
                double number = Math.floor(Math.random() * columns) + 1;
                play1 = (int) number;
                play1 = returnToDomain(play1);
                while (grid[0][play1] != EMPTY) {
                    number = Math.floor(Math.random() * columns) + 1;
                    play1 = (int) number;
                    play1 = returnToDomain(play1);
                }
            }
            else {
                double number = Math.floor(Math.random() * (max - min + 1)) + min;
                play1 = (int) number;
                while (grid[0][play1] != EMPTY) {
                    number = Math.floor(Math.random() * (max - min + 1)) + min;
                    play1 = (int) number;
                }
            }
        }
        else if (p.playersType[indix].equals("ia:gagner")) {
            Gagner G = new Gagner();
            play1 = G.bestChoice(grid,indix +1);
        } else {
            while (true) {
                try {
                    System.out.println("Choose an number between 1 and " + columns);
                    String number = myObj.nextLine();  // Read user input
                    String[] wordsNumber = number.split(" ");
                    if (wordsNumber.length == 1) {
                        play1 = Integer.parseInt(number); // Integer.valueOf(number)
                        play1 = returnToDomain(play1);
                        if (grid[0][play1] != EMPTY) {
                            System.out.println("cette colone est déjà pleine");
                            h.errorFull(play1);
                        } else {
                            return play1;
                        }
                    }
                }catch(Exception e){System.out.println("Oups! it's not a number");}

            }
        }
        return play1;
    }

    public int stock(int play1,int player) {
        last_case = play1 + 1;
        int i = lines-1;
        for (; i >= 0; i--){
            if (grid[i][play1] == 0) {
            grid[i][play1] = player;
            return i;
          }
        }
        return i;
    }
}
