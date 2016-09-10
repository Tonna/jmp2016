rm -r ./target
mkdir ./target
mkdir ./target/classes

du -a | cut -f 2 | grep "/src/main/java.*java" | xargs javac -cp "./lib/*" -d ./target/classes 
jar cfe ./target/spring-task.jar com/yakovchuk/SpringCoreMain -C ./target/classes . -C ./src/main/resources/ .

#libs used
# commons-logging-1.2.jar
# spring-beans-4.3.2.RELEASE.jar
# spring-context-4.3.2.RELEASE.jar
# spring-core-4.3.2.RELEASE.jar
# spring-expression-4.3.2.RELEASE.jar
