FROM airhacks/glassfish
COPY ./target/pw-favero1.war ${DEPLOYMENT_DIR}
