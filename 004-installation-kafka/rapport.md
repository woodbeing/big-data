# Rapport de Programmation Installation Kafka

## envirenement windows

### I - Installation Kafka

```text
> docker compose up -d
```

```text
[+] Running 3/3
 ✔ Container hadoop-slave1  Started        0.7s 
 ✔ Container hadoop-slave2  Started        0.7s 
 ✔ Container hadoop-master  Started        0.8s
```

```text
> docker exec -it hadoop-master bash
```

```text
root@hadoop-master:~# ./start-hadoop.sh
root@hadoop-master:~# ./start-kafka-zookeeper.sh
root@hadoop-master:~# jps
```

```text
272 NameNode
931 ResourceManager
2228 Jps
584 SecondaryNameNode
1305 QuorumPeerMain
1306 Kafka
```

### II - Première utilisation d’apache Kafka

#### 1 - Création d'un topic

```text
root@hadoop-master:~# kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic Hello-Kafka
root@hadoop-master:~# kafka-topics.sh --list --bootstrap-server localhost:9092
```

```text
Hello-Kafka
```

#### 2 - Description d'un topic

```text
root@hadoop-master:~# kafka-topics.sh --describe --topic Hello-Kafka --bootstrap-server localhost:9092
```

```text
Topic: Hello-Kafka      TopicId: uFR5ZCNAQnyejJsCy4ndaQ PartitionCount: 1       ReplicationFactor: 1    Configs:
Topic: Hello-Kafka      Partition: 0    Leader: 0       Replicas: 0     Isr: 0
```

#### 3 - Ecrire des évènements dans un topic

```text
root@hadoop-master:~# kafka-console-producer.sh --bootstrap-server localhost:9092 --topic Hello-Kafka
```

```text
> what do i type here?
> anything written here shows in the other window without resetting
> 
```

#### 4 - Lire des événements

```text
root@hadoop-master:~# kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic Hello-Kafka --from-beginning
```

```text
what do i type here?
anything written here shows in the other window without resetting
```

![004-installation-kafka-event-test](/004-installation-kafka/004-installation-kafka-event-test.png "004-installation-kafka-event-test")

### III - Création d'une application kafka

[pom.xml](/004-installation-kafka/kafka_lab/pom.xml)  

#### 1 - Création du Producer

[EventProducer.java](/004-installation-kafka/kafka_lab/src/main/java/edu/supmti/kafka/EventProducer.java)

```text
root@hadoop-master:~# cd /shared_volume/kafka_lab/
root@hadoop-master:/shared_volume/kafka_lab# mvn clean package
```

```text
root@hadoop-master:~# java -jar /shared_volume/kafka-producer-app-jar-with-dependencies.jar Hello-Kafka
root@hadoop-master:~# kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic Hello-Kafka --from-beginning
```

```text
what do i type here?
anything written here shows in the other window without resetting
0
1
2
3
4
5
6
7
8
9
```

#### 2 - Création du consumer

[EventConsumer.java](/004-installation-kafka/kafka_lab/src/main/java/edu/supmti/kafka/EventConsumer.java)

```text
root@hadoop-master:~# cd /shared_volume/kafka_lab/
root@hadoop-master:/shared_volume/kafka_lab# mvn clean package
```

```text
root@hadoop-master:~# java -jar /shared_volume/kafka-consumer-app-jar-with-dependencies.jar Hello-Kafka
```

![004-installation-kafka-consumer-class-preview](/004-installation-kafka/004-installation-kafka-consumer-class-preview.png "Consumer Preview")

### IV - Ingestion des données d’une source(fichier) vers une destination(sink) HDFS avec Kafka Connect

```text
root@hadoop-master:~# cat $KAFKA_HOME/config/connect-standalone.properties
```

```text
# Licensed to the Apache Software Foundation (ASF) under one or more
# contributor license agreements.  See the NOTICE file distributed with
# this work for additional information regarding copyright ownership.
# The ASF licenses this file to You under the Apache License, Version 2.0
# (the "License"); you may not use this file except in compliance with
# the License.  You may obtain a copy of the License at
#
#    http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

# These are defaults. This file just demonstrates how to override some settings.
bootstrap.servers=localhost:9092

# The converters specify the format of data in Kafka and how to translate it into Connect data. Every Connect user will
# need to configure these based on the format they want their data in when loaded from or stored into Kafka
key.converter=org.apache.kafka.connect.json.JsonConverter
value.converter=org.apache.kafka.connect.json.JsonConverter
# Converter-specific settings can be passed in by prefixing the Converter's setting with the converter we want to apply
# it to
key.converter.schemas.enable=true
value.converter.schemas.enable=true

offset.storage.file.filename=/tmp/connect.offsets
# Flush much faster than normal, which is useful for testing/debugging
offset.flush.interval.ms=10000

# Set to a list of filesystem paths separated by commas (,) to enable class loading isolation for plugins
# (connectors, converters, transformations). The list should consist of top level directories that include
# any combination of:
# a) directories immediately containing jars with plugins and their dependencies
# b) uber-jars with plugins and their dependencies
# c) directories immediately containing the package directory structure of classes of plugins and their dependencies
# Note: symlinks will be followed to discover dependencies or plugins.
# Examples:
# plugin.path=/usr/local/share/java,/usr/local/share/kafka/plugins,/opt/connectors,
plugin.path=/usr/local/kafka/libs/
```

```text
root@hadoop-master:~# cat $KAFKA_HOME/config/connect-file-source.properties
```

```text
name=local-file-source
connector.class=FileStreamSource
tasks.max=1
file=test-source.txt
topic=connect-topic
```

```text
root@hadoop-master:~# cat $KAFKA_HOME/config/connect-file-sink.properties
```

```text
name=local-file-sink
connector.class=FileStreamSink
tasks.max=1
file=test-sink.txt
topics=connect-topic
```

```text
root@hadoop-master:~# echo "bonjour kafka" > /tmp/test-source.txt
root@hadoop-master:~# echo "Bienvenue dans le monde du streaming" >> /tmp/te
st-source.txt
root@hadoop-master:~# cat /tmp/test-source.txt
```

```text
bonjour kafka
Bienvenue dans le monde du streaming
```

```text
$KAFKA_HOME/bin/connect-standalone.sh $KAFKA_HOME/config/connect-standalone.properties $KAFKA_HOME/config/connect-file-source.properties $KAFKA_HOME/config/connect-file-sink.properties
```

```text
root@hadoop-master# more /tmp/test-sink.txt
bonjour kafka
Bienvenue dans le monde du streaming
Exercice Kafka Connect simple
Bienvenue dans le monde du streaming
Exercice Kafka Connect simple
Exercice Kafka Connect simple
Exercice Kafka Connect simple
```

### V - Application Word Count avec Kafka Streams

[pom.xml](/004-installation-kafka/kafka_lab/pom.xml)

[WordCountApp.java](/004-installation-kafka/kafka_lab/src/main/java/edu/supmti/kafka/WordCountApp.java)

```text
root@hadoop-master:~# kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic input-topic
root@hadoop-master:~# kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic output-topic
```

```text
root@hadoop-master:~# java -jar /shared_volume/kafka/kafka-wordcount-app-jar-with-dependencies.jar input-topic output-topic
```

![004-installation-kafka-word-count-jar](/004-installation-kafka/004-installation-kafka-word-count-jar.png "Word Count Class")

```text
root@hadoop-master:~# kafka-console-producer.sh --bootstrap-server localhost:9092 --topic input-topic
```

![004-installation-kafka-word-count-producer](/004-installation-kafka/004-installation-kafka-word-count-producer.png "Word Count Producer")

```text
root@hadoop-master:~# kafka-console-consumer.sh --topic output-topic --from-beginning --bootstrap-server localhost:9092 --property print.key=true
```

![004-installation-kafka-word-count-consumer](/004-installation-kafka/004-installation-kafka-word-count-consumer.png "Word Count Consumer")

### VI - Mise en place d’un cluster Kafka à deux brokers et application WordCount interactive

#### 1 - Configuration de plusieurs brokers

```text
root@hadoop-master:~# cp $KAFKA_HOME/config/server.properties $KAFKA_HOME/config/server-one.properties
root@hadoop-master:~# cp $KAFKA_HOME/config/server.properties $KAFKA_HOME/config/server-two.properties
```

```text
root@hadoop-master:~# nano $KAFKA_HOME/config/server-one.properties
root@hadoop-master:~# nano $KAFKA_HOME/config/server-two.properties
```

```text
root@hadoop-master:~# cat $KAFKA_HOME/config/server-one.properties
```

```text
broker.id=1
listeners=PLAINTEXT://localhost:9093
num.network.threads=3
num.io.threads=8
socket.send.buffer.bytes=102400
socket.receive.buffer.bytes=102400
socket.request.max.bytes=104857600
log.dirs=/tmp/kafka-logs-1
num.partitions=1
num.recovery.threads.per.data.dir=1
offsets.topic.replication.factor=1
transaction.state.log.replication.factor=1
transaction.state.log.min.isr=1
log.retention.hours=168
log.retention.check.interval.ms=300000
zookeeper.connect=localhost:2181
zookeeper.connection.timeout.ms=18000
group.initial.rebalance.delay.ms=0
```

```text
root@hadoop-master:~# cat $KAFKA_HOME/config/server-two.properties
```

```text
broker.id=2
listeners=PLAINTEXT://localhost:9094
num.network.threads=3
num.io.threads=8
socket.send.buffer.bytes=102400
socket.receive.buffer.bytes=102400
socket.request.max.bytes=104857600
log.dirs=/tmp/kafka-logs-2
num.partitions=1
num.recovery.threads.per.data.dir=1
offsets.topic.replication.factor=1
transaction.state.log.replication.factor=1
transaction.state.log.min.isr=1
log.retention.hours=168
log.retention.check.interval.ms=300000
zookeeper.connect=localhost:2181
zookeeper.connection.timeout.ms=18000
group.initial.rebalance.delay.ms=0
```

```text
root@hadoop-master:~# $KAFKA_HOME/bin/kafka-server-start.sh -daemon $KAFKA_HOME/config/server-one.properties
root@hadoop-master:~# $KAFKA_HOME/bin/kafka-server-start.sh -daemon $KAFKA_HOME/config/server-two.properties
```

```text
root@hadoop-master:~# jps
```

```text
21521 Kafka
5746 Kafka
3012 ResourceManager
20598 Kafka
2664 SecondaryNameNode
21625 Jps
3386 QuorumPeerMain
2351 NameNode
```

```text
root@hadoop-master:~# cat /tmp/kafka-logs-1/*
```

```text
0
0
#
#Tue Feb 03 16:04:20 GMT 2026
cluster.id=kd8Sm7RbTQK9Ypn7JDQ4KQ
version=0
broker.id=1
0
0
```

```text
root@hadoop-master:~# cat /tmp/kafka-logs-2/*
```

```text
0
0
#
#Tue Feb 03 16:05:23 GMT 2026
cluster.id=kd8Sm7RbTQK9Ypn7JDQ4KQ
version=0
broker.id=2
0
0
```

```text
root@hadoop-master:~# $KAFKA_HOME/bin/kafka-topics.sh --create --bootstrap-server localhost:9093 --replication-factor 2 --partitions 1 --topic WordCount-Topic
root@hadoop-master:~# $KAFKA_HOME/bin/kafka-topics.sh --describe --topic WordCount-Topic --bootstrap-server localhost:9094
```

```text
Topic: WordCount-Topic  TopicId: FLrYu1uVSZyB6GoCHMT1lg PartitionCount: 1  ReplicationFactor: 2     Configs:
        Topic: WordCount-Topic  Partition: 0    Leader: 1       Replicas: 1,2       Isr: 1,2
```

#### 2 - Création de l’application word count

[WordCountProducer.java](/004-installation-kafka/kafka_lab/src/main/java/edu/supmti/kafka/WordCountProducer.java)

[WordCountConsumer.java](/004-installation-kafka/kafka_lab/src/main/java/edu/supmti/kafka/WordCountConsumer.java)

```text
root@hadoop-master:~# java -jar /shared_volume/Kafka/kafka-wordcount-producer-jar-with-dependencies.jar
```

```text
something
> nothing
> here is a thing
> hah
> nah
> a ah
> something
> something
> something
> something
> something
```

```text
root@hadoop-master:~# java -jar /shared_volume/Kafka/kafka-wordcount-consumer-jar-with-dependencies.jar
```

```text
Fr?quences actuelles : {hah=1}
Fr?quences actuelles : {nah=1, hah=1}
Fr?quences actuelles : {a=1, nah=1, ah=1, hah=1}
Fr?quences actuelles : {a=1, nah=1, ah=1, hah=1, something=1}
Fr?quences actuelles : {a=1, nah=1, ah=1, hah=1, something=2}
Fr?quences actuelles : {a=1, nah=1, ah=1, hah=1, something=3}
Fr?quences actuelles : {a=1, nah=1, ah=1, hah=1, something=4}
Fr?quences actuelles : {a=1, nah=1, ah=1, hah=1, something=5}
```

#### 4 - kafka-ui

[docker-compose.yml](/001-installation-hadoop/docker-compose.yml)

![004-installation-kafka-kafka-ui](/004-installation-kafka/004-installation-kafka-kafka-ui.png "Kafka UI")
