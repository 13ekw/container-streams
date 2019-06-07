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
  
## Explanation
Ther was a demo to show Kafka's capabilities which could be shown to tech seller and customers to explain and simplify the product. The demo involves monitoring various properties of a container on a container ship to be able to flag up any issues. Initally we wanted to simplify it to just looking at temperatures to make warning and danger messages.
Rather than trying to incorperate it into the demo straight off I wrote my own producer which produces randomly generated temperatures to containers and ships. This information was sent to a topic called "streams-shipping-input". 
Then my Kafka Streams App ("ShippingStreams") which reads from the "streams-shipping-input" topic to start transformations

tbc
