#!/bin/bash

mkdir -p bin
kotlinc -classpath "./lib/kotlinx-cli-0.2.1.jar;lib/h2-1.4.200.jar" ./src -include-runtime -d bin/app.jar