
```
paymentservice
├─ .gitignore
├─ gradle
│  └─ wrapper
│     ├─ gradle-wrapper.jar
│     └─ gradle-wrapper.properties
├─ gradlew
├─ gradlew.bat
└─ src
   ├─ main
   │  ├─ java
   │  │  └─ com
   │  │     └─ pplbo
   │  │        └─ ecommerce
   │  │           └─ paymentservice
   │  │              ├─ config
   │  │              │  └─ KafkaConfig.java
   │  │              ├─ controller
   │  │              │  └─ PaymentController.java
   │  │              ├─ dto
   │  │              │  ├─ PaymentRequest.java
   │  │              │  └─ PaymentResponse.java
   │  │              ├─ HelloWorldController.java
   │  │              ├─ kafka
   │  │              │  └─ PaymentResponseProducer.java
   │  │              ├─ model
   │  │              │  └─ Payment.java
   │  │              ├─ PaymentserviceApplication.java
   │  │              ├─ repository
   │  │              │  └─ PaymentRepository.java
   │  │              └─ service
   │  │                 └─ PaymentService.java
   │  └─ resources
   │     └─ application.properties
   └─ test
      └─ java
         └─ com
            └─ pplbo
               └─ ecommerce
                  └─ paymentservice
                     └─ PaymentserviceApplicationTests.java

```
```
payment-service
├─ .gitignore
├─ docker
├─ docker-compose.yml
├─ Dockerfile
├─ gradle
│  └─ wrapper
│     ├─ gradle-wrapper.jar
│     └─ gradle-wrapper.properties
├─ gradlew
├─ gradlew.bat
├─ README.md
└─ src
   ├─ main
   │  ├─ java
   │  │  └─ com
   │  │     └─ pplbo
   │  │        └─ ecommerce
   │  │           └─ paymentservice
   │  │              ├─ config
   │  │              │  ├─ KafkaPaymentConsumerConfig.java
   │  │              │  └─ KafkaPaymentProducerConfig.java
   │  │              ├─ controller
   │  │              │  └─ PaymentController.java
   │  │              ├─ dto
   │  │              │  ├─ PaymentRequest.java
   │  │              │  └─ PaymentResponse.java
   │  │              ├─ event
   │  │              │  └─ ValidatePayment.java
   │  │              ├─ model
   │  │              │  └─ Payment.java
   │  │              ├─ PaymentserviceApplication.java
   │  │              ├─ repository
   │  │              │  └─ PaymentRepository.java
   │  │              └─ service
   │  │                 ├─ PaymentConsumerService.java
   │  │                 ├─ PaymentProducerService.java
   │  │                 └─ PaymentService.java
   │  └─ resources
   │     ├─ application.properties
   │     └─ db
   │        └─ migration
   │           └─ V1__init.sql
   └─ test

```