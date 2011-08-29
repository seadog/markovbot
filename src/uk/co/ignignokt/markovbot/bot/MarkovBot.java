package uk.co.ignignokt.markovbot.bot;


import java.io.IOException;

import org.jibble.pircbot.PircBot;

import uk.co.ignignokt.markov.irc.IrcMarkov;
import uk.co.ignignokt.markovbot.Backup;

public class MarkovBot extends PircBot {
	IrcMarkov markov;
	Backup backup;
	
	public MarkovBot(IrcMarkov markov, Backup backup){
        this.setName("MarkovBotTester");
		this.markov = markov;
		this.backup = backup;
	}
	
    public void onMessage(String channel, String sender, String login, String hostname, String message) {
    	if (message.matches("(!line)|!line .*")) {
    		String x = markov.getSentence();
    		while(x.split(" ").length < 6){ x = markov.getSentence(); }
    		sendMessage(channel, x);
    	} else {
    		markov.addParagraph(message);
    		try {
				backup.addParagraph(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    }
}
