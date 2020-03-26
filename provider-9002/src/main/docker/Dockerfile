FROM java:8
ENV LANG=C.UTF-8


# set environment
ENV TIME_ZONE="Asia/Shanghai"

WORKDIR /$BASE_DIR

ADD provider-9002-1.0-SNAPSHOT.jar /app.jar


EXPOSE 9002

ENTRYPOINT ["java","-jar","/app.jar"]