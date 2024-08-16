[English👈](README.en-US.md)

# Superonline Sales System Microservice Project

 Bu repo bir ürün kayıt sistemi, redis cache üzerinden listelenen katalog, ve sipariş sistemi üzerine orkestre olmuş genel bir yapı içerir. Java programlama dili kullanılarak microservice mimaride servisler içerisinde layered architecture uygulanmıştır. 


## Genel işleyiş

![senario.png](docs%2Fsenario.png)

## Veritabanı Diagramı

![sol-microserviceDb.png](docs%2Fsol-microserviceDb.png)

## Microservice Yapısı

![SolMSFlowDiagram.png](docs%2FSolMSFlowDiagram.png)

## Kullanılan Teknoloji ve Yapılar

- Saga Orchestration Pattern

- Katmanlı mimari servis yapıları

- Request-Response Tasarım Deseni

- @Data` anotasyonu yerine `@Getter`, `@Setter`, ... gibi anotasyonlar kullanımı ile modülerlik ve esneklik

- Immutable veriler için record tipi kullanımı

- Asenkron iletişim için servisler arasında Message Broker - RabbitMQ

- Rest Controllerlar üzerinden senkron iletişim için servisler arasında - Spring Feign Client

- Mock ödeme servisi ile sipariş servisi arasında gRPC teknolojisi ile güçlü ve hızlı iletişim

- Stok sistemi concurrency yapısı

- Redis Cache üzerinden listelenen ürünler sayesinde hızlı okuma işlemi ve yük optimizasyonu

- Transactional yönetim ve rollback yapısı

- Bildirim servisi üzerinden sipariş durumu kontrolü noSql veritabanı sayesinde hızlı okuma işlemleri

- Spring Config Server üzerinden CI/CD sürecine destek kolay servis ayar yönetimi

- Spring Eureka Server Discovery Client

## Planlanan Geliştirmeler

- Stock ve Catalog Service arasında GraphQl ile senkron iletişim

- Örnek kullanım için notification service üzerinde Soap Endpoint yapısı

- Optimize şekilde dockerize edilmiş proje yapısı, development ve production ortamlarının ayrı şekilde yönetimi

- Identity Service Security Mechanism


## Geliştirme

- [Conventional Commits](https://gist.github.com/joshbuchea/6f47e86d2510bce28f8e7f42ae84c716)
- Package isimleri Oracle dokümanlarına göre lower_snake_case formatında olmalıdır.
- PostgreSQL veritabanı kullanılan servislerde topluluğa göre tablo ve sütun isimleri lower_snake_case formatında
  olmalıdır.
- `var` anahtar kelimesi mümkün olduğu sürece tercih edilmemelidir. Bunun yerine direkt tip belirtilmelidir. Bunun
  sebebi ise `var` anahtar kelimesinin okunabilirliği azaltmasıdır.
- Rest API'lerin dünya genelinde kabul görmüş standartları vardır. Şu linkteki makaleler ile bunlara hakim
  olabilirsiniz: [REST API URI Naming Conventions and Best Practices](https://restfulapi.net/resource-naming/), [Best practices for REST API design](https://stackoverflow.blog/2020/03/02/best-practices-for-rest-api-design/)