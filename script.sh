#!/usr/bin/env bash
mvn clean install
# output path = sierpinski_dataset.csv
# number of levels = 9
# number of points = 100000
java -jar target/SierpinskiGenerator-1.0-SNAPSHOT.jar sierpinski_dataset.csv 100000 random
