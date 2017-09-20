#!/usr/bin/env bash
mvn clean compile assembly:single
# output path = sierpinski_dataset.csv
# number of levels = 9
# number of points = 100000
# mode = fixed/random
java -jar target/SierpinskiGenerator-1.0-SNAPSHOT-jar-with-dependencies.jar sierpinski_dataset.csv 100000 random
