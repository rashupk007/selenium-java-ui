# Use an official Ubuntu base image
FROM ubuntu:22.04

# Set the working directory inside the container
WORKDIR /app

# Install essential packages and dependencies
RUN apt-get update && \
    apt-get install -y \
    openjdk-11-jdk \
    maven \
    wget \
    unzip \
    xvfb \
    libxi6 \
    libgconf-2-4 \
    gnupg \
    curl \
    && rm -rf /var/lib/apt/lists/*

# Fetch the latest stable Chrome version and install Chrome and ChromeDriver
RUN CHROME_VERSION=$(curl -sSL https://googlechromelabs.github.io/chrome-for-testing/ | awk -F 'Version:' '/Stable/ {print $2}' | awk '{print $1}' | sed 's/<code>//g; s/<\/code>//g') && \
    CHROME_URL="https://storage.googleapis.com/chrome-for-testing-public/${CHROME_VERSION}/linux64/chrome-linux64.zip" && \
    echo "Fetching Chrome version: ${CHROME_VERSION}" && \
    curl -sSL ${CHROME_URL} -o /tmp/chrome-linux64.zip && \
    mkdir -p /opt/google/chrome && \
    mkdir -p /usr/local/bin && \
    unzip -q /tmp/chrome-linux64.zip -d /opt/google/chrome && \
    rm /tmp/chrome-linux64.zip

# Install Firefox
RUN apt-get update && \
    apt-get install -y firefox && \
    rm -rf /var/lib/apt/lists/*

# Copy your project files into the container
COPY . .

# Build and compile the project without running tests
RUN mvn clean install -DskipTests

# Command to run on container start (optional, modify as needed)
CMD ["mvn", "test"]
