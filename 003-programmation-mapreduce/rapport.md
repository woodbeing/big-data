# Rapport de Programmation map/reduce

## envirenement windows

### I - Démarrer le Cluster Hadoop

#### 1 - Démarrage des containers

```text
> docker compose up -d
```

```text
[+] Running 3/3
 ✔ Container hadoop-slave1  Started            1.0s 
 ✔ Container hadoop-slave2  Started            1.1s 
 ✔ Container hadoop-master  Started            1.1s
 ```

#### 2 - Accéder au master

```text
> docker exec -it hadoop-master bash
```

```text
root@hadoop-master:~#
```

#### 3 - Démarrer hadoop et yarn

```text
root@hadoop-master:~# ./start-hadoop.sh
```

```text
Starting namenodes on [hadoop-master]
hadoop-master: Warning: Permanently added 'hadoop-master,172.20.0.4' (ECDSA) to the list of known hosts.
hadoop-master: WARNING: HADOOP_NAMENODE_OPTS has been replaced by HDFS_NAMENODE_OPTS. Using value of HADOOP_NAMENODE_OPTS.
Starting datanodes
WARNING: HADOOP_SECURE_DN_LOG_DIR has been replaced by HADOOP_SECURE_LOG_DIR. Using value of HADOOP_SECURE_DN_LOG_DIR.
hadoop-slave1: Warning: Permanently added 'hadoop-slave1,172.20.0.2' (ECDSA) to the list of known hosts.
hadoop-slave2: Warning: Permanently added 'hadoop-slave2,172.20.0.3' (ECDSA) to the list of known hosts.
hadoop-slave2: WARNING: HADOOP_SECURE_DN_LOG_DIR has been replaced by HADOOP_SECURE_LOG_DIR. Using value of HADOOP_SECURE_DN_LOG_DIR.
hadoop-slave2: WARNING: HADOOP_DATANODE_OPTS has been replaced by HDFS_DATANODE_OPTS. Using value of HADOOP_DATANODE_OPTS.
hadoop-slave1: WARNING: HADOOP_SECURE_DN_LOG_DIR has been replaced by HADOOP_SECURE_LOG_DIR. Using value of HADOOP_SECURE_DN_LOG_DIR.
hadoop-slave1: WARNING: HADOOP_DATANODE_OPTS has been replaced by HDFS_DATANODE_OPTS. Using value of HADOOP_DATANODE_OPTS.
Starting secondary namenodes [hadoop-master]
hadoop-master: Warning: Permanently added 'hadoop-master,172.20.0.4' (ECDSA) to the list of known hosts.
hadoop-master: WARNING: HADOOP_SECONDARYNAMENODE_OPTS has been replaced by HDFS_SECONDARYNAMENODE_OPTS. Using value of HADOOP_SECONDARYNAMENODE_OPTS.
Starting resourcemanager
Starting nodemanagers
hadoop-slave2: Warning: Permanently added 'hadoop-slave2,172.20.0.3' (ECDSA) to the list of known hosts.
hadoop-slave1: Warning: Permanently added 'hadoop-slave1,172.20.0.2' (ECDSA) to the list of known hosts.
```

```text
root@hadoop-master:~# jps
```

```text
913 ResourceManager
260 NameNode
1286 Jps
568 SecondaryNameNode
```

### II - Programmation avec l’api MapReduce

#### 1 - Installation de l’environnement de développement

`visual studio code` deja exist sur la machine.  

`ctrl + shift + P` pour lancer une nouveau projet `Java`

* [pom.xml](/003-programmation-mapreduce/lab3_mapreduce/pom.xml)

```xml
<properties>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.apache.hadoop</groupId>
            <artifactId>hadoop-hdfs</artifactId>
            <version>3.2.0</version>
        </dependency>
        <dependency>
            <groupId>org.apache.hadoop</groupId>
            <artifactId>hadoop-common</artifactId>
            <version>3.2.0</version>
        </dependency>
        <dependency>
            <groupId>org.apache.hadoop</groupId>
            <artifactId>hadoop-mapreduce-client-core</artifactId>
            <version>3.2.0</version>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <version>3.2.2</version>
                <configuration>
                    <archive>
                        <manifest>
                            <mainClass>edu.ismagi.hadoop.mapreduce.MainJob</mainClass>
                        </manifest>
                    </archive>
                    <finalName>mapreduce-app</finalName>
                </configuration>
            </plugin>
        </plugins>
    </build>
```

[lab3_mapreduce](/003-programmation-mapreduce/lab3_mapreduce)

#### [2 - class Mapper](/003-programmation-mapreduce/lab3_mapreduce/src/main/java/edu/ismagi/hadoop/mapreduce/TokenizerMapper.java)
  
#### [3 - Class Reducer](/003-programmation-mapreduce/lab3_mapreduce/src/main/java/edu/ismagi/hadoop/mapreduce/IntSumReducer.java)

#### [3 - Classe Principale](/003-programmation-mapreduce/lab3_mapreduce/src/main/java/edu/ismagi/hadoop/mapreduce/MainJob.java)

```text
root@hadoop-master:/shared_volume/lab3_mapreduce# mvn package
```

Notez que j'ai déplacé le fichier lab3_mapreduce dans le dossier shared_volume pour utiliser `mvn package` dans le conteneur Docker

```text
root@hadoop-master:~# hadoop jar /shared_volume/mapreduce-app.jar input/achat.txt input/result
```

```text
2026-01-14 03:17:35,501 INFO client.RMProxy: Connecting to ResourceManager at hadoop-master/172.20.0.4:8032
2026-01-14 03:17:35,597 INFO client.AHSProxy: Connecting to Application History server at localhost/127.0.0.1:10200
2026-01-14 03:17:35,696 WARN mapreduce.JobResourceUploader: Hadoop command-line option parsing not performed. Implement the Tool interface and execute your application with ToolRunner to remedy this.
2026-01-14 03:17:35,707 INFO mapreduce.JobResourceUploader: Disabling Erasure Coding for path: /tmp/hadoop-yarn/staging/root/.staging/job_1768270651742_0008
2026-01-14 03:17:35,851 INFO input.FileInputFormat: Total input files to process : 1
2026-01-14 03:17:35,906 INFO mapreduce.JobSubmitter: number of splits:1
2026-01-14 03:17:35,927 INFO Configuration.deprecation: yarn.resourcemanager.system-metrics-publisher.enabled is deprecated. Instead, use yarn.system-metrics-publisher.enabled
2026-01-14 03:17:35,991 INFO mapreduce.JobSubmitter: Submitting tokens for job: job_1768270651742_0008
2026-01-14 03:17:35,992 INFO mapreduce.JobSubmitter: Executing with tokens: []
2026-01-14 03:17:36,100 INFO conf.Configuration: resource-types.xml not found
2026-01-14 03:17:36,101 INFO resource.ResourceUtils: Unable to find 'resource-types.xml'.
2026-01-14 03:17:36,136 INFO impl.YarnClientImpl: Submitted application application_1768270651742_0008
2026-01-14 03:17:36,158 INFO mapreduce.Job: The url to track the job: http://hadoop-master:8088/proxy/application_1768270651742_0008/
2026-01-14 03:17:36,158 INFO mapreduce.Job: Running job: job_1768270651742_0008
2026-01-14 03:17:40,208 INFO mapreduce.Job: Job job_1768270651742_0008 running in uber mode : false
2026-01-14 03:17:40,209 INFO mapreduce.Job:  map 0% reduce 0%
2026-01-14 03:17:44,239 INFO mapreduce.Job:  map 100% reduce 0%
2026-01-14 03:17:48,263 INFO mapreduce.Job:  map 100% reduce 100%
2026-01-14 03:17:48,273 INFO mapreduce.Job: Job job_1768270651742_0008 completed successfully
2026-01-14 03:17:48,335 INFO mapreduce.Job: Counters: 54
        File System Counters
                FILE: Number of bytes read=1637
                FILE: Number of bytes written=446647
                FILE: Number of read operations=0
                FILE: Number of large read operations=0
                FILE: Number of write operations=0
                HDFS: Number of bytes read=2665
                HDFS: Number of bytes written=1142
                HDFS: Number of read operations=8
                HDFS: Number of large read operations=0
                HDFS: Number of write operations=2
                HDFS: Number of bytes read erasure-coded=0
        Job Counters
                Launched map tasks=1
                Launched reduce tasks=1
                Data-local map tasks=1
                Total time spent by all maps in occupied slots (ms)=1368
                Total time spent by all reduces in occupied slots (ms)=1365
                Total time spent by all map tasks (ms)=1368
                Total time spent by all reduce tasks (ms)=1365
                Total vcore-milliseconds taken by all map tasks=1368
                Total vcore-milliseconds taken by all reduce tasks=1365
                Total megabyte-milliseconds taken by all map tasks=1400832
                Total megabyte-milliseconds taken by all reduce tasks=1397760
        Map-Reduce Framework
                Map input records=50
                Map output records=333
                Map output bytes=3833
                Map output materialized bytes=1637
                Input split bytes=116
                Combine input records=333
                Combine output records=124
                Reduce input groups=124
                Reduce shuffle bytes=1637
                Reduce input records=124
                Reduce output records=124
                Spilled Records=248
                Shuffled Maps =1
                Failed Shuffles=0
                Merged Map outputs=1
                GC time elapsed (ms)=54
                CPU time spent (ms)=610
                Physical memory (bytes) snapshot=533749760
                Virtual memory (bytes) snapshot=5294747648
                Total committed heap usage (bytes)=469237760
                Peak Map Physical memory (bytes)=315355136
                Peak Map Virtual memory (bytes)=2646147072
                Peak Reduce Physical memory (bytes)=218394624
                Peak Reduce Virtual memory (bytes)=2648600576
        Shuffle Errors
                BAD_ID=0
                CONNECTION=0
                IO_ERROR=0
                WRONG_LENGTH=0
                WRONG_MAP=0
                WRONG_REDUCE=0
        File Input Format Counters
                Bytes Read=2549
        File Output Format Counters
                Bytes Written=1142
```

#### [4 - mapreduce python](/003-programmation-mapreduce/python/mapper.py)

```text
root@hadoop-master:~# cat /shared_volume/alice.txt | python /shared_volume/mapper.py
```

```text
.
.
.
dream           1
of              1
Wonderland              1
of              1
long            1
ago:            1
and             1
how             1
she             1
would           1
feel            1
with            1
all             1
their           1
simple          1
sorrows,                1
.
.
.
```

* Vérifier si le reducer fonctionne correctement

```text
root@hadoop-master:~# cat /shared_volume/alice.txt | python /shared_volume/mapper.py cat /shared_volume/alice.txt | python /shared_volume/mapper.py | sort -k1,1 | python /shared_volume/reducer.py
```

```text
.
.
.
yet--it's        1
yet.'    2
yet?'    2
you      260
you!     2
you!'    3
you'd    8
you'll   4
you're   15
you've   5
you,     25
you,'    6
you--all         1
you--are         1
you.     1
you.'    1
you:     1
you?     2
you?'    7
young    5
your     56
yours    1
.
.
.
```

* localiser le fichier JAR de l’utilitaire hadoop streaming.

```text
root@hadoop-master:~# find / -name 'hadoop-streaming*.jar'
```

```text
/usr/local/hadoop/share/hadoop/tools/sources/hadoop-streaming-3.2.0-test-sources.jar
/usr/local/hadoop/share/hadoop/tools/sources/hadoop-streaming-3.2.0-sources.jar
/usr/local/hadoop/share/hadoop/tools/lib/hadoop-streaming-3.2.0.jar
```

* exécuter le programme map/reduce avec la commande suivante

```text
root@hadoop-master:~# hadoop jar /usr/local/hadoop/share/hadoop/tools/lib/hadoop-streaming-3.2.0.jar -files /shared_volume/mapper.py,/shared_volume/reducer.py -mapper "python3 mapper.py" -reducer "python3 reducer.py" -input web_input/achat.txt -output web_input/result
```

```text
packageJobJar: [/tmp/hadoop-unjar2299773715949025263/] [] /tmp/streamjob4695627965853683692.jar tmpDir=null
2026-01-15 17:49:23,570 INFO client.RMProxy: Connecting to ResourceManager at hadoop-master/172.20.0.4:8032
2026-01-15 17:49:23,664 INFO client.AHSProxy: Connecting to Application History server at localhost/127.0.0.1:10200
2026-01-15 17:49:23,689 INFO client.RMProxy: Connecting to ResourceManager at hadoop-master/172.20.0.4:8032
2026-01-15 17:49:23,689 INFO client.AHSProxy: Connecting to Application History server at localhost/127.0.0.1:10200
2026-01-15 17:49:23,840 INFO mapreduce.JobResourceUploader: Disabling Erasure Coding for path: /tmp/hadoop-yarn/staging/root/.staging/job_1768270651742_0012
2026-01-15 17:49:24,156 INFO mapred.FileInputFormat: Total input files to process : 1
2026-01-15 17:49:24,217 INFO mapreduce.JobSubmitter: number of splits:2
2026-01-15 17:49:24,266 INFO Configuration.deprecation: yarn.resourcemanager.system-metrics-publisher.enabled is deprecated. Instead, use yarn.system-metrics-publisher.enabled
2026-01-15 17:49:24,350 INFO mapreduce.JobSubmitter: Submitting tokens for job: job_1768270651742_0012
2026-01-15 17:49:24,351 INFO mapreduce.JobSubmitter: Executing with tokens: []
2026-01-15 17:49:24,514 INFO conf.Configuration: resource-types.xml not found
2026-01-15 17:49:24,514 INFO resource.ResourceUtils: Unable to find 'resource-types.xml'.
2026-01-15 17:49:24,578 INFO impl.YarnClientImpl: Submitted application application_1768270651742_0012
2026-01-15 17:49:24,605 INFO mapreduce.Job: The url to track the job: http://hadoop-master:8088/proxy/application_1768270651742_0012/
2026-01-15 17:49:24,606 INFO mapreduce.Job: Running job: job_1768270651742_0012
2026-01-15 17:49:29,684 INFO mapreduce.Job: Job job_1768270651742_0012 running in uber mode : false
2026-01-15 17:49:29,685 INFO mapreduce.Job:  map 0% reduce 0%
2026-01-15 17:49:33,727 INFO mapreduce.Job:  map 100% reduce 0%
2026-01-15 17:49:38,759 INFO mapreduce.Job:  map 100% reduce 100%
2026-01-15 17:49:38,768 INFO mapreduce.Job: Job job_1768270651742_0012 completed successfully
2026-01-15 17:49:38,832 INFO mapreduce.Job: Counters: 54
        File System Counters
                FILE: Number of bytes read=3839
                FILE: Number of bytes written=683994
                FILE: Number of read operations=0
                FILE: Number of large read operations=0
                FILE: Number of write operations=0
                HDFS: Number of bytes read=4038
                HDFS: Number of bytes written=1388
                HDFS: Number of read operations=11
                HDFS: Number of large read operations=0
                HDFS: Number of write operations=2
                HDFS: Number of bytes read erasure-coded=0
        Job Counters
                Launched map tasks=2
                Launched reduce tasks=1
                Data-local map tasks=2
                Total time spent by all maps in occupied slots (ms)=4215
                Total time spent by all reduces in occupied slots (ms)=1775
                Total time spent by all map tasks (ms)=4215
                Total time spent by all reduce tasks (ms)=1775
                Total vcore-milliseconds taken by all map tasks=4215
                Total vcore-milliseconds taken by all reduce tasks=1775
                Total megabyte-milliseconds taken by all map tasks=4316160
                Total megabyte-milliseconds taken by all reduce tasks=1817600
        Map-Reduce Framework
                Map input records=50
                Map output records=333
                Map output bytes=3167
                Map output materialized bytes=3845
                Input split bytes=214
                Combine input records=0
                Combine output records=0
                Reduce input groups=124
                Reduce shuffle bytes=3845
                Reduce input records=333
                Reduce output records=124
                Spilled Records=666
                Shuffled Maps =2
                Failed Shuffles=0
                Merged Map outputs=2
                GC time elapsed (ms)=111
                CPU time spent (ms)=1260
                Physical memory (bytes) snapshot=861114368
                Virtual memory (bytes) snapshot=7938945024
                Total committed heap usage (bytes)=753926144
                Peak Map Physical memory (bytes)=320184320
                Peak Map Virtual memory (bytes)=2646732800
                Peak Reduce Physical memory (bytes)=224350208
                Peak Reduce Virtual memory (bytes)=2649006080
        Shuffle Errors
                BAD_ID=0
                CONNECTION=0
                IO_ERROR=0
                WRONG_LENGTH=0
                WRONG_MAP=0
                WRONG_REDUCE=0
        File Input Format Counters
                Bytes Read=3824
        File Output Format Counters
                Bytes Written=1388
2026-01-15 17:49:38,832 INFO streaming.StreamJob: Output directory: web_input/result
```

* verification

```text
root@hadoop-master:~# hdfs dfs -cat ./web_input/result/*
```

```text
09:00    22
09:01    22
09:02    6
108.69   1
117.81   1
135.96   1
136.9    1
15.41    1
153.57   1
16.78    1
170.2    1
182.05   1
189.01   1
2012-01-01       50
205.06   1
213.88   1
214.05   1
215.82   1
22.36    1
222.61   1
235.63   1
247.18   1
25.38    1
252.88   1
255.68   1
260.65   1
287.65   1
290.82   1
296.8    1
298.86   1
3.85     1
309.16   1
31.08    1
327.75   1
344.09   1
345.18   1
351.31   1
370.55   1
376.11   1
379.6    1
39.75    1
414.08   1
418.94   1
466.64   1
469.63   1
475.26   1
483.82   1
493.51   1
53.26    1
6.38     1
66.08    1
80.46    1
88.25    1
93.39    1
Amex     10
Anchorage        3
Aurora   1
Austin   3
Baby     4
Beach    1
Bernardino       1
Books    5
Boston   1
Buffalo          1
CDs      2
Cameras          4
Cash     10
Chandler         2
Chicago          1
Children's       2
Christi          1
Clothing         10
Computers        2
Consumer         4
Corpus   1
Crafts   2
DVDs     3
Diego    1
Discover         8
Electronics      4
Fort     3
Francisco        1
Fremont          1
Fresno   1
Games    2
Garden   2
Goods    3
Greensboro       1
Honolulu         1
Houston          1
Indianapolis     1
Jose     2
Las      2
Lincoln          1
Madison          1
MasterCard       9
Men's    4
Minneapolis      1
Music    3
New      1
Newark   1
Norfolk          1
Omaha    2
Pet      1
Philadelphia     1
Pittsburgh       2
Portland         1
Reno     2
Riverside        2
San      5
Spokane          2
Sporting         3
Stockton         1
Supplies         1
Toys     3
Tulsa    1
Vegas    2
Video    2
Virginia         1
Visa     13
Wayne    1
Women's          4
Worth    2
York    1
```

### III - Exercice Ouvert

**Problématique**: calculer le montant pour chaque mode de paiement

#### 1 - mapper.py

```python
import sys

for line in sys.stdin:
    line = line.strip()
    words = line.split()
    print(f"{words[-1]}:{words[-2]}")
```

#### 2 - reducer.py

```python
from operator import itemgetter
import sys

current_word = None
current_amount = 0
word = None

for line in sys.stdin:
    line = line.strip() 
    word, amount = line.split(':', 1)

    try:
        amount = float(amount)
    except ValueError:
        continue

    if current_word == word:
        current_amount += amount
    else:
        if current_word:
            print('%s\t%f' % (current_word, current_amount))
        current_amount = amount
        current_word = word

if current_word == word:
    print('%s\t%f' % (current_word, current_amount))
```

#### 3 - resultats

```text
root@hadoop-master:~# cat /shared_volume/achat.txt | python /shared_volume/mapper.py | sort -k1,1 | python /shared_volume/reducer.py
```

```text
Amex    2185.200000
Cash    2060.610000
Discover        1947.930000
MasterCard      2669.900000
Visa    2396.180000
```

#### 4 - test sur hdfs

```text
root@hadoop-master:~# hdfs dfs -mkdir lab3_problematic
root@hadoop-master:~# hdfs dfs -put /shared_volume/mapper.py lab3_problematic
root@hadoop-master:~# hdfs dfs -put /shared_volume/reducer.py lab3_problematic
root@hadoop-master:~# hdfs dfs -put /shared_volume/achat.txt lab3_problematic
```

```text
root@hadoop-master:~# hadoop jar /usr/local/hadoop/share/hadoop/tools/lib/hadoop-streaming-3.2.0.jar -files /shared_volume/mapper.py,/shared_volume/reducer.py -mapper "python3 mapper.py" -reducer "python3 reducer.py" -input lab3_problematic/achat.txt -output lab3_problematic/result
```

```text
packageJobJar: [/tmp/hadoop-unjar6841365878616845896/] [] /tmp/streamjob7479045029410276899.jar tmpDir=null
2026-01-16 04:59:39,127 INFO client.RMProxy: Connecting to ResourceManager at hadoop-master/172.20.0.4:8032
2026-01-16 04:59:39,202 INFO client.AHSProxy: Connecting to Application History server at localhost/127.0.0.1:10200
2026-01-16 04:59:39,219 INFO client.RMProxy: Connecting to ResourceManager at hadoop-master/172.20.0.4:8032
2026-01-16 04:59:39,220 INFO client.AHSProxy: Connecting to Application History server at localhost/127.0.0.1:10200
2026-01-16 04:59:39,320 INFO mapreduce.JobResourceUploader: Disabling Erasure Coding for path: /tmp/hadoop-yarn/staging/root/.staging/job_1768539327035_0002
2026-01-16 04:59:39,542 INFO mapred.FileInputFormat: Total input files to process : 1
2026-01-16 04:59:39,606 INFO mapreduce.JobSubmitter: number of splits:2
2026-01-16 04:59:39,632 INFO Configuration.deprecation: yarn.resourcemanager.system-metrics-publisher.enabled is deprecated. Instead, use yarn.system-metrics-publisher.enabled
2026-01-16 04:59:39,716 INFO mapreduce.JobSubmitter: Submitting tokens for job: job_1768539327035_0002
2026-01-16 04:59:39,717 INFO mapreduce.JobSubmitter: Executing with tokens: []
2026-01-16 04:59:39,832 INFO conf.Configuration: resource-types.xml not found
2026-01-16 04:59:39,832 INFO resource.ResourceUtils: Unable to find 'resource-types.xml'.
2026-01-16 04:59:40,165 INFO impl.YarnClientImpl: Submitted application application_1768539327035_0002
2026-01-16 04:59:40,189 INFO mapreduce.Job: The url to track the job: http://hadoop-master:8088/proxy/application_1768539327035_0002/
2026-01-16 04:59:40,190 INFO mapreduce.Job: Running job: job_1768539327035_0002
2026-01-16 04:59:45,242 INFO mapreduce.Job: Job job_1768539327035_0002 running in uber mode : false
2026-01-16 04:59:45,243 INFO mapreduce.Job:  map 0% reduce 0%
2026-01-16 04:59:49,278 INFO mapreduce.Job:  map 100% reduce 0%
2026-01-16 04:59:53,300 INFO mapreduce.Job:  map 100% reduce 100%
2026-01-16 04:59:53,310 INFO mapreduce.Job: Job job_1768539327035_0002 completed successfully
2026-01-16 04:59:53,360 INFO mapreduce.Job: Counters: 54
        File System Counters
                FILE: Number of bytes read=823
                FILE: Number of bytes written=678004
                FILE: Number of read operations=0
                FILE: Number of large read operations=0
                FILE: Number of write operations=0
                HDFS: Number of bytes read=4052
                HDFS: Number of bytes written=95
                HDFS: Number of read operations=11
                HDFS: Number of large read operations=0
                HDFS: Number of write operations=2
                HDFS: Number of bytes read erasure-coded=0
        Job Counters
                Launched map tasks=2
                Launched reduce tasks=1
                Data-local map tasks=2
                Total time spent by all maps in occupied slots (ms)=4107
                Total time spent by all reduces in occupied slots (ms)=1323
                Total time spent by all map tasks (ms)=4107
                Total time spent by all reduce tasks (ms)=1323
                Total vcore-milliseconds taken by all map tasks=4107
                Total vcore-milliseconds taken by all reduce tasks=1323
                Total megabyte-milliseconds taken by all map tasks=4205568
                Total megabyte-milliseconds taken by all reduce tasks=1354752
        Map-Reduce Framework
                Map input records=50
                Map output records=50
                Map output bytes=717
                Map output materialized bytes=829
                Input split bytes=228
                Combine input records=0
                Combine output records=0
                Reduce input groups=50
                Reduce shuffle bytes=829
                Reduce input records=50
                Reduce output records=5
                Spilled Records=100
                Shuffled Maps =2
                Failed Shuffles=0
                Merged Map outputs=2
                GC time elapsed (ms)=82
                CPU time spent (ms)=980
                Physical memory (bytes) snapshot=857522176
                Virtual memory (bytes) snapshot=7937835008
                Total committed heap usage (bytes)=756547584
                Peak Map Physical memory (bytes)=318550016
                Peak Map Virtual memory (bytes)=2644746240
                Peak Reduce Physical memory (bytes)=223985664
                Peak Reduce Virtual memory (bytes)=2650116096
        Shuffle Errors
                BAD_ID=0
                CONNECTION=0
                IO_ERROR=0
                WRONG_LENGTH=0
                WRONG_MAP=0
                WRONG_REDUCE=0
        File Input Format Counters
                Bytes Read=3824
        File Output Format Counters
                Bytes Written=95
2026-01-16 04:59:53,360 INFO streaming.StreamJob: Output directory: lab3_problematic/result
```

```text
root@hadoop-master:~# hdfs dfs -cat lab3_problematic/result/*
```

```text
Amex    2185.200000
Cash    2060.610000
Discover        1947.930000
MasterCard      2669.900000
Visa    2396.180000
```
