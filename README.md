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

Create a user:
```
POST http://localhost:8080/user

Content-Type: application/json
Accept: */*

{
    "name": "James"
}
```
Sample response:
```
{
  "key": "5ddc6ffe41383f37d0cb2fdc",
  "name": "James",
  "activeTreasureHunts": null,
  "completedTreasureHunts": null,
  "avatarUrl": null,
  "createdAt": "1574727678011"
}
```

Fetch a user:
```
GET http://localhost:8080/user/5ddc6ffe41383f37d0cb2fdc
```
