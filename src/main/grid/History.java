package main.grid;

import java.io.FileWriter;
import java.io.IOException;

public class History {

    // création du fichier log.txt
    public History() {
        writeHist("",false);
    }

    public void writeHist(String text,boolean val){
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("/home/melmeklati/Puissance4/src/main/grid/log.txt",val);
            //inherited method from java.io.OutputStreamWriter
            fileWriter.write(text);
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void histPlayer(String text,int i){
        writeHist("Joueur " + i + " est" + text + " \n",true);
    }

    void round(){
        writeHist("Manche commence\n",true);
    }

    void histPlay(int play , int i){
        writeHist("Joueur " + i + " joue " + play + "\n", true);
    }

    void roundVict(int i){
        if(i == 0){
            writeHist("Egalité \n",true);
        }else{
            writeHist("joueur " + i + " gagne\n",true);
        }
    }

    void score(int val1,int val2){
        writeHist("Score " + val1 + " - " + val2 + "\n",true);
    }

    void end(){
        writeHist("Partie finie\n",true);
    }

    void errorPlayer(int numPlayer){ writeHist("Erreur saisie Joueur " + numPlayer + " \n", true);}

    void errorFull(int numColumn){ writeHist("Erreur colonne pleine " + numColumn + " \n", true);}

}
