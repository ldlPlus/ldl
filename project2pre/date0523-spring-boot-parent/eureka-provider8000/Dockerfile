FROM java:8

COPY *.jar /provider.jar

CMD ["--server.port=8000"]

EXPOSE 8000

ENTRYPOINT ["java","-jar","/provider.jar"]
