# 환경세팅

docker 설치

```jsx
brew install docker
brew link docker

docker version
```

docker mysql 실행 명령어

```kotlin
docker pull mysql
docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=1234 --name mysql mysql
docker ps
docker exec -it mysql bash
```

> docker: no matching manifest for linux/arm64/v8 in the manifest list entries.
오류가 발생하시는분은

docker pull --platform linux/x86_64 mysql

mysql 명령어
```kotlin
mysql -u root -p
create database coupon_example;
use coupon_example;
```

redis 설치
```kotlin
docker pull redis
docker run --name myredis -d -p 6379:6379 redis

docker exec -it f5af56809d01 redis-cli
flushall
```

kafka 설치
```kotlin
docker-compose 가 먼저 설치되어 잇어야 함
docker-compose -v

docker-compose 를 만들 폴더로 이동
mkdir kafka 폴더 생성 후 
vim docker-compose.yml 후에 아래와 같이 복사

version: '2'
services:
zookeeper:
image: wurstmeister/zookeeper
container_name: zookeeper
ports:
- "2181:2181"
kafka:
image: wurstmeister/kafka:2.12-2.5.0
container_name: kafka
ports:
- "9092:9092"
environment:
KAFKA_ADVERTISED_HOST_NAME: 127.0.0.1
KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
volumes:
- /var/run/docker.sock:/var/run/docker.sock
```
카프카 실행
```kotlin
docker-compose up -d
```

카프카 실행 종료
```kotlin
docker-compose down
```

테스트 토픽생성
```yaml
docker exec -it kafka kafka-topics.sh --bootstrap-server localhost:9092 --create --topic testTopic
```

테스트 프로듀서 실행
```yaml
docker exec -it kafka kafka-console-producer.sh --topic testTopic --broker-list 0.0.0.0:9092
```

테스트 컨슈머 실행
```yaml
docker exec -it kafka kafka-console-consumer.sh --topic testTopic --bootstrap-server localhost:9092
```

application 을 위한 토픽생성
```yaml
docker exec -it kafka kafka-topics.sh --bootstrap-server localhost:9092 --create --topic coupon_create
```

application 을 위한 컨슈머 실행   
cli 환경에서 보고싶으면 아래 명령어를, 또는 consumer 모듈의 application을 실행
```yaml
docker exec -it kafka kafka-console-consumer.sh --topic coupon_create --bootstrap-server localhost:9092 --key-deserializer "org.apache.kafka.common.serialization.StringDeserializer" --value-deserializer "org.apache.kafka.common.serialization.LongDeserializer"
```


---


요구사항 정의
```kotlin
선착순 100명에게 할인쿠폰을 제공하는 이벤트를 진행하고자 한다.

이 이벤트는 아래와 같은 조건을 만족하여야 한다.
- 선착순 100명에게만 지급되어야한다.
- 101개 이상이 지급되면 안된다.
- 순간적으로 몰리는 트래픽을 버틸 수 있어야합니다.
```
