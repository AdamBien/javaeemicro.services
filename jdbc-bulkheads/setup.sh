#!/bin/bash

# Create Workshops
curl -i -XPOST -d'bootstrap' http://localhost:8080/jdbc-bulkheads/resources/workshops
curl -i -XPOST -d'effective' http://localhost:8080/jdbc-bulkheads/resources/workshops
curl -i -XPOST -d'architecture' http://localhost:8080/jdbc-bulkheads/resources/workshops
curl -i -XPOST -d'microservices' http://localhost:8080/jdbc-bulkheads/resources/workshops

curl -i http://localhost:8080/jdbc-bulkheads/resources/workshops

# Create Screencasts

curl -i -XPOST -d'essential maven' http://localhost:8080/jdbc-bulkheads/resources/screencasts
curl -i -XPOST -d'JAX-RS' http://localhost:8080/jdbc-bulkheads/resources/screencasts
curl -i -XPOST -d'Java EE rocks' http://localhost:8080/jdbc-bulkheads/resources/screencasts
curl -i -XPOST -d'BCE productivity' http://localhost:8080/jdbc-bulkheads/resources/screencasts

curl -i http://localhost:8080/jdbc-bulkheads/resources/screencasts
