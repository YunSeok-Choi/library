# 실행 설명서

<aside>
💡 도커로 실행하는 것을 권장 합니다. (Node Exporter 제외)
</aside>
<br> </br>

# 사용 서비스

## 백엔드

### Jdk17

### Spring boot

### Spring Data JPA

### Query-dsl

<br> </br>

## DB

### MySQL

### Redis

<br> </br>

## Monitoring

### Prometheus

### grafana

### Loki

<br> </br>

## Infra

### AWS EC2

### Ubuntu 20.04.6 LTS

### Docker

### Docker compose

### Node Exporter

<br> </br>

# 프로젝트 설정

## 백엔드 설정 파일 (application.yml)

```yaml
server:
  port: 8080

spring:
  application:
    name: local

  servlet:
    multipart:
      max-file-size: 25MB
      max-request-size: 25MB

  jpa:
    open-in-view: false
    hibernate:
      ddl-auto: update
    properties:
      hibernate:
        order_updates: true
        order_inserts: true
        default_batch_fetch_size: 50
        show_sql: true
        format_sql: true
        dialect: org.hibernate.dialect.MySQL8Dialect
        jdbc:
          batch_versioned_data: true
          batch_size: 5000
          time_zone: Asia/Seoul

  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://mysql:3306/library?serverTimezone=Asia/Seoul
    username: mysql
    password: 1234
  sql:
    init:
      mode: always

  data:
    web:
      pageable:
        max-page-size: 2000
        default-page-size: 10
    redis:
      host: redis
      port: 6379

management:
  metrics:
    distribution:
      percentiles-histogram:
        http:
          server:
            requests: true
  endpoints:
    web:
      exposure:
        include: health, prometheus, metrics, info
  health:
    defaults:
      enabled: true
```

## 프로메테우스 설정 파일(prometheus.yml)

```yaml
global:
  scrape_interval: 15s

scrape_configs:
  - job_name: prometheus

    static_configs:
      - targets: ["prometheus:9090"]

  - job_name: library
    metrics_path: "/actuator/prometheus"
    static_configs:
      - targets: ["backend:8080"]

  - job_name: node
    static_configs:
      - targets: ["host.docker.internal:9100"]
```

<br> </br>

# 배포 설정

## 도커 컴포즈 파일(docker-compose.yml)

```yaml
services:
  redis:
    image: redis
    container_name: redis
    restart: unless-stopped
    ports:
      - "6379:6379"
    volumes:
      - ./redis.conf:/usr/local/etc/redis/redis.conf
    command: redis-server /usr/local/etc/redis/redis.conf
    networks:
      - webnet

  mysql:
    image: mysql
    container_name: mysql
    environment:
      MYSQL_ROOT_PASSWORD: 1234
      MYSQL_USER: root
      MYSQL_PASSWORD: 1234
      TZ: Asia/Seoul
    ports:
      - "3306:3306"
    volumes:
      - ./mysql/data:/var/lib
      - ./mysql/dump:/var/lib/mysql
    networks:
      - webnet

  backend:
    image: yungsix/library
    container_name: backend
    environment:
      TZ: Asia/Seoul
      SPRING_DATASOURCE_HIKARI_JDBC-URL: jdbc:mysql://mysql:3306/library?serverTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8&useSSL=false&allowPublicKeyRetrieval=true
      SPRING_DATASOURCE_HIKARI_USERNAME: root
      SPRING_DATASOURCE_HIKARI_PASSWORD: 1234
    ports:
      - "8080:8080"
    networks:
      - webnet
    depends_on:
      - mysql
      - redis

  prometheus:
    image: prom/prometheus
    container_name: prometheus
    volumes:
      - ./prometheus.yml:/etc/prometheus/prometheus.yml
    command:
      - "--config.file=/etc/prometheus/prometheus.yml"
    ports:
      - "9090:9090"
    networks:
      - webnet

  grafana:
    image: grafana/grafana
    container_name: grafana
    entrypoint:
      - sh
      - -euc
      - |
        mkdir -p /etc/grafana/provisioning/datasources
        cat <<EOF > /etc/grafana/provisioning/datasources/ds.yaml
        apiVersion: 1
        datasources:
        - name: Loki
          type: loki
          access: proxy
          orgId: 1
          url: http://loki:3100
          basicAuth: false
          isDefault: true
          version: 1
          editable: false
        EOF
        /run.sh
    ports:
      - "3000:3000"
    depends_on:
      - prometheus
    networks:
      - webnet

  loki:
    container_name: loki
    image: grafana/loki
    ports:
      - "3100:3100"
    networks:
      - webnet
```

<br> </br>

# 서비스 실행

mysql에는 library 스키마가 존재해야 합니다.

```bash
# 해당 docker-compose.yml 경로가 있는곳에서
docker compose up -d
```
