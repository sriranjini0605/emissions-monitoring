#!/bin/bash

echo ""
echo "========================================"
echo "Starting Emissions Monitoring API Setup"
echo "========================================"
echo ""

# Step 1: Build the Spring Boot JAR
echo "Step 1: Building Spring Boot application (skip tests)..."
./mvnw clean package -DskipTests

if [ $? -ne 0 ]; then
  echo ""
  echo "Build failed. Please fix the errors above before running Docker Compose."
  exit 1
fi

echo ""
echo "Build successful."
echo ""

# Step 2: Bring down existing containers
echo "Step 2: Stopping any running containers..."
docker-compose down --remove-orphans
echo "Stopped old containers."

echo ""
echo "Step 3: Starting Docker Compose services..."
docker-compose up --build

echo ""
echo "========================================"
echo "Docker Compose Services Running"
echo "========================================"
echo ""
echo "API is running on: http://localhost:8080"
echo "   - Example endpoint: http://localhost:8080/api/emissions"
echo ""
echo "PostgreSQL is running on: localhost:5432"
echo "   - Database name: emissionsdb"
echo "   - Username: postgres"
echo "   - Password: pillar123"
echo ""
echo "To stop the containers: Ctrl+C, then run 'docker-compose down'"
echo ""
