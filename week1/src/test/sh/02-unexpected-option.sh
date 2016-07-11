#!/bin/sh

case $1 in
sh) sh $2 -x;;
java) java $2 -x;;
*) printf "not expected \"$1\"\n"
esac
