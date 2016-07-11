#!/bin/sh

case $1 in
sh) sh $2 -f missing;;
java) java $2 -f missing;;
*) printf "not expected \"$1\"\n"
esac
