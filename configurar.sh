#!/bin/bash

home=$(echo $HOME)
if ! [ -e "${home}/TrabajoUY" ]; then
    mkdir "${home}/TrabajoUY"
fi

if ! [ -e "${home}/TrabajoUY/server.properties" ]; then
    touch "${home}/TrabajoUY/server.properties"
fi

echo "host=$1\nport=$2" > ${home}/TrabajoUY/server.properties
