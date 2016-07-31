rm -r ./target
mkdir ./target
mkdir ./target/classes

du -a | cut -f 2 | grep "/src/main/java.*java" | xargs javac -d ./target/classes
#javac -cp . -d ./target ./src/main/java
