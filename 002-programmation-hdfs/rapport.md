# Rapport de Programmation HDFS

## envirenement windows

### I - Démarrer le Cluster Hadoop

#### 1 - Démarrage des containers

```text
> docker compose up -d

## output
[+] Running 3/3
 ✔ Container hadoop-slave1  Started            1.0s 
 ✔ Container hadoop-slave2  Started            1.1s 
 ✔ Container hadoop-master  Started            1.1s
```

#### 2 - Accéder au master

```text
> docker exec -it hadoop-master bash

## output
root@hadoop-master:~#
```

#### 3 - Démarrer hadoop et yarn

```text
root@hadoop-master:~# ./start-hadoop.sh

## output
Starting namenodes on [hadoop-master]
hadoop-master: Warning: Permanently added 'hadoop-master,172.20.0.4' (ECDSA) to the list of known hosts.
hadoop-master: WARNING: HADOOP_NAMENODE_OPTS has been replaced by HDFS_NAMENODE_OPTS. Using value of HADOOP_NAMENODE_OPTS.
Starting datanodes
WARNING: HADOOP_SECURE_DN_LOG_DIR has been replaced by HADOOP_SECURE_LOG_DIR. Using value of HADOOP_SECURE_DN_LOG_DIR.
hadoop-slave1: Warning: Permanently added 'hadoop-slave1,172.20.0.2' (ECDSA) to the list of known hosts.
hadoop-slave2: Warning: Permanently added 'hadoop-slave2,172.20.0.3' (ECDSA) to the list of known hosts.
hadoop-slave1: WARNING: HADOOP_SECURE_DN_LOG_DIR has been replaced by HADOOP_SECURE_LOG_DIR. Using value of HADOOP_SECURE_DN_LOG_DIR.
hadoop-slave1: WARNING: HADOOP_DATANODE_OPTS has been replaced by HDFS_DATANODE_OPTS. Using value of HADOOP_DATANODE_OPTS.
hadoop-slave2: WARNING: HADOOP_SECURE_DN_LOG_DIR has been replaced by HADOOP_SECURE_LOG_DIR. Using value of HADOOP_SECURE_DN_LOG_DIR.
hadoop-slave2: WARNING: HADOOP_DATANODE_OPTS has been replaced by HDFS_DATANODE_OPTS. Using value of HADOOP_DATANODE_OPTS.
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

## output
1283 Jps
259 NameNode
566 SecondaryNameNode
910 ResourceManager
```

### I - Programmation avec l’api HDFS

#### 1 - nstallation de l’environnement de développement

[pom.xml](/002-programmation-hdfs/lab1/pom.xml)

#### 2 - Premier exemple

[HadoopFileStatus.java](/002-programmation-hdfs/lab1/src/main/java/edu/supmti/hadoop/HadoopFileStatus.java)

pom.xml (partie `<build>...</build>`)

```xml
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
                        <mainClass>edu.supmti.hadoop.HadoopFileStatus</mainClass>
                    </manifest>
                </archive>
                <finalName>HadoopFileStatus</finalName>
            </configuration>
        </plugin>
    </plugins>
</build>
```

**P.S:** Je n'ai pas créé le fichier .jar sous Windows ; j'ai placé le projet `lab1` dans le dossier sahred_volume.

```text
root@hadoop-master:~# apt update
root@hadoop-master:~# apt install maven
root@hadoop-master:~# cd /shared_volume/lab1
root@hadoop-master:/shared_volume/lab1# mvn -version

## output
Apache Maven 3.6.0

root@hadoop-master:/shared_volume/lab1# mvn package
```

```text
root@hadoop-master:~# hadoop jar /shared_volume/lab1/target/HadoopFileStatus.jar

## output
2549 bytes
File Name: purchases.txt
File Size: 2549
File owner: root
File permission: rw-rw-rw-
File Replication: 2
File Block Size: 134217728
Block offset: 0
Block length: 2549
Block hosts: hadoop-slave2 hadoop-slave1
```

P.S: Modification des lignes `16` et `47`

```java
.
.
.
Path filepath = new Path(args[0], args[1]);
.
.
.
fs.rename(filepath, new Path(args[0], args[2]));
.
.
.
```

```text
root@hadoop-master:/shared_volume/lab1# mvn package
root@hadoop-master:~# hadoop jar /shared_volume/lab1/target/HadoopFileStatus.jar /user/root/input purchases.txt achats.txt

## output
2549 bytes
File Name: purchases.txt
File Size: 2549
File owner: root
File permission: rw-r--r--
File Replication: 2
File Block Size: 134217728
Block offset: 0
Block length: 2549
Block hosts: hadoop-slave2 hadoop-slave1
```

#### 3 - Lire un fichier sur HDFS

[ReadHDFS.java](/002-programmation-hdfs/lab1/src/main/java/edu/supmti/hadoop/ReadHDFS.java)

pom.xml (partie `<build>...</build>`)

```xml
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
                        <mainClass>edu.supmti.hadoop.ReadHDFS</mainClass>
                    </manifest>
                </archive>
                <finalName>ReadHDFS</finalName>
            </configuration>
        </plugin>
    </plugins>
</build>
```

```text
root@hadoop-master:/shared_volume/lab1# mvn package
root@hadoop-master:~# hadoop jar /shared_volume/lab1/target/ReadHDFS.jar

## output
2012-01-01      09:00   San Jose        Men's Clothing  214.05  Amex
2012-01-01      09:00   Fort Worth      Women's Clothing        153.57  Visa
2012-01-01      09:00   San Diego       Music   66.08   Cash
2012-01-01      09:00   Pittsburgh      Pet Supplies    493.51  Discover
2012-01-01      09:00   Omaha   Children's Clothing     235.63  MasterCard
2012-01-01      09:00   Stockton        Men's Clothing  247.18  MasterCard
2012-01-01      09:00   Austin  Cameras 379.6   Visa
2012-01-01      09:00   New York        Consumer Electronics    296.8   Cash
2012-01-01      09:00   Corpus Christi  Toys    25.38   Discover
2012-01-01      09:00   Fort Worth      Toys    213.88  Visa
2012-01-01      09:00   Las Vegas       Video Games     53.26   Visa
2012-01-01      09:00   Newark  Video Games     39.75   Cash
2012-01-01      09:00   Austin  Cameras 469.63  MasterCard
2012-01-01      09:00   Greensboro      DVDs    290.82  MasterCard
2012-01-01      09:00   San Francisco   Music   260.65  Discover
2012-01-01      09:00   Lincoln Garden  136.9   Visa
2012-01-01      09:00   Buffalo Women's Clothing        483.82  Visa
2012-01-01      09:00   San Jose        Women's Clothing        215.82  Cash
2012-01-01      09:00   Boston  Cameras 418.94  Amex
2012-01-01      09:00   Houston Baby    309.16  Visa
2012-01-01      09:00   Las Vegas       Books   93.39   Visa
2012-01-01      09:00   Virginia Beach  Children's Clothing     376.11  Amex
2012-01-01      09:01   Riverside       Consumer Electronics    252.88  Cash
2012-01-01      09:01   Tulsa   Baby    205.06  Visa
2012-01-01      09:01   Reno    Crafts  88.25   Visa
2012-01-01      09:01   Chicago Books   31.08   Cash
2012-01-01      09:01   Fort Wayne      Men's Clothing  370.55  Amex
2012-01-01      09:01   San Bernardino  Consumer Electronics    170.2   Cash
2012-01-01      09:01   Madison Men's Clothing  16.78   Visa
2012-01-01      09:01   Austin  Sporting Goods  327.75  Discover
2012-01-01      09:01   Portland        CDs     108.69  Amex
2012-01-01      09:01   Riverside       Sporting Goods  15.41   Discover
2012-01-01      09:01   Reno    Toys    80.46   Visa
2012-01-01      09:01   Anchorage       Music   298.86  MasterCard
2012-01-01      09:01   Pittsburgh      Sporting Goods  475.26  Amex
2012-01-01      09:01   Spokane Garden  3.85    Amex
2012-01-01      09:01   Spokane Computers       287.65  MasterCard
2012-01-01      09:01   Fresno  CDs     466.64  MasterCard
2012-01-01      09:01   Omaha   Baby    255.68  MasterCard
2012-01-01      09:01   Anchorage       DVDs    6.38    Amex
2012-01-01      09:01   Aurora  Consumer Electronics    117.81  MasterCard
2012-01-01      09:01   Philadelphia    DVDs    351.31  Cash
2012-01-01      09:01   Fremont Baby    222.61  Cash
2012-01-01      09:01   Anchorage       Crafts  22.36   Amex
2012-01-01      09:02   Norfolk Women's Clothing        189.01  Amex
2012-01-01      09:02   Chandler        Books   414.08  Cash
2012-01-01      09:02   Minneapolis     Computers       182.05  Visa
2012-01-01      09:02   Honolulu        Cameras 345.18  Discover
2012-01-01      09:02   Indianapolis    Books   135.96  Discover
2012-01-01      09:02   Chandler        Books   344.09  Discover
null
```

P.S: Modification de ligne `14`

```java
.
.
.
Path nomcomplet = new Path(".", args[0].replace("./", ""));
.
.
.
```

```text
root@hadoop-master:/shared_volume/lab1# mvn package
root@hadoop-master:~# hadoop jar /shared_volume/lab1/target/ReadHDFS.jar ./purchases.txt

## output
2012-01-01      09:00   San Jose        Men's Clothing  214.05  Amex
2012-01-01      09:00   Fort Worth      Women's Clothing        153.57  Visa
2012-01-01      09:00   San Diego       Music   66.08   Cash
2012-01-01      09:00   Pittsburgh      Pet Supplies    493.51  Discover
2012-01-01      09:00   Omaha   Children's Clothing     235.63  MasterCard
2012-01-01      09:00   Stockton        Men's Clothing  247.18  MasterCard
2012-01-01      09:00   Austin  Cameras 379.6   Visa
2012-01-01      09:00   New York        Consumer Electronics    296.8   Cash
2012-01-01      09:00   Corpus Christi  Toys    25.38   Discover
2012-01-01      09:00   Fort Worth      Toys    213.88  Visa
2012-01-01      09:00   Las Vegas       Video Games     53.26   Visa
2012-01-01      09:00   Newark  Video Games     39.75   Cash
2012-01-01      09:00   Austin  Cameras 469.63  MasterCard
2012-01-01      09:00   Greensboro      DVDs    290.82  MasterCard
2012-01-01      09:00   San Francisco   Music   260.65  Discover
2012-01-01      09:00   Lincoln Garden  136.9   Visa
2012-01-01      09:00   Buffalo Women's Clothing        483.82  Visa
2012-01-01      09:00   San Jose        Women's Clothing        215.82  Cash
2012-01-01      09:00   Boston  Cameras 418.94  Amex
2012-01-01      09:00   Houston Baby    309.16  Visa
2012-01-01      09:00   Las Vegas       Books   93.39   Visa
2012-01-01      09:00   Virginia Beach  Children's Clothing     376.11  Amex
2012-01-01      09:01   Riverside       Consumer Electronics    252.88  Cash
2012-01-01      09:01   Tulsa   Baby    205.06  Visa
2012-01-01      09:01   Reno    Crafts  88.25   Visa
2012-01-01      09:01   Chicago Books   31.08   Cash
2012-01-01      09:01   Fort Wayne      Men's Clothing  370.55  Amex
2012-01-01      09:01   San Bernardino  Consumer Electronics    170.2   Cash
2012-01-01      09:01   Madison Men's Clothing  16.78   Visa
2012-01-01      09:01   Austin  Sporting Goods  327.75  Discover
2012-01-01      09:01   Portland        CDs     108.69  Amex
2012-01-01      09:01   Riverside       Sporting Goods  15.41   Discover
2012-01-01      09:01   Reno    Toys    80.46   Visa
2012-01-01      09:01   Anchorage       Music   298.86  MasterCard
2012-01-01      09:01   Pittsburgh      Sporting Goods  475.26  Amex
2012-01-01      09:01   Spokane Garden  3.85    Amex
2012-01-01      09:01   Spokane Computers       287.65  MasterCard
2012-01-01      09:01   Fresno  CDs     466.64  MasterCard
2012-01-01      09:01   Omaha   Baby    255.68  MasterCard
2012-01-01      09:01   Anchorage       DVDs    6.38    Amex
2012-01-01      09:01   Aurora  Consumer Electronics    117.81  MasterCard
2012-01-01      09:01   Philadelphia    DVDs    351.31  Cash
2012-01-01      09:01   Fremont Baby    222.61  Cash
2012-01-01      09:01   Anchorage       Crafts  22.36   Amex
2012-01-01      09:02   Norfolk Women's Clothing        189.01  Amex
2012-01-01      09:02   Chandler        Books   414.08  Cash
2012-01-01      09:02   Minneapolis     Computers       182.05  Visa
2012-01-01      09:02   Honolulu        Cameras 345.18  Discover
2012-01-01      09:02   Indianapolis    Books   135.96  Discover
2012-01-01      09:02   Chandler        Books   344.09  Discover
null
```

#### 4 - Ecrire un fichier sur HDFS

[WriteHDFS.java](/002-programmation-hdfs/lab1/src/main/java/edu/supmti/hadoop/WriteHDFS.java)

pom.xml (partie `<build>...</build>`)

```xml
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
                        <mainClass>edu.supmti.hadoop.WriteHDFS</mainClass>
                    </manifest>
                </archive>
                <finalName>WriteHDFS</finalName>
            </configuration>
        </plugin>
    </plugins>
</build>
```

```text
root@hadoop-master:/shared_volume/lab1# mvn package
root@hadoop-master:~# hadoop jar /shared_volume/lab1/target/WriteHDFS.jar ./input/bonjour.txt
root@hadoop-master:~# hadoop jar /shared_volume/lab1/target/ReadHDFS.jar bonjour.txt

## output
Bonjour tout le monde !
null
```
