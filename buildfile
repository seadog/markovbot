repositories.remote << 'http://www.ibiblio.org/maven2/'

define 'MarkovBot' do
    project.version = '0.1.0'
    compile.with 'pircbot:pircbot:jar:1.5.0', 'Markov:Markov:jar:0.1.0'
    package(:jar).with :manifest => { 'Main-Class' => 'uk.co.ignignokt.markovbot.Main' }
end

