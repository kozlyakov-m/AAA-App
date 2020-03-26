#!/bin/bash

mkdir -p bin
kotlinc -classpath ./lib/kotlinx-cli-0.2.1.jar ./src -include-runtime -d bin/app.jar