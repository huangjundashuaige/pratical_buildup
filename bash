bugrunner

cd ~/Desktop/GridWorldCode/projects/firstProjects
javac -classpath .:./../../gridworld.jar BugRunner.java
java -classpath .:./../../gridworld.jar BugRunner 

sonar runner

cd $SONAR_HOME
./sonar.sh start
in which place have property sonar-runner (after sonar started)

ant only actived in ./ unless you add path=" " in the pathelement

java org.junit.runner.JUnitCore xx
export JAVA_HOME=/usr/lib/jvm/jdk1.8.0_91
export ANT_HOME=/usr/bin/ant
export PATH=$PATH:$ANT_HOME/bin
export JUNIT_HOME=/usr/bin/junit
export PATH=$PATH:$JUNIT_HOME/bin
export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar:/opt/resources/junit-4.9.jar:$CLASSPATH