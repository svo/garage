#!/bin/bash

source bash/common.sh

./gradlew ktlintCheck
quality=$?
echo_result "Quality Tests" $quality

./gradlew test
unit=$?
echo_result "Unit Tests" $unit
