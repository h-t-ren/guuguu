#!/bin/bash
current_path="$PWD"
echo ${current_path}
cd ${current_path}
cd domain
mvn clean install