#!/bin/bash
cd /home/ec2-user/proyecto_monicovid
docker-compose build --no-cache
docker-compose up -d