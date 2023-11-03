FROM tomcat:9.0.82-jdk17-corretto

ADD target/*.war /usr/local/tomcat/webapps/

EXPOSE 9090

CMD ["catalina.sh", "run"]