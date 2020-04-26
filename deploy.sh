#!/usr/bin/env bash

mvn clean package
ssh -i "sprin.pem" ubuntu@ec2-18-237-111-160.us-west-2.compute.amazonaws.com<< EOF
rm accessing-data-mysql-1.0-SNAPSHOT.jar
exit
EOF
echo 'Copy files...'

scp -i "sprin.pem" "C:/Users/tonis/IdeaProjects/springTest/target/accessing-data-mysql-1.0-SNAPSHOT.jar" ubuntu@ec2-52-39-205-163.us-west-2.compute.amazonaws.com:~/

echo 'Restart server...'

ssh -i "sprin.pem" ubuntu@ec2-52-39-205-163.us-west-2.compute.amazonaws.com<< EOF
pgrep java | xargs kill -9
java -jar accessing-data-mysql-1.0-SNAPSHOT.jar
exit
EOF

#scp "sprin.pem" ubuntu@ec2-18-237-111-160.us-west-2.compute.amazonaws.com:~/files
#java -jar accessing-data-mysql-1.0-SNAPSHOT.jar
# sudo nano /etc/nginx/sites-enabled/default
#sudo systemctl reload nginx