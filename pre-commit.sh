#!/bin/bash

source bash/common.sh

./gradlew test
unit=$?
echo_result "Unit Tests" $unit
