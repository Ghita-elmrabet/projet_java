package main.grid;

import static main.grid.Main.columns;
import static main.grid.Main.lines;
import static main.grid.Main.win;

public class Gagner {
    public int[][] gridStock = new int[lines][columns];
    public int[][] gridTempo = new int[lines][columns];
    public int playNum;

    public Gagner(){
        playNum = 0;
        for (int i=0;i<lines;i++){
            for (int j=0;j<columns;j++){
                gridStock[i][j]=0;
                gridTempo[i][j]=0;
            }
        }
    }

    //on travaille juste avec stock a bien remplir
    int getScore(int[] stock,int playNum){
        int score = 0;
        int sum = 0;
        int sumEmpty = 0;
        for (int k = 0; k < win; k++) {
            if (stock[k] == playNum) {
                sum += 1;
            }else if (stock[k] == 0) {
                sumEmpty += 1;
            }
        }

        if (sum == win) {// on gagnera si on prend ce choix
            score += 1000000;
        } else if (sum == win - 1 && sumEmpty == 1) {//il me reste 1 seule a remplir
            score += 50;
        } else if (sum == win - 2 && sumEmpty == 2) {//il me reste a remplir 2 autres
            score += 8;
        } else if (sum == 1 && sumEmpty == 3) {//j'ai 1 quelque part
            score += 1;
        }
        return score;
    }

    //donne un score precis selon combien de pions sont aligner dans la gridTemp
    int scorePosition(int[][] gridSetPostion,int playNum) {
        int score = 0;
        int[] stock = new int[win];

        //on fait le score de toute la grille temporaire
        //horizontale de gauche a droite
        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < columns - win + 1; j++) {
                for (int k = 0; k < win; k++) {
                    stock[k] = gridSetPostion[i][j + k];
                }
                score += getScore(stock, playNum);
            }
        }

        //verticale de haut en bas
        for (int j = 0; j < columns; j++) {
            for (int i = 0; i < lines - win + 1; i++) {
                for (int k = 0; k < win; k++) {
                    stock[k] = gridSetPostion[i + k][j];
                }
                score += getScore(stock, playNum);
            }
        }

        //diagonale1 de haut en bas et de gauche a droite
        for (int j = 0; j < columns - win + 1; j++) {
            for (int i = 0; i < lines - win + 1; i++) {
                for (int k = 0; k < win; k++) {
                    stock[k] = gridSetPostion[i + k][j  + k];
                }
                score += getScore(stock, playNum);
            }
        }

        //diagonale2 de bas en haut et de gauche a droite
        for (int j = 0; j < columns - win + 1; j++) {
            for (int i = lines - 1; i > win - 1; i--) {
                for (int k = 0; k < win; k++) {
                    stock[k] = gridSetPostion[i - k][j  + k];
                }
                score += getScore(stock, playNum);
            }
        }
        return score;
    }

    // retourne la ligne a remplir si on choisi la colone i
    int PositionLine(int[][] gridPositionLine,int i){
        for (int j = lines - 1; j >= 0; j--) {
            if (gridPositionLine[j][i] == 0) {
                return j;
            }
        }
        return -1;
    }

    // retourne un tableau qui contient la ligne a remplir si on choisi la colone i
    int[] getPlacesAvailabal(int[][] gridGetPlacesAvailabal){
        int[] valPlacesAvailabal = new int[columns];
        for (int i = 0; i < columns; i++) {
            valPlacesAvailabal[i] = PositionLine(gridGetPlacesAvailabal,i);
        }
        return valPlacesAvailabal;
    }

    //penser a ajouter le cas ou la ligne est paire ou impaire
    //trouver la bonne colone de jeux
    int bestChoice(int[][] gridGagner,int playNum){
        int bestColumn = columns/2;
        int best = 0;
        int score;
        int[] placesAvailabal = getPlacesAvailabal(gridGagner);
        for (int i = 0; i < columns; i++) {
            if(placesAvailabal[i] != -1){
                int[][] gridTempo = new int[lines][columns];
                System.arraycopy(gridGagner, 0, gridTempo, 0, gridGagner.length);
                gridTempo[placesAvailabal[i]][i] = playNum;
                score = scorePosition(gridTempo,playNum);
                if(score > best){
                    best = score;
                    bestColumn = i;
                }
                gridTempo[placesAvailabal[i]][i] = 0;
            }
        }
        // pour ne pas laisser l autre gagner
        playNum = (playNum%2) + 1;
        for (int i = 0; i < columns; i++) {
            if(placesAvailabal[i] != -1){
                int[][] gridTempo = new int[lines][columns];
                System.arraycopy(gridGagner, 0, gridTempo, 0, gridGagner.length);
                gridTempo[placesAvailabal[i]][i] = playNum;
                score = scorePosition(gridTempo,playNum);
                if(score > best){
                    best = score;
                    bestColumn = i;
                }
                gridTempo[placesAvailabal[i]][i] = 0;
            }
        }

        System.out.println("je joue la colone " + bestColumn);
        return bestColumn;
    }

}