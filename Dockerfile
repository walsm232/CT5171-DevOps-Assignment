FROM tomcat:9.0.82-jdk17-corretto

COPY target/michaelspetitions.war /usr/local/tomcat/webapps/

EXPOSE 8080

CMD ["catalina.sh", "run"]