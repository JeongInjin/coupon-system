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
```

요구사항 정의
```kotlin
선착순 100명에게 할인쿠폰을 제공하는 이벤트를 진행하고자 한다.

이 이벤트는 아래와 같은 조건을 만족하여야 한다.
- 선착순 100명에게만 지급되어야한다.
- 101개 이상이 지급되면 안된다.
- 순간적으로 몰리는 트래픽을 버틸 수 있어야합니다.
```
