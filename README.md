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
  
## Explanation of Shipping Package
This was my first time coding in java and using the Eclipse IDE. It was a week of many firsts but I had so much help from the amazing Kafka team especailly Jordan, Kate and Dale. As well as lots of coding I thorougly enjoyed being shown around IBM Hursley including it's amazing grounds and mainframe room.
The Kafka team had made a demo to show Kafka's capabilities which could be shown to tech sellers and customers to explain and simplify the product. The demo involves monitoring various properties of containers on container ships to be able to flag up any issues. Initally we wanted to simplify it to just looking at temperatures to make warning and danger messages.
Rather than trying to incorperate it into the demo straight off I wrote my own producer which produces randomly generated temperatures to containers and ships. This information was sent to a topic called "streams-shipping-input". 
Then I wrote a Kafka Streams App ("ShippingStreams") which reads from the "streams-shipping-input" topic and transforms the json data to set the key as the shipID and value as the temperature, container ID, timestamp and warning message dependent on weather the temperaure is at a warning level or dangerous level. These key-value pairs are then written to the topic called "streams-shipping-warning-output" if temperatures are between 75-90 degrees or to "streams-shipping-danger-output" if temperatures are above 90 degrees. 
I also wrote two consumers "ConsumerWarning" and "ConsumerDanger" which consume only warning or danger key-value pairs from the relevant topic.
The output will be in the following format:
> ShipA - Problem {

>> container: Container1

>> temperature: 89 C

>> time/date: 2019.06.08.13.22.38

>> message: WARNING temperature approaching danger level

>> }
