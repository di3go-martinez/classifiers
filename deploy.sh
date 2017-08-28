#!/bin/bash -x

cp classifiers/build/libs/classifiers-0.0.1-SNAPSHOT.jar docker-classifiers/classifiers/classifiers.jar
cp -r classifiers/db docker-classifiers/classifiers/

cat rclassifiers/src/filters/* rclassifiers/src/functions/* > docker-classifiers/rclassifiers/main.r

