package main.grid;

import static main.grid.Main.columns;
import static main.grid.Main.lines;
import static main.grid.Main.win;

public class Play {


    Play(){

    }

    private static int test(Case c,int line,int column, int PLAYER, int i, int j){
        int x = line;int y = column;int sum = 0;
        while(x >= 0 && x < lines && y < columns && y >= 0 && c.grid[x][y] == PLAYER){x = x-i;y = y-j;sum++;}
        x =line ; y = column;
        while(x >= 0 && x < lines && y < columns && y >= 0 && c.grid[x][y] == PLAYER){x = x+i;y = y+j;sum++;}
        return sum;
    }

   int endGame(Case c,int line,int column, int PLAYER){
    int tst;
    // diagonale 1
        tst = test (c,line,column,PLAYER,1,1);
        if(tst == win+1){System.out.print("player " + PLAYER + " a gagné.");return 1 ;}
    // diagonale 2
        tst = test (c,line,column,PLAYER,1,-1);
        if(tst == win+1){System.out.print("player " + PLAYER + " a gagné.");return 1 ;}
     // verticale
        tst = test (c,line,column,PLAYER,1,0);
        if(tst == win+1){System.out.print("player " + PLAYER + " a gagné.");return 1 ;}
     // horizontale
        tst = test (c,line,column,PLAYER,0,1);
        if(tst == win+1){System.out.print("player " + PLAYER + " a gagné.");return 1 ;}

    //parite null
      int y;
    if(line == 0) {
      y = 0;
      while (y < columns) {
        if (c.grid[line][y] == 0) {
          return 0;
        }
        y++;
      }
      System.out.print("Partie null\n");
      return 2;
    }
    return 0;
  }

    public void display(Case c){
        for (int k=1;k<=c.grid[0].length;k++){
          System.out.print(k+" ");
        }
        System.out.println();
        for (int i=0;i<c.grid.length;i++){
          for (int j=0;j<c.grid[0].length;j++){
            if (c.grid[i][j]== Case.EMPTY){
              System.out.print(".");
            }
            if (c.grid[i][j]== Case.PLAYER1){
              System.out.print("X");
            }
            if (c.grid[i][j]== Case.PLAYER2){
              System.out.print("O");
            }
            System.out.print(" ");
          }
          System.out.println();
        }
        System.out.println();
    }

}
