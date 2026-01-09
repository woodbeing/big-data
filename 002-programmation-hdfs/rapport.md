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
> docker exec -it hadoop-master text

## output
root@hadoop-master:~#
```

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


root@hadoop-master:~# jps

## output
1283 Jps
259 NameNode
566 SecondaryNameNode
910 ResourceManager
```

### I - Programmation avec l’api HDFS

#### 1 - nstallation de l’environnement de développement

* [lab1](/shared-hadoop-project/lab1)
  * [pom.xml](/shared-hadoop-project/lab1/pom.xml)

#### 2 - Premier exemple

* [lab1](/shared-hadoop-project/lab1)
  * [src](/shared-hadoop-project/lab1/src)
    * [java/edu/supmti/hadoop/HadoopFileStatus.java](/shared-hadoop-project/lab1/src/main/java/edu/supmti/hadoop/HadoopFileStatus.java)

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
                <finalName>HadoopFileStatus.jar</finalName>
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
