FROM eclipse-temurin:17-jre-focal AS java

USER root

WORKDIR /app
COPY --chmod=644 --chown=root:root ./target/spring-boot-eureka-*.jar app.jar
RUN mkdir -p /app/data && chmod 777 -R /app/data

USER 1001

ARG OTEL_LOGS_EXPORTER
ARG OTEL_EXPORTER_OTLP_ENDPOINT
ARG OTEL_SERVICE_NAME
ARG JAVA_VM_OPTIONS
ARG PORT
ARG MANAGEMENT_PORT

ENV JAVA_VM_OPTIONS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=80"
ENV OTEL_LOGS_EXPORTER="otlp"
ENV OTEL_TRACES_EXPORTER="otlp"
ENV OTEL_EXPORTER_OTLP_ENDPOINT="http://localhost:4318"
ENV OTEL_SERVICE_NAME="spring-cloud-eureka"
ENV PORT="8761"
ENV MANAGEMENT_PORT="8761"


ENTRYPOINT ["sh", "-c"]
CMD ["exec java $JAVA_VM_OPTIONS -jar /app/app.jar"]

HEALTHCHECK --interval=30s --timeout=5s --start-period=20s --retries=3 CMD sh -c "wget --no-verbose --tries=1 --spider http://localhost:$MANAGEMENT_PORT/actuator/health || exit 1"

EXPOSE 8761