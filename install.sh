#!/bin/sh 

CURRENT_PATH=${PWD}
#echo "current path ${CURRENT_PATH}"

#echo "the parameter is $1"

cd ${CURRENT_PATH}/ws-domain
mvn clean install

cd ${CURRENT_PATH}/domain
mvn clean install

cd ${CURRENT_PATH}/service

if [ $1 -eq 100 ]
then
mvn clean install
else
mvn -Dmaven.test.skip=true clean install
fi

cd ${CURRENT_PATH}/rest
mvn clean install&&mvn tomcat7:run
