# Build
mvn clean package && docker build -t it.tss/pw-favero1 .

# RUN

docker rm -f pw-favero1 || true && docker run -d -p 8080:8080 -p 4848:4848 --name pw-favero1 it.tss/pw-favero1 