FROM tomcat:9.0.82-jdk17-corretto

COPY target/ct5171-devops-assignment-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/

RUN sed -i -e 's/8080/9000/' /usr/local/tomcat/conf/server.xml

EXPOSE 9000

CMD ["catalina.sh", "run"]