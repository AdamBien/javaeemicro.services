ab -c 5 -n 10000 http://localhost:8080/jdbc-bulkheads/resources/workshops
ab -c 5 -n 10000 http://localhost:8080/jdbc-bulkheads/resources/screencasts
curl -i http://localhost:8080/jdbc-bulkheads/resources/screencasts
curl -i http://localhost:8080/jdbc-bulkheads/resources/workshops