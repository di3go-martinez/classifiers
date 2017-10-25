#!/bin/bash

version=${1?"indicar la versión a parar"}
docker stop classifiers-service-$version
