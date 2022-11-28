FROM maven:3.6.3-jdk-8

EXPOSE 9090

WORKDIR .


RUN git clone https://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@github.com/dds-utn/2022-tpa-vi-no-grupo-05.git

RUN ls

WORKDIR /2022-tpa-vi-no-grupo-05

RUN mvn clean package

RUN java -version

CMD  ["java", "-jar", "target/tpa-1.0-SNAPSHOT-jar-with-dependencies.jar"]


# Build image
# docker build -t tpa .

# Run container
# docker run --rm -p 9090:9090 tpa