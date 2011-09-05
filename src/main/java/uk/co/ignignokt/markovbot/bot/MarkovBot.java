package uk.co.ignignokt.markovbot.bot;

import java.io.IOException;

import org.jibble.pircbot.PircBot;

import uk.co.ignignokt.markov.Chain;
import uk.co.ignignokt.markovbot.Backup;

public class MarkovBot extends PircBot {

        Chain chain;
        Backup backup;

        public void setNick(String nick){
                this.setName(nick);
        }

        public MarkovBot(Chain chain, Backup backup) {
                this.chain = chain;
                this.backup = backup;
        }

        @Override
        public void onMessage(String channel, String sender, String login, String hostname, String message) {
                if (message.matches("(!line)|!line .*")) {
                        String x = chain.getSentence();
                        while (x.split(" ").length < 6) {
                                x = chain.getSentence();
                        }
                        sendMessage(channel, x);
                } else {
                        chain.addSentence(message);
                        try {
                                backup.addSentence(message);
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                }
        }
}
