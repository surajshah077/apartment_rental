What is this?
A full backend API for handling apartment rentals. As a graduate student in computer science in Berlin's crazy rental market, I made this to help with real problems: 
• Landlords: List apartments, check out potential tenants, keep track of payments, and take care of repairs.
• Tenants: See what units are available and send in requests 
• Automation: checks on income (three times the rent rule), apartments that are automatically unavailable, and payment status 
Everything from onboarding tenants to the end of the lease is in clean REST APIs.
Features
5 Entities: Apartment, Tenant, Lease, Payment, MaintenanceRequest
 Full CRUD + custom workflows (mark payment PAID/OVERDUE)
 Business validation (affordability, availability)
Global JSON error handling
 Generic BaseController (DRY code!)
 Scheduled tasks ready (overdue payments)
25+ endpoints ready for Postman/React/Angular frontend.
 Quick Start
1.Clone & Open
bash
git clone <https://github.com/surajshah077/apartment_rental>
cd apartment-rental-system
2.Run (H2 database auto-starts)
bash
mvn spring-boot:run
 or in IntelliJ: click ▶️
3.API Ready: http://localhost:8080
 Test with Postman
Real workflow :
1. POST /api/apartments
{"address":"Berlin Mitte","rentAmount":1000,"bedrooms":2}

2. POST /api/tenants  
{"name":"Suraj Shah","email":"suraj@berlin.de","income":3500}

3. POST /api/leases/create
{"tenantId":1,"apartmentId":1,"startDate":"2026-04-01","endDate":"2027-04-01"}

4. POST /api/payments
{"amount":1000,"lease":{"id":1}} → POST /api/payments/1/pay

5. POST /api/maintenance
{"description":"Fix leaky faucet","lease":{"id":1}}
Full API Reference → https://github.com/surajshah077/Postman_M604
Architecture
Controllers → Services → Repositories → MySQL/H2
   BaseController    BaseService    JpaRepository
Key Patterns:
•	Generic BaseController<T,ID> + BaseService<T,ID>
•	@Transactional services
•	@ControllerAdvice for JSON errors
•	Lombok everywhere (@Data)
Project Structure
 

Configuration
application.properties (all auto-configured):
spring.datasource.url=jdbc:h2:mem:testdb
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true
Production: Swap H2 → MySQL, add spring.jpa.show-sql=true
Error Handling
Clean JSON everywhere:
json
{
  "code": "RESOURCE_NOT_FOUND",
  "message": "Payment not found: 999",
  "timestamp": "2026-03-19 10:47:00"
}
Handles: 404s, validation (@NotNull/@Positive), bad IDs, server errors.
API Documentation
 
Troubleshooting
 
Deployment
bash
 Docker (add Dockerfile)
mvn clean package -DskipTests
docker build -t arms-api .
docker run -p 8080:8080 arms-api
Heroku/Railway: Just mvn package → deploy JAR.
Academic Context
MSc Computer Science Project demonstrating:
•	Enterprise Spring Boot patterns
•	RESTful API design
•	JPA relationships & transactions
•	Global exception handling
•	Test-driven development

