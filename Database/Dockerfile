FROM mysql:latest


ENV MYSQL_ROOT_PASSWORD=123456789
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=123456789

COPY ./init/ /docker-entrypoint-initdb.d/

EXPOSE 3306