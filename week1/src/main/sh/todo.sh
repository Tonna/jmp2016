#!/bin/sh

OPTION=$1
FILE=$2
COMMAND=$3
INPUT=$4

print_help() {
  printf "expected input \"-[fh] [file name] [command] [all other]\"\n"
}

validate_remove_argument(){
  if [ `echo $@ | grep "^[1-9][0-9]*$"` = '' ]
    then printf "failure: input \"$@\" doesn't match number\n"; exit;
  fi
}

validate_input_non_empty(){
  if [ -z $INPUT ]
    then printf "failure: input is empty\n"; exit;
  fi
}

case "$OPTION" in
-h) print_help; exit;;
-f) ;;
*)  printf "failure: invalid input\n"; print_help; exit;;
esac

if test ! -f $FILE
then
  printf "failure: file \"$FILE\" does not exist\n"; exit
fi 

case "$COMMAND" in

list) awk '{ print NR " " $0 }' $FILE;;
add) validate_input_non_empty; echo "$*" | cut -d ' ' -f 4- >> $FILE;;
remove) validate_input_non_empty; validate_remove_argument $INPUT; sed -i "$INPUT"d $FILE;;
remove-all) printf "" > $FILE;;
*) printf "failure: unknown command \"$COMMAND\"\n"; exit;;
esac
