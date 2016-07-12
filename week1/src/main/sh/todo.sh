#!/bin/sh

OPTION=$1
FILE=$2
COMMAND=$3
INPUT=$4

print_help() {
  printf "expected input \"-[fh] [file name] [command] [command args]\"\n"
  printf "command list:\n  list\n  add [text]\n  remove [task number]\n  remove all\n"
}

print_invalid_input_message(){
  printf "failure: invalid input\n"; print_help; 
}

check_is_number(){
  if [ -z `echo $@ | grep "^[1-9][0-9]*$"` ]
    then printf "failure: input \"$@\" is not a number\n"; exit;
  fi
}

check_non_empty(){
  if [ -z $@ ]
    then print_invalid_input_message; exit;
  fi
}

case "$OPTION" in
-h) print_help; exit;;
-f) check_non_empty $FILE;;
*)  print_invalid_input_message; exit;
esac

if test ! -f $FILE
  then printf "failure: file \"$FILE\" does not exist\n"; exit
fi 

case "$COMMAND" in
list) awk '{ print NR " " $0 }' $FILE;;
add) check_non_empty $INPUT; echo "$*" | cut -d ' ' -f 4- >> $FILE;;
remove) check_non_empty $INPUT; check_is_number $INPUT; sed -i "$INPUT"d $FILE;;
remove-all) printf "" > $FILE;;
*) print_invalid_input_message; exit;; 
esac
