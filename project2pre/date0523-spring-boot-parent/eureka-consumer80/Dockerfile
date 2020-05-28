FROM java:8

COPY *.jar /consumer.jar

CMD ["--server.port=80"]

EXPOSE 80

ENTRYPOINT ["java","-jar","/consumer.jar"]
