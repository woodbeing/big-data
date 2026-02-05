# Rapport de Cluster Spark avec Docker

## envirenement windows

### I - Installation Cluster Spark

#### 1 - Accéder au master

```text
> docker exec -it hadoop-master bash
```

#### 2 - Démarrer hadoop et yarn

```text
root@hadoop-master:~# ./start-hadoop.sh
root@hadoop-master:~# ./start-spark.sh
```

### II - Premiers exemples sur apache spark

#### 1 - Premier exemple spark avec spark-submit

```text
root@hadoop-master:~# spark-submit --executor-memory 1G --class org.apache.spark.examples.SparkPi --master local[*] $SPARK_HOME/examples/jars/spark-examples_2.12-3.2.4.jar 100  
```

```text
Pi is roughly 3.1426643142664314
```

#### 2 - Application word count avec spark-shell

```text
root@hadoop-master:~# spark-shell --executor-memory 1G
```

```text
Setting default log level to "WARN".
To adjust logging level use sc.setLogLevel(newLevel). For SparkR, use setLogLevel(newLevel).
2026-02-05 19:22:03,919 WARN yarn.Client: Neither spark.yarn.jars nor spark.yarn.archive is set, falling back to uploading libraries under SPARK_HOME.
Spark context Web UI available at http://hadoop-master:4040
Spark context available as 'sc' (master = yarn, app id = application_1770313358898_0004).
Spark session available as 'spark'.
Welcome to
      ____              __
     / __/__  ___ _____/ /__
    _\ \/ _ \/ _ `/ __/  '_/
   /___/ .__/\_,_/_/ /_/\_\   version 3.2.4
      /_/

Using Scala version 2.12.15 (OpenJDK 64-Bit Server VM, Java 1.8.0_362)
Type in expressions to have them evaluated.
Type :help for more information.

scala>
```

```scala
val data = sc.textFile("hdfs://hadoop-master:9000/user/root/input/alice.txt")
val count = data.flatMap(line => line.split(" ")).map(word => (word, 1)).reduceByKey(_+_)
count.saveAsTextFile("hdfs://hadoop-master:9000/user/root/output/respark1")
```

```spark
scala> val data=sc.textFile("hdfs://hadoop-master:9000/user/root/input/alice.txt")
data: org.apache.spark.rdd.RDD[String] = hdfs://hadoop-master:9000/user/root/input/alice.txt MapPartitionsRDD[1] at textFile at <console>:23

scala> val count= data.flatMap(line => line.split(" ")).map(word => (word, 1)).reduceByKey(_+_)
count: org.apache.spark.rdd.RDD[(String, Int)] = ShuffledRDD[4] at reduceByKey at <console>:23

scala> count.saveAsTextFile("hdfs://hadoop-master:9000/user/root/output/respark1")
[Stage 0:>                                                          (0 + 1) 
[Stage 0:=============================>                             (1 + 1)
```

#### 3 - Soumettre une Application python

```text
root@hadoop-master:~# touch wordcount.py
root@hadoop-master:~# nano wordcount.py 
```

```python
import pyspark
from pyspark.sql import SparkSession
spark = SparkSession.builder.master("yarn").appName('wordcount').getOrCreate()
data = spark.sparkContext.textFile("hdfs://hadoop-master:9000/user/root/input/alice.txt")
words=data.flatMap(lambda line: line.split(" "))
wordCounts = words.map(lambda word: (word, 1)).reduceByKey(lambda a, b: a + b)
wordCounts.saveAsTextFile("hdfs://hadoop-master:9000/user/root/output/rr2")
```

```text
root@hadoop-master:~# spark-submit --master local[*] wordcount.py
```

```text
root@hadoop-master:~# hdfs dfs -ls ./output
```

```text
Found 2 items
drwxr-xr-x   - root supergroup          0 2026-02-05 19:27 output/respark1
drwxr-xr-x   - root supergroup          0 2026-02-05 19:54 output/rr2
```

```python
# 1
df = spark.read.option("header", "true").option("inferSchema", "true").csv("hdfs://hadoop-master:9000/data/fifa/results.csv")
# 2
df.printSchema()
# 3
df.createOrReplaceTempView("fifa")
# 4
spark.sql("""
      SELECT COUNT(*) AS total_matches_USA
      FROM fifa 
      WHERE home_team = 'USA' 
      OR away_team = 'USA'
""").show()
```
