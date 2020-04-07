#!/usr/bin/env bash

mvn clean package

echo 'COpy files...'

scp ssh -i "spring.pem" ec2-user@ec2-35-166-196-47.us-west-2.compute.amazonaws.com
