#!/bin/bash


: ${server?"environment var server is not defined"}

#FIXME parametrizar bien el server!
(cd classifiers-web && sed -i "s/localhost\:8080/$server/g" src/App.js && npx npm-install-if-needed && npm run build)

static_dir=jclassifiers/src/main/resources/static
rm -rf $static_dir

mv classifiers-web/build $static_dir

(cd jclassifiers && ./gradlew build )

#copio apps
jar=$(ls jclassifiers/build/libs/jclassifiers-*.jar -tr | tail -n 1)
echo "Deploying jclassifier $jar"
mkdir -p docker-classifiers/classifiers
cp $jar docker-classifiers/classifiers/classifiers.jar

unzip -cq $jar META-INF/MANIFEST.MF | grep Version

#armo el script R con todas las funciones necesarias
cp rclassifiers/src/dependencies.r docker-classifiers/rclassifiers/dependencies.r
cat rclassifiers/src/filters/* rclassifiers/src/functions/* > docker-classifiers/rclassifiers/main.r

