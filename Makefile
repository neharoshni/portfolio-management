# Target to build the project
build:
	mvn clean install

# Target to simulate traffic using PowerShell for Windows
traffic:
	@echo "Generating traffic to endpoints..."
	powershell -ExecutionPolicy Bypass -Command "& { \
		while ($true) { \
			Invoke-RestMethod -Uri 'http://localhost:9902/api/instrument/stocks' -Headers @{'accept'='application/json'; 'access-token'='c96cf9ae-a0ce-40ba-91b8-8a636a38a87d'}; \
			Start-Sleep -Milliseconds 500; \
			Invoke-RestMethod -Uri 'http://localhost:9903/api/trade/portfolio/1' -Headers @{'accept'='application/json'; 'access-token'='c96cf9ae-a0ce-40ba-91b8-8a636a38a87d'}; \
			Start-Sleep -Milliseconds 500; \
		} \
	}"

# Start all Docker containers
start-docker:
	@echo "Starting Docker containers..."
	docker compose -f ./deployment/compose/docker-compose.yaml down -v && \
	docker compose -f ./deployment/compose/docker-compose.yaml rm -fsv && \
	docker compose -f ./deployment/compose/docker-compose.yaml up --remove-orphans

# Stop and clean up Docker containers
stop-docker:
	@echo "Stopping Docker containers..."
	docker compose -f ./deployment/compose/docker-compose.yaml down -v && \
	docker compose -f ./deployment/compose/docker-compose.yaml rm -fsv

# Start backend applications
start-apps:
	@echo "Starting backend applications..."
	start /B mvn -f modules/spring-admin/pom.xml spring-boot:run && \
	start /B mvn -f modules/service-registry/pom.xml spring-boot:run && \
	timeout /T 20 && \
	start /B mvn -f modules/auth-service/pom.xml spring-boot:run && \
	start /B mvn -f modules/order-service/pom.xml spring-boot:run && \
	start /B mvn -f modules/ticker-service/pom.xml spring-boot:run && \
	start /B mvn -f modules/producer-service/pom.xml spring-boot:run && \
	start /B mvn -f modules/consumer/pom.xml spring-boot:run

# Stop backend applications
stop-apps:
	@echo "Stopping backend applications..."
	jps | findstr "ConsumerCLIRunner" | for /F "tokens=1" %i in ('') do taskkill /PID %i /F && \
	jps | findstr "ProducerServiceApplication" | for /F "tokens=1" %i in ('') do taskkill /PID %i /F && \
	jps | findstr "TickerServiceApplication" | for /F "tokens=1" %i in ('') do taskkill /PID %i /F && \
	jps | findstr "OrderServiceApplication" | for /F "tokens=1" %i in ('') do taskkill /PID %i /F && \
	jps | findstr "EurekaServerApplication" | for /F "tokens=1" %i in ('') do taskkill /PID %i /F && \
	jps | findstr "SpringAdminApplication" | for /F "tokens=1" %i in ('') do taskkill /PID %i /F && \
	jps | findstr "APIGatewayApplication" | for /F "tokens=1" %i in ('') do taskkill /PID %i /F && \
	jps | findstr "AuthServiceApplication" | for /F "tokens=1" %i in ('') do taskkill /PID %i /F
