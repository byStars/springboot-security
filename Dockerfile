FROM java:8
MAINTAINER xxx <xxx@qq.com>

ENV TZ=Asia/Shanghai

VOLUME /tmp

COPY target/*.jar app.jar

RUN echo $(date) > /image_built_at

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
