FROM java:8
ENV LANG=C.UTF-8

#RUN ln -snf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' > letc/timezone

#ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
#    JHIPSTER_SLEEP=0 \
#    JAVA_OPTS=""

ADD provider-9001-1.0-SNAPSHOT.jar app.jar


EXPOSE 9001

ENTRYPOINT ["java","-jar","/app.jar"]