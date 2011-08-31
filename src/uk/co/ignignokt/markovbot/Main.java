package uk.co.ignignokt.markovbot;

import java.io.IOException;

import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.NickAlreadyInUseException;

import uk.co.ignignokt.markov.Markov;
import uk.co.ignignokt.markovbot.bot.MarkovBot;

public class Main {
	public static void main(String[] args) throws NickAlreadyInUseException, IOException, IrcException{
		Markov markov = new Markov();
		MarkovBot bot = new MarkovBot(markov, new Backup(markov));
		
		bot.connect("irc.xelix.net");

        bot.joinChannel("#xelix");
	}
}
