rm -r log
mkdir log

CLASSPATH=./target/classes
APP=com.yakovchuk.MainGC

JAVA_7=/d/java/jdk1.7.0_40/bin
JAVA_8=/d/java/jdk1.8.0_11/bin

LOAD_SMALL=100
LOAD_MEDIUM=10000
LOAD_BIG=1000000

INTERVAL_SMALL=100
INTERVAL_MEDIUM=1000
INTERVAL_BIG=10000

THREADS_ONE=1
THREADS_FOUR=4

GC_ALG_SERIAL=-XX:+UseSerialGC
GC_ALG_PARALLEL=-XX:+UseParallelGC
GC_ALG_PARALLEL_COPY=-XX:+UseParNewGC
GC_ALG_PARALLEL_SCAVENGE=-XX:+UseParallelOldGC
GC_ALG_CMS=-XX:+UseConcMarkSweepGC
GC_ALG_G1=-XX:+UseG1GC

for java in $JAVA_7 $JAVA_8
do
    for interval in $INTERVAL_SMALL $INTERVAL_MEDIUM $INTERVAL_BIG
    do
        for threads in $THREADS_ONE $THREADS_FOUR
        do
            for load in $LOAD_SMALL $LOAD_MEDIUM $LOAD_BIG
            do
                for gc_alg in $GC_ALG_SERIAL $GC_ALG_PARALLEL $GC_ALG_PARALLEL_COPY $GC_ALG_PARALLEL_SCAVENGE $GC_ALG_CMS $GC_ALG_G1
                do
                    ID=`date +%N | md5sum.exe | cut -b -6`
                    printf "ID $ID | $java/java $gc_alg -XX:+PrintGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:log/$ID -cp $CLASSPATH $APP $load $interval $threads 5\n" >>log/experiment-name-log
                    $java/java $gc_alg -XX:+PrintGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:log/$ID -cp $CLASSPATH $APP $load $interval $threads 1
                done;
            done;
        done;
    done;
done;
