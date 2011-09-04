package uk.co.ignignokt.markovbot;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import uk.co.ignignokt.markov.external.UnicodeReader;
import uk.co.ignignokt.markov.Markov;

public class Backup {

        FileWriter fw;
        Markov irc;

        public Backup(Markov irc) throws IOException {
                fw = new FileWriter("backup.txt", true);
                this.irc = irc;

                FileInputStream fis = new FileInputStream("backup.txt");
                UnicodeReader ur = new UnicodeReader(fis, "UTF-8");
                BufferedReader br = new BufferedReader(ur);

                String line;
                while ((line = br.readLine()) != null) {
                        irc.addSentence(line);
                }

                br.close();
        }

        public void addSentence(String sentence) throws IOException {
                fw.write(sentence + "\n");
                fw.flush();
        }
}