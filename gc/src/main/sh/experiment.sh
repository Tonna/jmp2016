    rm -r log
mkdir log

CLASSPATH=./target/classes
APP=com.yakovchuk.MainGC2

JAVA_7=/d/java/jdk1.7.0_40/bin
JAVA_8=/d/java/jdk1.8.0_11/bin

INTERVAL_SMALL=100
INTERVAL_MEDIUM=1000
INTERVAL_BIG=10000

THREADS_ONE=1
THREADS_FOUR=4
THREADS_HUNDRED=100
THREADS_THOUSAND=1000

GC_ALG_SERIAL=-XX:+UseSerialGC
GC_ALG_PARALLEL=-XX:+UseParallelGC
GC_ALG_PARALLEL_COPY=-XX:+UseParNewGC
GC_ALG_PARALLEL_SCAVENGE=-XX:+UseParallelOldGC
GC_ALG_CMS=-XX:+UseConcMarkSweepGC
GC_ALG_G1=-XX:+UseG1GC

HEAP_SMALL=-Xmx256m
HEAP_MEDIUM=-Xmx800m
HEAP_BIG=-Xmx2g

for java in $JAVA_7
do
    for interval in $INTERVAL_SMALL
    do
        for threads in $THREADS_THOUSAND
        do
            for gc_alg in $GC_ALG_SERIAL $GC_ALG_PARALLEL $GC_ALG_PARALLEL_COPY $GC_ALG_PARALLEL_SCAVENGE $GC_ALG_CMS $GC_ALG_G1
            do
                for heap in $HEAP_SMALL $HEAP_BIG
                do
                    ID=`date +%N | md5sum.exe | cut -b -10`
                    printf "ID $ID | $java/java $heap $gc_alg -XX:+PrintGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:log/$ID -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=log/$ID.hprof -cp $CLASSPATH $APP $interval $threads 10\n" >>log/experiment-name-log
                    $java/java $heap $gc_alg -XX:+PrintGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:log/$ID -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=log/$ID.hprof -cp $CLASSPATH $APP $interval $threads 10
                done;
            done;
        done;
    done;
done;