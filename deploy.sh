#!/bin/bash

## deploy jar to this computer
target_path=$1
jar_file=$2

cp $jar_package $target_path

cd $target_path

if -e pid
then kill -9 `cat pid`
fi

nohup java -jar $jar_package &
echo $! > pid