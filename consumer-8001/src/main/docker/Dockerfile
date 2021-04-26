FROM java:8
ENV LANG=C.UTF-8


# set environment
ENV TIME_ZONE="Asia/Shanghai"

WORKDIR /$BASE_DIR

ADD sonsumer-8001-1.0-SNAPSHOT.jar /app.jar


EXPOSE 8001

ENTRYPOINT ["java","-jar","/app.jar"]