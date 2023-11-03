#FROM tomcat:9.0.82-jdk17-corretto
FROM tomcat:latest

ADD target/michaelspetitions.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 9090

CMD ["catalina.sh", "run"]