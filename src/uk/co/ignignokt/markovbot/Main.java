package uk.co.ignignokt.markovbot;

import java.io.FileInputStream;
import java.io.IOException;

import java.util.Properties;
import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.NickAlreadyInUseException;

import uk.co.ignignokt.markov.Markov;
import uk.co.ignignokt.markovbot.bot.MarkovBot;

public class Main {

        public Main() throws NickAlreadyInUseException, IOException, IrcException {
                Markov markov = new Markov();
                MarkovBot bot = new MarkovBot(markov, new Backup(markov));

                Properties config = new Properties();
                FileInputStream in = new FileInputStream("bot.properties");
                config.load(in);

                bot.setNick(config.getProperty("nick", "MarkovBot"));
                bot.connect(config.getProperty("server"));
                bot.joinChannel(config.getProperty("channel"));

                bot.joinChannel("#xelix");
        }

        public static void main(String[] args) throws NickAlreadyInUseException, IOException, IrcException  {
                new Main();
        }
}
