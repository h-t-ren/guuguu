echo path is: %PATH%
set path1=%cd%
cd ./ws-domain
call mvn clean install
cd %path1%
cd ./domain
call mvn clean install
cd %path1%
cd ./service
call mvn -Dmaven.test.skip=true clean install
cd %path1%
cd ./rest
call mvn clean install
call mvn tomcat7:run
