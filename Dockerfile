FROM ubuntu:latest
LABEL authors="dinhq"

ENTRYPOINT ["top", "-b"]