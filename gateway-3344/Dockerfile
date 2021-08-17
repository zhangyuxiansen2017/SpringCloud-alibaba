FROM java:8
ENV LANG=C.UTF-8
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone

WORKDIR /$BASE_DIR
ADD target/gateway-3344-1.0-SNAPSHOT.jar /app.jar
RUN bash -c 'touch /app.jar'

EXPOSE 3344
ENTRYPOINT ["java","-jar","-Xms128m","-Xmx500m","/app.jar"]