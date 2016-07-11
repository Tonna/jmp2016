#!/bin/sh

cat <<EOF >todo.txt
buy cat
buy milk for cat
EOF

case $1 in
sh) sh $2 -f todo.txt add buy bread;;
java) java $2 -f todo.txt add buy bread;;
*) printf "not expected \"$1\"\n"
esac
