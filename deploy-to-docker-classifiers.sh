#!/bin/bash 


(cd classifiers-web && npm run build)

rm -rf jclassifiers/src/main/resources/static
mv classifiers-web/build jclassifiers/src/main/resources/static

(cd jclassifiers && ./gradlew build )

#copio apps
jar=$(ls jclassifiers/build/libs/jclassifiers-*.jar -tr | tail -n 1)
echo "Deploying jclassifier $jar"
cp $jar docker-classifiers/classifiers/classifiers.jar

unzip -cq $jar META-INF/MANIFEST.MF | grep Version

#armo el script R con todas las funciones necesarias
cp rclassifiers/src/dependencies.r docker-classifiers/rclassifiers/dependencies.r
cat rclassifiers/src/filters/* rclassifiers/src/functions/* > docker-classifiers/rclassifiers/main.r

