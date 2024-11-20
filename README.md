# portfolio-management

## [Work in progress]

![Java](https://img.shields.io/badge/Java-17-green.svg)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3-green.svg)
![Postgres](https://img.shields.io/badge/Postgres-blue.svg)
![Kafka](https://img.shields.io/badge/Confluent--Kafka-7.3.2%2B-red.svg)
![Redis](https://img.shields.io/badge/Redis-6.0.20%2B-blue.svg)

## Build

```shell
mvn clean install
```

## Run

Start docker containers:

```shell
make start-docker
```

Start Java apps:

```shell
make start-apps
```

With all services up, access:

| Description             | Port/Link                  | Additional Info                          |
|-------------------------|----------------------------|------------------------------------------|
| Postgres                | 5432                       |                                          |
| Postgres UI             | http://localhost:5050      | U: `pgadmin4@pgadmin.org`<br/>P: `admin` |
| Kafka UI                | http://localhost:8080      |                                          |
| Redis UI                | http://localhost:8050      |                                          |
| Grafana UI              | http://localhost:3000      |                                          |
| Keycloak                | http://localhost:9000      |                                          |
| Eureka Service Registry | http://localhost:9900      |                                          |
| Spring Boot Admin       | http://localhost:9800      |                                          |
| Auth Service            | http://localhost:9901/docs |                                          |
| Ticker Service          | http://localhost:9902/docs |                                          |
| Order Service           | http://localhost:9903/docs |                                          |
| App UI                  | http://localhost:9910      | U: `test`<br/>P: `test`                  |

Generate traffic:

```shell
make traffic
```

Stop apps:

```shell
make stop-apps
```

Stop docker containers:

```shell
make stop-docker
```
