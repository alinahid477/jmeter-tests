FROM fedora:41


ARG JMETER_VERSION="5.6.3"
ENV JMETER_HOME /opt/apache-jmeter-${JMETER_VERSION}
ENV JMETER_CUSTOM_PLUGINS_FOLDER /jars
ENV	JMETER_BIN	${JMETER_HOME}/bin
ENV	JMETER_DOWNLOAD_URL  https://dlcdn.apache.org//jmeter/binaries/apache-jmeter-${JMETER_VERSION}.tgz

RUN    dnf update -y \
	&& dnf upgrade -y \
	&& dnf install -y java-17-openjdk.x86_64 \
	&& dnf install -y nss \
	&& mkdir -p /tmp/dependencies  \
	&& curl -L --silent ${JMETER_DOWNLOAD_URL} >  /tmp/dependencies/apache-jmeter-${JMETER_VERSION}.tgz  \
	&& mkdir -p /opt  \
	&& tar -xzf /tmp/dependencies/apache-jmeter-${JMETER_VERSION}.tgz -C /opt  \
	&& rm -rf /tmp/dependencies

# TODO: plugins (later)
# && unzip -oq "/tmp/dependencies/JMeterPlugins-*.zip" -d $JMETER_HOME

# Set global PATH such that "jmeter" command is found
ENV PATH $PATH:$JMETER_BIN

# Entrypoint has same signature as "jmeter" command
COPY entrypoint.sh /

COPY jars/*.jar /opt/apache-jmeter-${JMETER_VERSION}/lib/
RUN rm /opt/apache-jmeter-${JMETER_VERSION}/lib/mongo-java-driver-2.14.3.jar

WORKDIR	${JMETER_HOME}
RUN mkdir output/ && chmod +x /entrypoint.sh

ENTRYPOINT [ "/entrypoint.sh", "-n", "-t", "/opt/ww_mongo_test.jmx", "-l", "output.jtl", "-e", "-o", "output/"]

# ENTRYPOINT ["/entrypoint.sh"]