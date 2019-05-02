#!/bin/bash

## deploy jar to this computer
target_path=$1
jar_package=$2

if [ ! -d ${target_path} ];then
    mkdir -p ${target_path}
fi

cp $jar_package $target_path

cd $target_path

if [ -e pid ];then
    kill -9 `cat pid`
fi

nohup java -jar $jar_package &
echo $! > pid