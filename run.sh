if [ ! -n "${M2_REPO}" ]; then
    echo "M2_REPO not set";
    exit;
fi

if [ "$M2_REPO" == "" ]; then
    echo "M2_REPO blank";
    exit;
fi

java -Xmn5M -Xms10M -Xmx10M -XX:MaxPermSize=16m -cp "$M2_REPO/pircbot/pircbot/1.5.0/pircbot-1.5.0.jar:$M2_REPO/Markov/Markov/0.1.0/Markov-0.1.0.jar:$M2_REPO/MarkovBot/MarkovBot/0.1.0/MarkovBot-0.1.0.jar" uk.co.ignignokt.markovbot.Main &
