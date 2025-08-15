Docker
  step1. docker pull mysql:8.0
  step2. docker run --name airline-mysql-db -e MYSQL_ROOT_PASSWORD=airlineTest1234 -p 3306:3306 -d mysql:8.0
  step3. docker ps

