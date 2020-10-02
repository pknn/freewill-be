FROM hseeberger/scala-sbt:8u265_1.3.13_2.13.3 as build-stage
WORKDIR /app
COPY . .
RUN sbt stage

FROM robsonoduarte/8-jre-alpine-bash as run-stage
WORKDIR /app
COPY --from=build-stage /app/target/universal/stage .
EXPOSE 9000
CMD ["./bin/freewill"]