# User Service

This is the backend user service for the [Yarn](https://github.com/Awilg/yarn-android) mobile app. 
It is a fully async/reactive service written entirely in kotlin and uses the 
[Ktor framework](https://github.com/ktorio/ktor). The underlying server is Netty. The backing datastore is
 MongoDB via the kotlin [KMongo toolkit](https://github.com/ktorio/ktor). 

## Setup
#### Required
- docker 
- docker-compose

Clone the repository as normal and run the following
```bash
./gradlew build && docker-compose up --build
```

## Usage

```bash
curl localhost:8080/
HELLO, WORLD!
```


