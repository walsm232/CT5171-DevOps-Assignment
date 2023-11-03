FROM tomcat:9.0.82-jdk17-corretto

COPY target/michaelspetitions.war /usr/local/tomcat/webapps/

RUN sed -i 's/port="8080"/port="9090"/' ${CATALINA_HOME}/conf/server.xml

CMD ["catalina.sh", "run"]