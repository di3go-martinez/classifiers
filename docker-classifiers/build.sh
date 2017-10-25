#!/bin/bash

. utils.def

docker build -t classifiers-service:$(findVersion) .
