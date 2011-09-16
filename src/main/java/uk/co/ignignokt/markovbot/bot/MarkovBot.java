package uk.co.ignignokt.markovbot.bot;

import java.io.IOException;

import org.jibble.pircbot.PircBot;

import uk.co.ignignokt.markov.Chain;
import uk.co.ignignokt.markovbot.Backup;

public class MarkovBot extends PircBot {

        private Chain chain;
        private Backup backup;
        
        private static final long THROTTLETIME = 30000;
        
        private long throttle;

        public void setNick(String nick){
                this.setName(nick);
        }

        public MarkovBot(Chain chain, Backup backup) {
                this.chain = chain;
                this.backup = backup;
                this.throttle = System.currentTimeMillis();
                
                setVersion("Markov Chain Bot v0.1");
        }

        @Override
        public void onMessage(String channel, String sender, String login, String hostname, String message) {
                if (message.matches("(!line)|!line .*")) {
                        long time = System.currentTimeMillis();
                        if(time - this.throttle < THROTTLETIME){
                                sendMessage(sender, "Sorry, please wait " + (THROTTLETIME - (time - this.throttle))/1000 + "s before trying again");
                                return;
                        }

                        String x = chain.getSentence();
                        while (x.split(" ").length < 6) {
                                x = chain.getSentence();
                        }
                        sendMessage(channel, x);
                        this.throttle = System.currentTimeMillis();
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
