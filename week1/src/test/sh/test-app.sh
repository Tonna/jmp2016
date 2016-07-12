#!/bin/sh

APP_SH=./src/main/sh/todo.sh
APP_JAVA=./target/todo-cli.jar

TMP=./target/test-tmp
mkdir $TMP
tf=$TMP/1 #test first
ts=$TMP/2 #test second

#01-empty-input.sh

sh $APP_SH > $tf
java -jar $APP_JAVA > $ts

printf "test 1 - empty input - diff "
diff $tf $ts | wc -l
rm $tf $ts


#02-print-help.sh

sh $APP_SH -h > $tf
java -jar $APP_JAVA -h > $ts

printf "test 2 - print help - diff "
diff $tf $ts | wc -l
rm $tf $ts

#03-unexpected-option.sh

sh $APP_SH -x > $tf
java -jar $APP_JAVA -x > $ts

printf "test 3 - unexpected option - diff "
diff $tf $ts | wc -l
rm $tf $ts

#04-file-not-exits.sh

sh $APP_SH -f not-exist > $tf
java -jar $APP_JAVA -f not-exist > $ts

printf "test 4 - file not exist - diff "

diff $tf $ts | wc -l
rm $tf $ts

#05-list-empty-file.sh

touch empty

sh $APP_SH -f empty > $tf
java -jar $APP_JAVA -f empty > $ts

printf "test 5 - list empty file - diff "
diff $tf $ts | wc -l
rm $tf $ts
rm empty

#06-add-line.sh

cat <<EOF >todo
buy cat
buy milk for cat
EOF

cp todo todo1
cp todo todo2

sh $APP_SH -f todo1 add buy bread;
java -jar $APP_JAVA -f todo2 add buy bread;
printf "test 6 - add line - diff "
diff todo1 todo2
rm todo todo1 todo2

rm -r $TMP
