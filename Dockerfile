FROM maven:3.8.6-openjdk-8

EXPOSE 9090

RUN apt-get update && apt-get install cron -y && rm -rf /etc/localtime && ln -s /usr/share/zoneinfo/America/Argentina/Buenos_Aires /etc/localtime

RUN service cron start && service cron status

#WORKDIR .

#RUN git clone https://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@github.com/dds-utn/2022-tpa-vi-no-grupo-05.git

COPY . /tpa-dds

#COPY target/tpa-1.0-SNAPSHOT-jar-with-dependencies.jar /tpa-dds/target

#WORKDIR /2022-tpa-vi-no-grupo-05
WORKDIR /tpa-dds

RUN mvn package -DskipTests

WORKDIR /etc/cron.d

RUN service cron start && service cron restart && service cron status

#RUN service cron status

RUN echo "0 19 * * * sh -c \"cd /tpa-dds; /usr/local/openjdk-8/bin/java -cp /tpa-dds/target/tpa-1.0-SNAPSHOT-jar-with-dependencies.jar main/Recomendador 2>/tmp/cron.log\"" > notificador

RUN chmod 0644 notificador

RUN crontab notificador

WORKDIR /tpa-dds

CMD ["/bin/bash", "-c", "service cron start; java -jar target/tpa-1.0-SNAPSHOT-jar-with-dependencies.jar"]

#CMD  ["java", "-jar", "target/tpa-1.0-SNAPSHOT-jar-with-dependencies.jar"]
#CMD ["cron", "-f"]

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