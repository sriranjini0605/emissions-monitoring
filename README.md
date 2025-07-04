
# Emissions Monitoring API

A Spring Boot application that collects emissions data from machines, stores it in PostgreSQL, and triggers AWS SNS alerts when emissions exceed specified thresholds.

This project is containerized using Docker and Docker Compose for easy local setup.

---

## Features

- REST API to submit and retrieve emissions data
- PostgreSQL for data persistence
- AWS SNS integration to send emission alert notifications
- Docker Compose setup for local development
- Simple shell script (`run.sh`) for one-command setup

---

## Tech Stack

- Java 21, Spring Boot 3
- PostgreSQL
- AWS SDK (SNS)
- Docker & Docker Compose
- Hibernate, Spring Data JPA

---

## Prerequisites

- Docker & Docker Compose installed  
  [Install Docker](https://docs.docker.com/get-docker/)
- Java 21 installed (required only for building)
- AWS account setup with the following:
    - An **SNS topic** created (you will need the **Topic ARN**)
    - **AWS Access Key ID** and **Secret Access Key** for programmatic access

---

## AWS SNS Setup

To enable SNS notifications:

1. Log in to your AWS account and navigate to **SNS > Topics > Create topic**.
    - Select **Standard** as the topic type.
    - Name the topic and create it.
    - Copy the **Topic ARN** after creation.
2. Subscribe your email or SMS to the topic (for testing):
    - Go to your topic → **Create subscription**
    - Select protocol (**Email**, **SMS**, etc.) and enter your endpoint.
    - Confirm the subscription from your email/SMS.
3. Create an IAM user with **AmazonSNSFullAccess** permissions:
    - Go to **IAM > Users > Add user**
    - Enable **Programmatic access**
    - Attach the **AmazonSNSFullAccess** policy
    - Save the **Access Key ID** and **Secret Access Key** for configuration

4. Add your AWS credentials in the `docker-compose.yml` file as environment variables:
   ```yaml
   AWS_ACCESS_KEY_ID: your_aws_access_key
   AWS_SECRET_ACCESS_KEY: your_aws_secret_key
   AWS_REGION: us-west-2
   ```

5. Update your SNS topic ARN in the `EmissionService.java` file:
   ```java
   private static final String TOPIC_ARN = "arn:aws:sns:us-west-2:123456789012:emission-alerts-topic";
   ```

---

## Running the Application Locally

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/emissions-monitoring-api.git
cd emissions-monitoring-api
```

### 2. Run Using the Provided Shell Script (Linux/Mac/WSL)

This script builds the Spring Boot app and starts both the API and PostgreSQL containers.

```bash
chmod +x run.sh
./run.sh
```

### 3. Or Run the Commands Manually

```bash
./mvnw clean package -DskipTests
docker-compose down --remove-orphans
docker-compose up --build
```

---

## API Endpoints

| Method | Endpoint           | Description                    |
|-------|--------------------|--------------------------------|
| POST  | `/api/emissions`   | Submit emissions data          |
| GET   | `/api/emissions`   | Retrieve all emissions records |

Example request body for `/api/emissions`:

```json
{
  "machineId": "CAT9001",
  "carbonMonoxide": 65,
  "nitrogenOxides": 45,
  "particulateMatter": 25
}
```

---

## Default Ports

| Service          | Port |
|------------------|------|
| Spring Boot API  | 8080 |
| PostgreSQL       | 5432 |

The API will be accessible at:  
`http://localhost:8080/api/emissions`

---

## Stopping the Services

Press `Ctrl + C` to stop the containers, then run:

```bash
docker-compose down
```

---

## Troubleshooting

- Ensure Docker is running before executing the script
- Verify your AWS credentials and SNS topic ARN are correct
- Make sure port 5432 and 8080 are available on your system

---

## Future Improvements

- Add Prometheus and Grafana for real-time monitoring
- Deploy on AWS EC2 using Docker Compose
- Add JWT authentication for API endpoints
- Support batch ingestion of emissions data

