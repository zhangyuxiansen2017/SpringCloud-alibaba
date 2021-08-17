FROM java:8
ENV LANG=C.UTF-8
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone

WORKDIR /$BASE_DIR
ADD target/auth-9999-1.0-SNAPSHOT.jar /app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 9999
ENTRYPOINT ["java","-jar","-Xms128m","-Xmx500m","/app.jar"]