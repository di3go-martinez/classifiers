#!/bin/bash

. utils.def

msg="data for $(findVersion)"
docker run --entrypoint "echo" \
           --name classifiers-service-data-$(findVersion) \
           classifiers-service:$(findVersion) $msg
