#!/bin/sh

case $1 in
sh) sh $2 -h;;
java) java $2 -h;;
*) printf "not expected \"$1\"\n"
esac
