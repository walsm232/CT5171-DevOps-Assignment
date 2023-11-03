FROM tomcat:9.0.82-jdk17-corretto

COPY target/michaelspetitions-0.0.1.war /usr/local/tomcat/webapps/

RUN sed -i -e 's/8080/9090/' /usr/local/tomcat/conf/server.xml

EXPOSE 9090

CMD ["catalina.sh", "run"]