#!/bin/bash

mvn clean package assembly:single

docker build -f Dockerfile-danger -t dangerconsumer:0.0.1 .
