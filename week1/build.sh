rm -r ./target

mkdir ./target
mkdir ./target/classes

#javac -d ./target/classes -sourcepath ./src/main/java/com/yakovcha/** ./src/main/java/com/yakovchuk/Main.java
du -a | cut -f 2 | grep "/src/main/java.*java" | xargs javac -d ./target/classes
jar cfe ./target/todo-cli.jar com/yakovchuk/Main -C ./target/classes .
