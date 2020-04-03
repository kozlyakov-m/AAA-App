#!/bin/bash

if [[ "$OSTYPE" == "msys" ]]; then
  params="./lib/kotlinx-cli-0.2.1.jar;lib/h2-1.4.200.jar;bin/app.jar"
else
  params="./lib/kotlinx-cli-0.2.1.jar:lib/h2-1.4.200.jar:bin/app.jar"
fi

# shellcheck disable=SC2068
java -classpath $params com.dinosaur.app.MainKt $@
