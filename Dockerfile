FROM maven:3.6.3-jdk-8

EXPOSE 8080

WORKDIR .

RUN git clone https://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@github.com/dds-utn/2022-tpa-vi-no-grupo-05.git

RUN ls

WORKDIR /2022-tpa-vi-no-grupo-05

RUN mvn clean package

RUN java -version

CMD  ["java", "-jar", "target/tpa-1.0-SNAPSHOT-jar-with-dependencies.jar"]

# Me muevo a la carpeta donde voy a dejar el cronjob
WORKDIR /2022-tpa-vi-no-grupo-05/cron.d

ARG CRON_NOTIFICADOR

# Creo el archivito en /etc/cron.d/cronjob, que tiene el comando
RUN echo "${CRON_ENVIO_GUIA} sh -c \"java -cp /2022-tpa-vi-no-grupo-05/target/application.jar src.main.java.dominio.Notificador.Notificador\"" >> logcron

RUN chmod 0644 logcron

RUN crontab logcron

# Dejo que el container se quede escuchando los logs de crond
ENTRYPOINT ["crond", "-f"]

# Build image
# docker build -t tpa .

# Run container
# docker run --rm -p 9090:9090 tpa

# Cloud Run
# docker tag tpa:latest gcr.io/utndds/tpa
# gcloud docker -- push gcr.io/utndds/tpa

# gcloud auth login
# gcloud config set project utndds
# gcloud auth application-default login