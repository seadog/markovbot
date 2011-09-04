package uk.co.ignignokt.markovbot.bot;

import java.io.IOException;

import org.jibble.pircbot.PircBot;

import uk.co.ignignokt.markov.Markov;
import uk.co.ignignokt.markovbot.Backup;

public class MarkovBot extends PircBot {

        Markov markov;
        Backup backup;

        public void setNick(String nick){
                this.setName(nick);
        }

        public MarkovBot(Markov markov, Backup backup) {
                this.markov = markov;
                this.backup = backup;
        }

        @Override
        public void onMessage(String channel, String sender, String login, String hostname, String message) {
                if (message.matches("(!line)|!line .*")) {
                        String x = markov.getSentence();
                        while (x.split(" ").length < 6) {
                                x = markov.getSentence();
                        }
                        sendMessage(channel, x);
                } else {
                        markov.addSentence(message);
                        try {
                                backup.addSentence(message);
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                }
        }
}
