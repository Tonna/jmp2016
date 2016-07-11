#!/bin/sh

APP_SH=./src/main/sh/todo.sh
APP_JAVA=./target/todo-cli.jar

TMP=./target/test-tmp
mkdir $TMP
tf=$TMP/1 #test first
ts=$TMP/2 #test second

#01-print-help.sh

sh $APP_SH -h > $tf
java -jar $APP_JAVA -h > $ts

printf "test 1 - print help - diff "
diff $tf $ts | wc -l
rm $tf $ts

#02-unexpected-option.sh

sh $APP_SH -x > $tf
java -jar $APP_JAVA -x > $ts

printf "test 2 - unexpected option - diff "
diff $tf $ts | wc -l
rm $tf $ts




rm -r $TMP
