FROM tomcat:9.0.82-jdk17-corretto

RUN mv /usr/local/tomcat/webapps /usr/local/tomcat/webapps2
RUN mv /usr/local/tomcat/webapps.dist /usr/local/tomcat/webapps

ADD target/michaelspetitions.war /usr/local/tomcat/webapps/

RUN sed -i 's/port="8080"/port="9090"/' ${CATALINA_HOME}/conf/server.xml

CMD ["catalina.sh", "run"]