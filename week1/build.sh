rm -r ./target

mkdir ./target
mkdir ./target/classes

javac -d ./target/classes ./src/main/java/com/yakovchuk/App.java
jar cfe ./target/todo-cli.jar com/yakovchuk/App -C ./target/classes .