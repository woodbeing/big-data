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

