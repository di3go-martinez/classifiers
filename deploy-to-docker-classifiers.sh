#!/bin/bash 

#copio jar+deps y la db
jar=$(ls jclassifiers/build/libs/jclassifiers-*.jar -tr | tail -n 1)
echo "Deploying jclassifier $jar"
cp $jar docker-classifiers/classifiers/classifiers.jar

#armo el script R con todas las funciones necesarias
cp rclassifiers/src/dependencies.r docker-classifiers/rclassifiers/dependencies.r
cat rclassifiers/src/filters/* rclassifiers/src/functions/* > docker-classifiers/rclassifiers/main.r

