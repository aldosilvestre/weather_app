FROM alpine:latest

RUN apk add --no-cache --update sudo zsh curl shadow openjdk8

RUN echo 'root:root' | chpasswd

RUN groupadd -g 1000 alpinegroup && \
    useradd -ms /bin/zsh --uid 1000 --gid 1000 alpineuser && \
    echo "alpineuser ALL=(ALL) NOPASSWD: ALL" >> /etc/sudoers.d/10-alpineuser

USER alpineuser
WORKDIR /home/alpineuser

ENV PATH=$PATH:/usr/bin/java
ENV JAVA_HOME=/usr/lib/jvm/java-1.8-openjdk/

COPY src src
COPY .mvn .mvn
COPY pom.xml mvnw .

RUN sudo sh mvnw package
RUN cp target/appWeather.jar .
RUN sudo rm -rf target
RUN sudo chmod 777 appWeather.jar

CMD ["java", "-jar", "appWeather.jar"]
