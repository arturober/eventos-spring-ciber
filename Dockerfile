FROM openjdk:17-jdk-alpine
RUN mkdir -p /public/images
COPY public/images/xss.js /public/images
RUN addgroup -S spring && adduser -S spring -G spring
RUN chown -R spring:spring /public/images
USER spring:spring
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.arturober.eventosjdbc.EventosjdbcApplication"]
