#!/bin/sh

APP_SH=A
APP_JAVA=B

#01-print-help.sh

sh $APP_SH -h > 1
java $APP_JAVA -h > 2

printf "diff "
diff 1 2 | wc -l
rm 1 2
