
# Estrutura projeto

Open API 3

https://springdoc.org/
https://www.baeldung.com/spring-rest-openapi-documentation
# Dia 001

Apache Kafka surgiu como uma alternativa eficiente para o consumo de publicação de dados em larga escala.
Mais do que uma  ferramenta de mensageria, definindo e permitindo que sistemas distribuídos possam se comunicar por
meio de troca de eventos, ela nos permite gerenciar todo seu contexto de message por um Broker(servidor/módulo de mensagens).

Toda comunicação das mensagens e seu broker é gerenciada pelo Zookeeper, uma ferramenta que
direciona e organiza os fluxos de dados,  salvando, recuperando e manipulando.

#Introdução

Para iniciar nosso entendimento e validação, irei criar um arquivo Dockerfile, contendo uma imagem do Kafka e o
Zookeeper. Dentro da pasta voce encontrara o arquivo [**docker-compose-kafka**](./dev/docker-compose-m1-single-broker.yml).

Execute no terminal o comando a seguir para subir localmente o kafka:
```bash
docker-compose -f docker-compose-m1-single-broker.yml up -d 
```

Para verificar se ele está em execução:

```bash
$ docker ps -a
CONTAINER ID   IMAGE                             COMMAND                  CREATED          STATUS          PORTS                                              NAMES
64cf60b6442f   confluentinc/cp-kafka:7.3.2       "/etc/confluent/dock…"   12 seconds ago   Up 10 seconds   0.0.0.0:9092->9092/tcp, 0.0.0.0:29092->29092/tcp   kafka1
e87c9e694a8e   confluentinc/cp-zookeeper:7.3.2   "/etc/confluent/dock…"   14 seconds ago   Up 11 seconds   2888/tcp, 0.0.0.0:2181->2181/tcp, 3888/tcp         zoo1

```

Agora vamos interagir com nosso container e plublicar um evento, para isso teremos que entrar no container:
```bash
docker exec -it kafka1 bash 
```

As pastas contendo os arquivos do Kafka estão dentro do diretorio opb -> bitnami -> kafka -> bin:
```bash
/opt/bitnami/kafka/bin ou /opt/bin
```

Ao seguir o caminho das pastas veremos todos os arquivos de configs do kafka
```bash
!@1ba279a1703c:/opt/bitnami/kafka/bin$ ls
connect-distributed.sh        kafka-configs.sh             kafka-delete-records.sh   kafka-mirror-maker.sh                kafka-server-start.sh               kafka-verifiable-producer.sh     zookeeper-shell.sh
connect-mirror-maker.sh       kafka-console-consumer.sh    kafka-dump-log.sh         kafka-preferred-replica-election.sh  kafka-server-stop.sh                trogdor.sh
connect-standalone.sh         kafka-console-producer.sh    kafka-features.sh         kafka-producer-perf-test.sh          kafka-storage.sh                    windows
kafka-acls.sh                 kafka-consumer-groups.sh     kafka-leader-election.sh  kafka-reassign-partitions.sh         kafka-streams-application-reset.sh  zookeeper-security-migration.sh
kafka-broker-api-versions.sh  kafka-consumer-perf-test.sh  kafka-log-dirs.sh         kafka-replica-verification.sh        kafka-topics.sh                     zookeeper-server-start.sh
kafka-cluster.sh              kafka-delegation-tokens.sh   kafka-metadata-shell.sh   kafka-run-class.sh                   kafka-verifiable-consumer.sh        zookeeper-server-stop.sh

```

Pronto, criamos nosso arquivo docker file contendo o kafka e o zookeeper, nas próximas etapas vamos começar a entender
os conceitos do kafka e aplicar usando nosso servidor local.



# Comandos

## Criar topico
./kafka-topics.sh --create --topic test-topic --replication-factor 1 --partitions 4 --bootstrap-server localhost:9092

## Instanciar um console para o producer
./kafka-console-producer.sh --broker-list localhost:9092 --topic test-topic

## Intanciar um consile para o consumer

Dentro co container

./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test-topic --from-beginning
./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test-topic --group <group-name>

Direto:
docker exec --interactive kafka1 kafka-console-consumer --bootstrap-server kafka1:19092 --topic transaction-v1 --from-beginning

## Listar topicos
./kafka-topics --bootstrap-server localhost:9092 --list

## Deletar topicos
./kafka-topics.sh --bootstrap-server localhost:9092 --delete --topic test-topic

## Verificar consumer groups
./kafka-consumer-groups.sh --bootstrap-server localhost:9092 --list

docker exec --interactive kafka1 kafka-consumer-groups --bootstrap-server kafka1:19092 --list



## TODO

Possíveis regras que serão implementadas

O Domínio deve ser de conta corrente e é possível fazer as operações de depósito, saque e saldo conforme as regras abaixo:

Ter estrutura para Cheque Especial.
Validar se o valor de saque não ultrapassa o cheque especial, e se ultrapassar lançar uma critica para o usuário.
Efetuar operação de depósito, informando a conta, valor e data atualizando o saldo.
Efetuar operação de saque, informando a conta, valor e data e atualizando o saldo.
Consulta de saldo a aplicação terá que ter um contrato que informe o saldo do correntista mais o cheque especial por uma data e conta.
Consulta de Extrato mostrar as movimentações por data de saques e depósitos de uma conta corrente nos últimos 15 dias.
Junto do código fonte do projeto da aplicação deverá ser entregue um Dockerfile para instalação.
Suba um arquivo.zip com o projeto da aplicação sem conter a pasta target ou build do maven e gradle respectivamente