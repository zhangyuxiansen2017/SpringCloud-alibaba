FROM java:8
ENV LANG=C.UTF-8

# set environmen
ENV TIME_ZONE="Asia/Shanghai"

WORKDIR /$BASE_DIR

ADD auth-9999-1.0-SNAPSHOT.jar /app.jar


EXPOSE 9999

ENTRYPOINT ["java","-jar","/app.jar"]