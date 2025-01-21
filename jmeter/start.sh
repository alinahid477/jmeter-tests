#!/bin/bash

name=$1
forcebuild=$2
port=$3;
if [[ $name == "forcebuild" ]]
then
    name=''
    forcebuild='forcebuild'
fi

containername=$name
if [[ $containername == *.* ]] ; then
    name="${containername%.*}"
fi

dockerfilename="Dockerfile"
name="jmeter-container"
containername="jmeter-container"


isexists=$(docker images | grep "\<$name\>")
if [[ -z $isexists || $forcebuild == "forcebuild" ]]
then
    docker build . -f $dockerfilename -t $name
fi
if [[ $2 != 'forcebuild' ]]
then
    port=$2
fi

printf "\n$name, $port\n"
docker run -it --rm -v ${PWD}/ww_mongo_test.jmx:/opt/ww_mongo_test.jmx -v ${PWD}/output/x:/opt/apache-jmeter-5.6.3/output --name $containername $name