#!/bin/bash

mvn clean package assembly:single

docker build -f Dockerfile-warning -t warningconsumer:0.0.1 .