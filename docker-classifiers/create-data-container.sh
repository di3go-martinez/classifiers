#!/bin/bash

. utils.def

docker run --entrypoint "echo" \
           --name classifiers-service-data-$(findVersion) \
           classifiers-service:$(findVersion) data
