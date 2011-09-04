repositories.remote << 'http://www.ibiblio.org/maven2/'

define 'markovbot' do
    project.version = '0.1.0'
    project.group = 'uk.co.ignignokt'
    compile.with 'pircbot:pircbot:jar:1.5.0', 'uk.co.ignignokt:markov:jar:0.1.0'
    package(:jar).with :manifest => { 'Main-Class' => 'uk.co.ignignokt.markovbot.Main' }
end

