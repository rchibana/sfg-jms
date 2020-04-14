# SFG-JMS

JMS example project

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- Java 8+
```
https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
```
- ArtemisMQ
```
https://github.com/vromero/activemq-artemis-docker
```

##### Running the project 

- Execute the following command to start ArtemisMQ:
```
docker run -it --rm -p 8161:8161 -p 61616:61616 vromero/activemq-artemis
```

- Execute the following command to start the application:
```
$ ./gradlew bootRun
```

## Authors

* [Rodrigo Chibana](http://github.com/rchibana)