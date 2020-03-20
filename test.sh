#!/bin/bash

function test() {
    test_name=$1
    params=$2
    expected_status=$3
    output=$(./run.sh $params)
    actual_status=$?

    if [ $actual_status -eq $expected_status ]
    then
    echo -e "$test_name passed \napp.jar $params \n"
    else
    echo -e "$test_name failed \napp.jar $params \nexpected: $expected_status \nactual: $actual_status \n"
    fi
}

test "T1.1" "" 1
test "T1.2" "-h" 1
test "T1.3" "-q"  0
test "Т1.4" "12345"  0
test "T1.5" "-res A.B 12345"  0
test "T2.1" "-login vasya -pass 123"  0
test "T2.2" "-login VASYA -pass 123" 2
test "T2.3" "-login asd -pass 123"  3
test "T2.4" "-login admin -pass 123"  4
test "T3.1" "-login vasya -pass 123 -res A -role READ"  0
test "T3.2" "-login vasya -pass 123 -res A -role DELETE"  5
test "T3.3" "-login vasya -pass 123 -res A -role WRITE"  6
test "T3.4" "-login vasya -pass 123 -res A.B -role READ"  0
test "T3.5" "-login vasya -pass 0000 -res A.B -role DELETE"  4
test "T3.6" "-login admin -pass admin -res A -role EXECUTE"  6
test "T3.7" "-login admin -pass admin -res A.A -role WRITE"  6
test "T4.1" "-login vasya -pass 123 -res A -role READ -ds 2020-03-10 -de 2020-03-11 -vol 100"  0
test "T4.2" "-login vasya -pass 123 -res A -role READ -ds 2020.03.10 -de 2020.03.11 -vol 100"  7
test "T4.3" "-login vasya -pass 123 -res A -role READ -ds 2020-03-10 -de 2020-03-11 -vol aaaa"  7
test "T4.4" "-login vasya -pass 123 -res A -role EXECUTE -ds 01.02.3012 -de 01.02.2030 -vol aaa"  6
test "T4.5" "-login vasya -pass 123 -res A -role READ -ds 2020-03-11 -de 2020-03-10 -vol 100"  7