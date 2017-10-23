#/bin/bash

R -e 'pr <<- plumber::plumb("/srv/main.r"); pr$run(host="0.0.0.0", port=8000)' &

java -jar classifiers.jar --spring.profiles.active=production --classifiers.datasource.dbfile=/classifiers/db/classifiers-db
