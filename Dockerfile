FROM openjdk:11-jdk-slim
ARG jar
RUN groupadd -g 998 fwmtjobloader && \
    useradd -r -u 998 -g fwmtjobloader fwmtjobloader
USER fwmtjobloader
COPY $jar /opt/fwmtjobloader.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "java", "-jar", "/opt/fwmtjobloader.jar" ]
