from trestletech/plumber

#instalo java
RUN apt-get update 
RUN apt-get install -y curl
RUN apt-get install -y openjdk-8-jre && \
  rm -rf /var/lib/apt/lists/
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64

#run ln -s /usr/lib/jvm/java-8-openjdk-amd64 /opt/jre

expose 8080

add classifiers/ /srv/classifiers/
workdir /srv/classifiers/

#Configuración de R 

copy rclassifiers/dependencies.r /srv/
run Rscript /srv/dependencies.r

copy rclassifiers/main.r /srv/main.r
#Ejecuto el servicio de R que ejecuta las funciones
#TODO revisar si es necesario o dejar el default que no define pr como global
#ENTRYPOINT ["R", "-e", "pr <<- plumber::plumb(commandArgs()[4]); pr$run(host='0.0.0.0', port=8000)"]
copy entrypoint.sh /srv/entrypoint.sh
entrypoint /srv/entrypoint.sh
