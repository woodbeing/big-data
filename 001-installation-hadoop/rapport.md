# Rapport d'installation hadoop

## envirenement windows

### I - Installation Cluster Hadoop

#### 1 - Installation docker

```text
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

#### 2 - Télécharger l’image hadoop-spark-cluster

```text
> docker pull yassern1/hadoop-spark-jupyter:1.0.3
```

```text
## output pendant install
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

```text
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

```text
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

#### 3 - Création d’un volume de partage

```text
> dir

## output
 Directory of C:\..\..\..\SUPMTI\big-data

08/01/2026  17:25    <DIR>          .
08/01/2026  15:24    <DIR>          ..
08/01/2026  17:25    <DIR>          001-installation-hadoop
08/01/2026  17:25    <DIR>          shared-hadoop-project
```

#### 4 - Création du cluster (trois conteneurs)

```text
> docker compose up --build -d

## output
[+] Running 4/4
 ✔ Network 001-installation-hadoop_hadoop  Created                                                                 0.0s
 ✔ Container hadoop-slave1                 Started                                                                 0.6s
 ✔ Container hadoop-master                 Started                                                                 0.9s
 ✔ Container hadoop-slave2                 Started                                                                 0.5s
```

![docker desktop](/001-installation-hadoop/001-installation-hadoop_docker-desktop.png "docker desktop")

#### 5 - Accéder au master

```text
> docker exec -it hadoop-master text

## output
root@hadoop-master:~#
```

#### 6 - Démarrer hadoop et yarn

```text
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

#### 7 - Manipulations sur HDFS

```text
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

root@hadoop-master:~# hdfs dfs -cat purchases.txt

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

root@hadoop-master:~# hdfs dfs -tail purchases.txt

## output
ustin   Sporting Goods  327.75  Discover
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

root@hadoop-master:~# hdfs dfs -rm purchases.txt

## output
Deleted purchases.txt

root@hadoop-master:~# hdfs dfs -copyFromLocal /shared_volume/purchases.txt ./input
root@hadoop-master:~# hdfs dfs -ls

## output
Found 1 items
drwxr-xr-x   - root supergroup          0 2026-01-08 20:44 input

root@hadoop-master:~# hdfs dfs -ls -R

## output
drwxr-xr-x   - root supergroup          0 2026-01-08 20:44 input
-rw-r--r--   2 root supergroup       2549 2026-01-08 20:44 input/purchases.txt

root@hadoop-master:~# hdfs dfs -chmod 777 ./input/purchases.txt
root@hadoop-master:~# hdfs dfs -ls -R

## output
drwxr-xr-x   - root supergroup          0 2026-01-08 20:44 input
-rwxrwxrwx   2 root supergroup       2549 2026-01-08 20:44 input/purchases.txt

root@hadoop-master:~# hdfs dfs -chmod ugo-x ./input/purchases.txt
root@hadoop-master:~# hdfs dfs -ls -R

## output
drwxr-xr-x   - root supergroup          0 2026-01-08 20:44 input
-rw-rw-rw-   2 root supergroup       2549 2026-01-08 20:44 input/purchases.txt

root@hadoop-master:~# hdfs dfs -get ./input/purchases.txt /shared_volume/achat.txt
```

#### 8 - télécharger un fichier sur hdfs

```text
root@hadoop-master:~# hdfs dfs -mkdir web_input
```

```text
> wget http://www.textfiles.com/etext/FICTION/alice.txt

## output
--2026-01-08 20:56:44--  http://www.textfiles.com/etext/FICTION/alice.txt
Resolving www.textfiles.com (www.textfiles.com)... 208.86.224.90
Connecting to www.textfiles.com (www.textfiles.com)|208.86.224.90|:80... connected.
HTTP request sent, awaiting response... 200 OK
Length: 150886 (147K) [text/plain]
Saving to: 'alice.txt'

alice.txt                              100%[============================================================================>] 147.35K   468KB/s    in 0.3s

2026-01-08 20:56:45 (468 KB/s) - 'alice.txt' saved [150886/150886]
```

```text
root@hadoop-master:~# hdfs dfs -put /shared_volume/alice.txt web_input
root@hadoop-master:~# hdfs dfs -ls web_input

## output
Found 1 items
-rw-r--r--   2 root supergroup     150886 2026-01-08 21:01 web_input/alice.txt
```
