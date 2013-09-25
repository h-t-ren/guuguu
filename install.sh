#!/bin/sh 

CURRENT_PATH=${PWD}
echo 'current path' ${CURRENT_PATH}
cd ${CURRENT_PATH}/ws-domain
mvn clean install

cd ${CURRENT_PATH}/domain
mvn clean install

cd ${CURRENT_PATH}/service
call mvn -Dmaven.test.skip=true clean install

cd ${CURRENT_PATH}/rest
mvn clean install&&mvn tomcat7:run
