FROM java:8

COPY *.jar /server.jar

CMD ["--server.port=9524"]

EXPOSE 9524

ENTRYPOINT ["java","-jar","/server.jar"]
