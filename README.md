# Menu

- [Project Usage Guide](##Project)
- [Kafka](##Kafka)
- [Docker and Docker compose](##Docker)
- [OpenApi 3](##OpenApi)

## Project

## Kafka

## Docker

To start the project you need to run the docker-compose file, it is in the dev folder at the root of the project.
Inside the folder you will find the file: [docker-compose-m1-single-broker
](./dev/docker-compose-m1-single-broker.yml).

Run the following command in the terminal to load kafka locally:

```bash
docker-compose -f docker-compose-m1-single-broker.yml up -d 
```

To check if it is running:

```bash
$ docker ps -a
CONTAINER ID   IMAGE                             COMMAND                  CREATED          STATUS          PORTS                                              NAMES
64cf60b6442f   confluentinc/cp-kafka:7.3.2       "/etc/confluent/dock…"   12 seconds ago   Up 10 seconds   0.0.0.0:9092->9092/tcp, 0.0.0.0:29092->29092/tcp   kafka1
e87c9e694a8e   confluentinc/cp-zookeeper:7.3.2   "/etc/confluent/dock…"   14 seconds ago   Up 11 seconds   2888/tcp, 0.0.0.0:2181->2181/tcp, 3888/tcp         zoo1
```

**Interact with our container:** Now let's publish an event, for that we will have to enter the container
```bash
docker exec -it kafka1 bash 
```

Folders containing Kafka files are inside the directoryopb -> bitnami -> kafka -> bin:
```bash
/opt/bitnami/kafka/bin ou /opt/bin
```

After following the path of the folders, we will see all the kafka config files
```bash
!@1ba279a1703c:/opt/bitnami/kafka/bin$ ls
connect-distributed.sh        kafka-configs.sh             kafka-delete-records.sh   kafka-mirror-maker.sh                kafka-server-start.sh               kafka-verifiable-producer.sh     zookeeper-shell.sh
connect-mirror-maker.sh       kafka-console-consumer.sh    kafka-dump-log.sh         kafka-preferred-replica-election.sh  kafka-server-stop.sh                trogdor.sh
connect-standalone.sh         kafka-console-producer.sh    kafka-features.sh         kafka-producer-perf-test.sh          kafka-storage.sh                    windows
kafka-acls.sh                 kafka-consumer-groups.sh     kafka-leader-election.sh  kafka-reassign-partitions.sh         kafka-streams-application-reset.sh  zookeeper-security-migration.sh
kafka-broker-api-versions.sh  kafka-consumer-perf-test.sh  kafka-log-dirs.sh         kafka-replica-verification.sh        kafka-topics.sh                     zookeeper-server-start.sh
kafka-cluster.sh              kafka-delegation-tokens.sh   kafka-metadata-shell.sh   kafka-run-class.sh                   kafka-verifiable-consumer.sh        zookeeper-server-stop.sh
```

### Basic commands for using Kafka

**Criate topic**
```
./kafka-topics.sh --create --topic test-topic --replication-factor 1 --partitions 4 --bootstrap-server localhost:9092
```

**Instantiate a console for the producer**
```
./kafka-console-producer.sh --broker-list localhost:9092 --topic test-topic
```

**Instantiate a console for the Consumer**

Inside the container:
```
./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test-topic --from-beginning
./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test-topic --group <group-name>
```

Command prompt:
```
docker exec --interactive kafka1 kafka-console-consumer --bootstrap-server kafka1:19092 --topic transaction-v1 --from-beginning
```

**List topics**
```
./kafka-topics --bootstrap-server localhost:9092 --list
```

**Delete topics**
```
./kafka-topics.sh --bootstrap-server localhost:9092 --delete --topic test-topic
```

**Check consumer groups**

Inside the container:
```
./kafka-consumer-groups.sh --bootstrap-server localhost:9092 --list
```
**Command prompt:**
```
docker exec --interactive kafka1 kafka-consumer-groups --bootstrap-server kafka1:19092 --list
```

## OpenApi
**Important links**

- https://springdoc.org/
- https://www.baeldung.com/spring-rest-openapi-documentation

