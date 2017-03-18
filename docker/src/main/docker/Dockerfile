FROM openjdk
MAINTAINER hryou0922
ENV JAVA_HOME /usr/local/java
RUN echo $JAVA_HOME
ADD docker-0.0.1-SNAPSHOT.jar /home/hry/docker/simplebuild/contain/app.jar
# 创建用户
RUN useradd hryou0922
RUN echo "hryou0922:hryou0922" | chpasswd 
# 指定用户  
USER hryou0922
# 外挂盘
VOLUME /home/hry/docker/simplebuild
# 工作目录
WORKDIR /home/hry/docker/simplebuild/contain/

EXPOSE 8080
ENTRYPOINT ["java","-jar" ,"/home/hry/docker/simplebuild/contain/app.jar"]

