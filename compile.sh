#!/bin/bash

mkdir bin
kotlinc ./src -include-runtime -d bin/app.jar