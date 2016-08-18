rm -r ./target
mkdir ./target
mkdir ./target/classes

du -a | cut -f 2 | grep "/src/main/java.*java" | xargs javac -d ./target/classes
jar cf ./target/entity.jar -C ./target/classes .
