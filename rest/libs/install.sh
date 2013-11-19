#!/bin/bash

mvn install:install-file -DgroupId=tecent.qq -DartifactId=qq-sdk -Dversion=1.0 -Dpackaging=jar -Dfile=Sdk4J.jar -DgeneratePom=true