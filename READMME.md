brew install docker

brew link docker

docker version

docker 실행 후 -> docker pull mysql

docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=1234 --name mysql mysql

docker ps

docker exec -it mysql bash

mysql -u root -p -> 비밀버호 1234 입력

create database coupon_example;

use coupon_example;
