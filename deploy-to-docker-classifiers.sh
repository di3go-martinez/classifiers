#!/bin/bash -x

#copio jar+deps y la db
cp classifiers/build/libs/classifiers-0.0.1-SNAPSHOT.jar docker-classifiers/classifiers/classifiers.jar
cp -r classifiers/db docker-classifiers/classifiers/

#armo el script R con todas las funciones necesarias
cat rclassifiers/src/filters/* rclassifiers/src/functions/* > docker-classifiers/rclassifiers/main.r

