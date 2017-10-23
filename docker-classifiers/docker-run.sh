#TODO hacer base de datos persistente
    #-v $( readlink -f classifiers/db/):/srv/classifiers/db/ \
docker run --rm --detach \
    -p 8080:8080 \
    --name classifiers-service \
    --volumes-from classifiers-service-data \
    classifiers-service

echo "^C para cortar el log sin salir del servicio" && sleep 5s

docker logs -f classifiers-service
