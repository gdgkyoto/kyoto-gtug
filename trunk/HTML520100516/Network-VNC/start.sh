CLASSPATH=build:lib/servlet-api-2.5.jar
CLASSPATH=$CLASSPATH:lib/jetty-websocket-7.0.2.v20100331.jar
CLASSPATH=$CLASSPATH:lib/jetty-server-7.0.2.v20100331.jar
CLASSPATH=$CLASSPATH:lib/jetty-util-7.0.2.v20100331.jar
CLASSPATH=$CLASSPATH:lib/jetty-http-7.0.2.v20100331.jar
CLASSPATH=$CLASSPATH:lib/jetty-io-7.0.2.v20100331.jar
CLASSPATH=$CLASSPATH:lib/jetty-servlet-7.0.2.v20100331.jar
CLASSPATH=$CLASSPATH:lib/jetty-security-7.0.2.v20100331.jar
CLASSPATH=$CLASSPATH:lib/jetty-continuation-7.0.2.v20100331.jar
CLASSPATH=$CLASSPATH:lib/commons-io-1.4.jar
CLASSPATH=$CLASSPATH:lib/commons-codec-1.4.jar


java -classpath $CLASSPATH org.kyotogtug.vnc.WebServer
