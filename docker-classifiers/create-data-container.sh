
docker run --entrypoint echo \
           --name classifiers-service-data \
           --volume /tmp/dbp/ \
           classifiers-service data
