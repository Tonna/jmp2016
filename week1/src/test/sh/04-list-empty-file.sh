#!/bin/sh

cat <<EOF >empty
EOF

case $1 in
sh) sh $2 -f empty;;
java) java $2 -f empty;;
*) printf "not expected \"$1\"\n"
esac
