# Rapport d'installation hadoop
## envirenement windows

### I - Installation Cluster Hadoop

**1 - Installation docker** 
```
> docker version

## output
Client:
 Version:           29.1.2
 API version:       1.52
 Go version:        go1.25.5
 Git commit:        890dcca
 Built:             Tue Dec  2 21:57:33 2025
 OS/Arch:           windows/amd64
 Context:           desktop-linux

Server: Docker Desktop 4.54.0 (212467)
 Engine:
  Version:          29.1.2
  API version:      1.52 (minimum version 1.44)
  Go version:       go1.25.5
  Git commit:       de45c2a
  Built:            Tue Dec  2 21:55:26 2025
  OS/Arch:          linux/amd64
  Experimental:     false
 containerd:
  Version:          v2.2.0
  GitCommit:        1c4457e00facac03ce1d75f7b6777a7a851e5c41
 runc:
  Version:          1.3.4
  GitCommit:        v1.3.4-0-gd6d73eb8
 docker-init:
  Version:          0.19.0
  GitCommit:        de40ad0
```

**2 - Télécharger l’image hadoop-spark-cluster**
```
> docker pull yassern1/hadoop-spark-jupyter:1.0.3
```
```
## output perndant install
7c457f213c76: Pull complete
4f4fb700ef54: Pull complete
88defd485d1b: Downloading [=====================================>             ]  155.7MB/208.1MB
0c7c8e899c31: Download complete
62d02b53c336: Download complete
93cfcb2f0187: Download complete
873ac057ca4e: Downloading [===========>                                       ]  78.12MB/348MB
40091887436b: Downloading [=============>                                     ]  79.77MB/301.7MB
e44c72237599: Waiting
7f61e7eab4ae: Waiting
fae462c2169c: Waiting
b71f1f2e032a: Waiting
47fc09f775a8: Waiting
3e7b11a95670: Waiting
b551d0588ff7: Waiting
70e2e8d7dba3: Waiting
776a89496dc4: Waiting
916b439a2e8b: Waiting
5c336d7045ea: Waiting
```
```
## output après install
1.0.3: Pulling from yassern1/hadoop-spark-jupyter
7c457f213c76: Pull complete
4f4fb700ef54: Pull complete
88defd485d1b: Pull complete
0c7c8e899c31: Pull complete
62d02b53c336: Pull complete
93cfcb2f0187: Pull complete
873ac057ca4e: Pull complete
40091887436b: Pull complete
e44c72237599: Pull complete
7f61e7eab4ae: Pull complete
fae462c2169c: Pull complete
b71f1f2e032a: Pull complete
47fc09f775a8: Pull complete
3e7b11a95670: Pull complete
b551d0588ff7: Pull complete
70e2e8d7dba3: Pull complete
776a89496dc4: Pull complete
916b439a2e8b: Pull complete
5c336d7045ea: Pull complete
Digest: sha256:511eec146b04568b392ff0a3522c54b47d7c010181334c96bcc9dbbe17ca2fa3
Status: Downloaded newer image for yassern1/hadoop-spark-jupyter:1.0.3
docker.io/yassern1/hadoop-spark-jupyter:1.0.3
```
```
> docker images

## output
                                                                                i Info →   U  In Use
IMAGE                                  ID             DISK USAGE   CONTENT SIZE   EXTRA
creamy-next-app:latest                 513abac24737        249MB             0B    U
mcr.microsoft.com/dotnet/aspnet:10.0   45b33e8b92bb        230MB             0B
mysql:latest                           2c849dee4ca9        859MB             0B    U
postgres:latest                        194f5f2a900a        456MB             0B
yassern1/hadoop-spark-jupyter:1.0.3    aa7263287dc0       2.37GB             0B
```

**3 - Création d’un volume de partage**
```
> dir

## output
 Directory of C:\..\..\..\SUPMTI\big-data

08/01/2026  17:25    <DIR>          .
08/01/2026  15:24    <DIR>          ..
08/01/2026  17:25    <DIR>          001-installation-hadoop
08/01/2026  17:25    <DIR>          shared-hadoop-project
```

**4 - Création du cluster (trois conteneurs)**
```
> docker compose up --build -d

## output
[+] Running 4/4
 ✔ Network 001-installation-hadoop_hadoop  Created                                                                 0.0s
 ✔ Container hadoop-slave1                 Started                                                                 0.6s
 ✔ Container hadoop-master                 Started                                                                 0.9s
 ✔ Container hadoop-slave2                 Started                                                                 0.5s
```
![](/001-installation-hadoop/001-installation-hadoop_docker-desktop.png)

**5 - Accéder au master**
```cmd
> docker exec -it hadoop-master bash

## output
root@hadoop-master:~#
```

**6 - Démarrer hadoop et yarn**
```
root@hadoop-master:~# ./start-hadoop.sh

## output
Starting namenodes on [hadoop-master]
hadoop-master: Warning: Permanently added 'hadoop-master,172.20.0.4' (ECDSA) to the list of known hosts.
hadoop-master: WARNING: HADOOP_NAMENODE_OPTS has been replaced by HDFS_NAMENODE_OPTS. Using value of HADOOP_NAMENODE_OPTS.
Starting datanodes
WARNING: HADOOP_SECURE_DN_LOG_DIR has been replaced by HADOOP_SECURE_LOG_DIR. Using value of HADOOP_SECURE_DN_LOG_DIR.
hadoop-slave2: Warning: Permanently added 'hadoop-slave2,172.20.0.3' (ECDSA) to the list of known hosts.
hadoop-slave1: Warning: Permanently added 'hadoop-slave1,172.20.0.2' (ECDSA) to the list of known hosts.
hadoop-slave2: WARNING: HADOOP_SECURE_DN_LOG_DIR has been replaced by HADOOP_SECURE_LOG_DIR. Using value of HADOOP_SECURE_DN_LOG_DIR.
hadoop-slave2: WARNING: HADOOP_DATANODE_OPTS has been replaced by HDFS_DATANODE_OPTS. Using value of HADOOP_DATANODE_OPTS.
hadoop-slave1: WARNING: HADOOP_SECURE_DN_LOG_DIR has been replaced by HADOOP_SECURE_LOG_DIR. Using value of HADOOP_SECURE_DN_LOG_DIR.
hadoop-slave1: WARNING: HADOOP_DATANODE_OPTS has been replaced by HDFS_DATANODE_OPTS. Using value of HADOOP_DATANODE_OPTS.
Starting secondary namenodes [hadoop-master]
hadoop-master: Warning: Permanently added 'hadoop-master,172.20.0.4' (ECDSA) to the list of known hosts.
hadoop-master: WARNING: HADOOP_SECONDARYNAMENODE_OPTS has been replaced by HDFS_SECONDARYNAMENODE_OPTS. Using value of HADOOP_SECONDARYNAMENODE_OPTS.
Starting resourcemanager
Starting nodemanagers
hadoop-slave1: Warning: Permanently added 'hadoop-slave1,172.20.0.2' (ECDSA) to the list of known hosts.
hadoop-slave2: Warning: Permanently added 'hadoop-slave2,172.20.0.3' (ECDSA) to the list of known hosts.
```
![name node web UI](/001-installation-hadoop/001-installation-hadoop_namenode-web-ui.png "name node web UI")
![ressource manager UI](/001-installation-hadoop/001-installation-hadoop_ressource-manager-ui.png "ressource manager UI")

**7 - Manipulations sur HDFS**
```
root@hadoop-master:~# hdfs dfs -mkdir input

## output
mkdir: `hdfs://hadoop-master:9000/user/root': No such file or directory

root@hadoop-master:~# hadoop fs -mkdir -p /user/root
root@hadoop-master:~# hdfs dfs -mkdir input
root@hadoop-master:~# hdfs dfs -ls

## output
Found 1 items
drwxr-xr-x   - root supergroup          0 2026-01-08 20:03 input 

root@hadoop-master:~# hdfs dfs -ls -R -h ./

## output
drwxr-xr-x   - root supergroup          0 2026-01-08 20:03 input

root@hadoop-master:~# hdfs dfs -put /shared_volume/purchases.txt .
root@hadoop-master:~# hdfs dfs -ls -R

## output
drwxr-xr-x   - root supergroup          0 2026-01-08 20:03 input
-rw-r--r--   2 root supergroup       2549 2026-01-08 20:25 purchases.txt
```