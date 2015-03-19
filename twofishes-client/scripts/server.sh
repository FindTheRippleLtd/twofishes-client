#!/bin/sh
# java -Xms1g -Xmx1g -jar server-assembly-0.84.9.jar --hfile_basepath latest/ --port 5100 --run_http_server true

#some variables make it more flexible
SERVER_ASSEMBLY="server-assembly-0.84.9.jar"
HFILE_BASEPATH="latest/"
PORT="5100"
RUN_HTTP_SERVER="true"

java -Xms1g -Xmx1g -jar $SERVER_ASSEMBLY --hfile_basepath $HFILE_BASEPATH --port $PORT --run_http_server $RUN_HTTP_SERVER
