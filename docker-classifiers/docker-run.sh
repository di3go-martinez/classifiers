
. utils.def

tag=$(findVersion)

docker run --rm --detach \
    -p ${1:-8080}:8080 \
    --name classifiers-service-$tag \
    --volumes-from classifiers-service-data-$tag \
    classifiers-service:$tag

echo "^C para cortar el log sin salir del servicio" && sleep 5s

docker logs -f classifiers-service-$tag
