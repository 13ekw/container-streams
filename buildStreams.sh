#!/bin/bash

mvn clean package assembly:single

docker build -f Dockerfile-streams -t kafkastreams:0.1.2 .