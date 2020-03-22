#!/bin/bash

java -classpath "./lib/kotlinx-cli-0.2.1.jar:bin/app.jar" MainKt $@
# может не работать под linux из-за ;