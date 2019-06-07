# Container Streams

Contains all the work I did for my work experience week at IBM

## Prerequisites to running

- You will need a running Zookeper and a running Kafka server, locally on your machine. Available [here](https://kafka.apache.org/downloads)
- You will also need Maven installed. Available [here](https://maven.apache.org/download.cgi)

## How to run

- run `mvn package` to compile all the code
- run `mvn exec:java -Dexec.mainClass=shipping.<ClassName>` to run the class you want to run. Can be one of:
  - ProducerContainers (Produces container messages)
  - ShippingStreams (Streams from container messages to produce either warning messages or danger messages)
  - ConsumerWarning (Consumes messages from warning topic)
  - ConsumerDanger (Consumes messages from danger topic)
  
