#!/usr/bin/env bash

mvn clean package

echo 'Copy files...'

scp ssh -i "sprin.pem" ubuntu@ec2-18-237-111-160.us-west-2.compute.amazonaws.com

echo 'Restart server...'

ssh -i "sprin.pem" ubuntu@ec2-18-237-111-160.us-west-2.compute.amazonaws.com<< EOF

EOF

#scp "sprin.pem" ubuntu@ec2-18-237-111-160.us-west-2.compute.amazonaws.com:~/files