#!/bin/bash
MAX_POOL_SIZE=2
# 1 sec connection timeout
MAX_WAIT=10
MINIMUM=1
$PAYARA_HOME/bin/asadmin start-domain
$PAYARA_HOME/bin/asadmin delete-jdbc-connection-pool --cascade=true bulkheads
$PAYARA_HOME/bin/asadmin create-jdbc-connection-pool --steadypoolsize ${MINIMUM} --maxwait ${MAX_WAIT} --maxpoolsize ${MAX_POOL_SIZE}  --port 4848 --datasourceclassname org.apache.derby.jdbc.ClientDataSource --restype javax.sql.ConnectionPoolDataSource --property "DatabaseName=sample:PortNumber=1527:serverName=localhost:User=app:Password=app:URL=jdbc\\:derby\\://localhost\\:1527/sample" bulkheads
$PAYARA_HOME/bin/asadmin create-jdbc-resource --port 4848 --connectionpoolid bulkheads jdbc/bulkheads
$PAYARA_HOME/bin/asadmin ping-connection-pool bulkheads
$PAYARA_HOME/bin/asadmin set resources.jdbc-connection-pool.SamplePool.steady-pool-size=${MINIMUM}
$PAYARA_HOME/bin/asadmin set resources.jdbc-connection-pool.SamplePool.max-wait-time-in-millis=${MAX_WAIT}
$PAYARA_HOME/bin/asadmin set resources.jdbc-connection-pool.SamplePool.max-pool-size=${MAX_POOL_SIZE}

