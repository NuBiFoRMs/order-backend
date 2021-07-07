# 고객, 주문관리 RESRful Back-End API 개발과제

[![Tag](https://img.shields.io/github/v/tag/nubiforms/order-backend)](https://github.com/NuBiFoRMs/order-backend/releases)
[![Actions Status](https://github.com/NuBiFoRMs/order-backend/workflows/build/badge.svg)](https://github.com/NuBiFoRMs/order-backend/actions)
[![codecov](https://codecov.io/gh/NuBiFoRMs/order-backend/branch/develop/graph/badge.svg?token=CUPAW61JYO)](https://codecov.io/gh/NuBiFoRMs/order-backend)

![Languages Count](https://img.shields.io/github/languages/count/nubiforms/order-backend)
![Languages Top](https://img.shields.io/github/languages/top/nubiforms/order-backend)
[![Issues](https://img.shields.io/github/issues/nubiforms/order-backend)](https://github.com/NuBiFoRMs/order-backend/issues)
[![Issues Closed](https://img.shields.io/github/issues-closed/nubiforms/order-backend)](https://github.com/NuBiFoRMs/order-backend/issues?q=is%3Aissue+is%3Aclosed)
[![Issues PR](https://img.shields.io/github/issues-pr/nubiforms/order-backend)](https://github.com/NuBiFoRMs/order-backend/pulls)
[![Issues PR Closed](https://img.shields.io/github/issues-pr-closed/nubiforms/order-backend)](https://github.com/NuBiFoRMs/order-backend/pulls?q=is%3Apr+is%3Aclosed)
[![Commit Activity](https://img.shields.io/github/commit-activity/w/nubiforms/order-backend)](https://github.com/NuBiFoRMs/order-backend/commits)
[![Last Commit](https://img.shields.io/github/last-commit/nubiforms/order-backend)](https://github.com/NuBiFoRMs/order-backend/commits)

## 개발 프레임워크

* JDK 11
* Gradle
* Spring Boot
* Spring Data JPA
* H2 Database
* Embedded Redis
* Docker
* Docker Compose
* MySQL
* Redis

## Database

`mysql-init-file/mysql.sql` 경로에 테이블 생성 쿼리가 포함되어 있습니다.  
해당 스크립트는 `Docker`를 통해 `MySQL`이 실행될때 자동으로 수행될 수 있도록 `Docker Compose`를 구성하였습니다.  
`mysql` 쓰기 전용 서버와 읽기 전용 서버 `replication` 설정을 쓰기 전용 유저 `write`, 읽기 전용 유저 `read-only` 구성으로 대체 하였습니다.

## 빌드 및 실행 방법

### Gradle Build and Run with `local` profile

H2 Database, Embedded Redis 구성으로 실행

```
git clone https://github.com/NuBiFoRMs/order-backend.git
cd order-backend
gradle build
java -jar -Dspring.profiles.active=local order-backend-1.0.4.jar
```

### Gradle Build and Run with `dev` profile

Docker 컨테이너 `mysql:8.0`, `redis:alpine` 구성으로 실행

```
git clone https://github.com/NuBiFoRMs/order-backend.git
cd order-backend
docker-compose -f docker-compose-dev.yml up -d
gradle build
java -jar -Dspring.profiles.active=local order-backend-1.0.4.jar
```

### Docker Run with `prod` profile

Docker 컨테이너 `mysql:8.0`, `redis:alpine`, `nubiform/order-backend:latest` 구성으로 실행

```
git clone https://github.com/NuBiFoRMs/order-backend.git
cd order-backend
docker-compose up -d
```

## 기타

### Docker Hub

* https://hub.docker.com/r/nubiform/order-backend

### Codecov

* https://app.codecov.io/gh/NuBiFoRMs/order-backend

### Swagger UI

* http://localhost:8080/swagger-ui.html