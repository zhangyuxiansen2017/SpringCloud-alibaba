FROM java:8
ENV LANG=C.UTF-8


# set environment
ENV BASE_DIR="/" \
    TIME_ZONE="Asia/Shanghai"

WORKDIR /$BASE_DIR

RUN ln -snf /usr/share/zoneinfo/$TIME_ZONE /etc/localtime && echo '$TIME_ZONE' > /etc/timezone \

ADD /var/jenkins_home/workspace/springcloud/provider-9001/target/provider-9001-1.0-SNAPSHOT.jar app.jar


EXPOSE 9001

ENTRYPOINT ["java","-jar","/app.jar"]