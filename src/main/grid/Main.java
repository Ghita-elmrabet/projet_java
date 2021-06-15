package main.grid;

public class Main {
    // modifiables
    //number of parts
    public static int parts = 3;
    //number of columns
    public static int columns = 7;
    // number of lines
    public static int lines = 6;
    // nombre de jetons a aligner
    public static int win = 4;

    //number of joueurs
    public static int numberPlayers = 2;




    public static void main(String[] args) {
        int play1;
        int line1;
        int i = 0;
        int end = 0;
        History h = new History();
        Player p1 = new Player("random");
        String v1 = p1.getPlayers(1, h);

        Player p2 = new Player(v1);
        String v2 = p2.getPlayers(2, h);

        h.round();
        Case c = new Case();
        Play play = new Play();
        int partsPlayed = 1;
        int endParts = 0;
        int nombreWinPlayer1 = 0;
        int nombreWinPlayer2 = 0;
        while (endParts == 0) {
            if (end != 0){i=(partsPlayed+1)%2;} // altérner le débutant de chaque round
            for (; i < numberPlayers; i++) {
                if (i == 0) {
                    play1 = c.playGrid(p1, i,h);
                } else {
                    play1 = c.playGrid(p2, i,h);
                }
                h.histPlay(play1,i+1);
                line1 = c.stock(play1, i + 1);
                play.display(c);
                end = play.endGame(c,line1,play1, i + 1);
                if(end != 0){
                    if(end == 1){
                        if(i == 0){// incrémenter le nombre de victoire du gagnant
                            nombreWinPlayer1++;
                        }else if(i == 1){
                            nombreWinPlayer2++;
                        }
                        h.roundVict(i+1);
                    }else{
                        h.roundVict(0);
                    }
                    h.score(nombreWinPlayer1,nombreWinPlayer2);
                    if(partsPlayed != parts){
                        System.out.println(" partie " + partsPlayed + " terminée " + parts);
                        partsPlayed++;
                        c = new Case();
                    }else {
                        int winner;
                        if(nombreWinPlayer1 < nombreWinPlayer2){winner = 2;}else if(nombreWinPlayer1 > nombreWinPlayer2){winner = 1;}else{winner = 0;}// stocké ne numéro du gagnant dans winner
                        if(winner == 0){// aucun n'a gagné
                            System.out.println("la partie est términée et il y a égalitée entre les joueurs avec un score de player1 " + nombreWinPlayer1 + " : " + nombreWinPlayer2 + " player2");
                        }else {
                            System.out.println("la partie est términée et le joueur " + winner + " a gagné avec un score de player1 " + nombreWinPlayer1 + " : " + nombreWinPlayer2 + " player2");
                        }
                        h.end();
                        endParts = 1;
                        break;
                    }
                }
            }
            i = 0;
        }

    }

}
