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

printf "test 1 - empty input - diff \n"
diff -w $tf $ts
rm $tf $ts


#02-print-help.sh

sh $APP_SH -h > $tf
java -jar $APP_JAVA -h > $ts

printf "test 2 - print help - diff \n"
diff -w $tf $ts
rm $tf $ts

#03-unexpected-option.sh

sh $APP_SH -x > $tf
java -jar $APP_JAVA -x > $ts

printf "test 3 - unexpected option - diff \n"
diff -w $tf $ts
rm $tf $ts

#04-file-not-exits.sh

sh $APP_SH -f not-exist list > $tf
java -jar $APP_JAVA -f not-exist list > $ts

printf "test 4 - file not exist - diff \n"

diff -w $tf $ts
rm $tf $ts

#05-list-empty-file.sh

touch empty

sh $APP_SH -f empty > $tf
java -jar $APP_JAVA -f empty > $ts

printf "test 5 - list empty file - diff \n"
diff -w $tf $ts
rm $tf $ts
rm empty

#06-list-file.sh

cat <<EOF >todo
buy cat
buy milk for cat
EOF

sh $APP_SH -f todo list > $tf
java -jar $APP_JAVA -f todo list > $ts

printf "test 6 - list file - diff \n"
diff -w $tf $ts
rm $tf $ts
rm todo



#07-add-line.sh

cat <<EOF >todo
buy cat
buy milk for cat
EOF

cp todo todo1
cp todo todo2

sh $APP_SH -f todo1 add buy bread;
java -jar $APP_JAVA -f todo2 add buy bread;
printf "test 7 - add line - diff \n"
diff -w todo1 todo2
rm todo todo1 todo2

#08-remove-line.sh

cat <<EOF >todo
buy cat
buy milk for cat
EOF

cp todo todo1
cp todo todo2

sh $APP_SH -f todo1 remove 1;
java -jar $APP_JAVA -f todo2 remove 1;
printf "test 8 - remove line - diff \n"
diff -w todo1 todo2
rm todo todo1 todo2
 
#09-remove-all.sh

cat <<EOF >todo
buy cat
buy milk for cat
EOF

cp todo todo1
cp todo todo2

sh $APP_SH -f todo1 remove-all;
java -jar $APP_JAVA -f todo2 remove-all;
printf "test 9 - remove all - diff \n"
diff -w todo1 todo2
rm todo todo1 todo2

#10-unknown-command.sh

touch dummy

sh $APP_SH -f dummy unknown-command > $tf
java -jar $APP_JAVA -f dummy unknown-command > $ts
printf "test 10 - unknown command - diff \n"
diff -w $tf $ts
rm $tf $ts
rm dummy

#11-remove-incorrect-argument.sh

touch dummy

sh $APP_SH -f dummy remove 1,2 > $tf
java -jar $APP_JAVA -f dummy remove 1,2 > $ts
printf "test 11 - remove incorrect argument - diff \n"
diff -w $tf $ts
rm $tf $ts
rm dummy


rm -r $TMP
