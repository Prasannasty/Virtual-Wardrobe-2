# Virtual Wardrobe - Spring Boot Application

## Overview
The Virtual Wardrobe is a comprehensive Spring Boot application designed to help users manage their clothing items digitally. It provides features for organizing, categorizing, and getting recommendations for clothing combinations. The application follows modern software development best practices and utilizes a microservices-oriented architecture.

## Key Features
1. **User Authentication & Authorization**
   - JWT-based authentication
   - OAuth2 integration
   - Role-based access control
   - Secure password storage

2. **Clothing Management**
   - Add/update/delete clothing items
   - Categorization by type, season, and occasion
   - Image upload to AWS S3
   - Metadata tracking (purchase date, brand, etc.)

3. **Recommendation System**
   - AI-powered outfit suggestions
   - Weather-based recommendations
   - Event-specific outfit ideas

4. **Contact & Feedback**
   - Secure contact form
   - Message tracking
   - Admin dashboard for managing inquiries

5. **API Documentation**
   - Swagger UI integration
   - Detailed endpoint documentation
   - Example requests/responses

## Technical Stack
- **Backend**: Spring Boot 3.x
- **Database**: MySQL (JPA/Hibernate)
- **Authentication**: JWT, OAuth2
- **Cloud Storage**: AWS S3
- **API Documentation**: Swagger
- **Testing**: JUnit, Mockito
- **Build Tool**: Maven
- **Security**: Spring Security, CORS configuration

## Architecture Highlights
1. **Layered Architecture**
   - Controller layer for API endpoints
   - Service layer for business logic
   - Repository layer for data access
   - DTO pattern for data transfer

2. **Exception Handling**
   - Custom exception classes
   - Global exception handler
   - Consistent error responses

3. **Security Implementation**
   - JWT token generation/validation
   - Password encryption
   - CSRF protection
   - CORS configuration

4. **AWS Integration**
   - S3 bucket configuration
   - File upload/download endpoints
   - Image processing capabilities

## Impressive Additions (Not Currently Implemented)
1. **Machine Learning Integration**
   - Outfit recommendation engine
   - Style analysis using computer vision
   - Trend prediction system

2. **Social Features**
   - Share outfits with friends
   - Community style challenges
   - Influencer collaborations

3. **Advanced Analytics**
   - Clothing usage statistics
   - Cost-per-wear calculations
   - Sustainability impact tracking

4. **Mobile Integration**
   - Progressive Web App (PWA) support
   - Push notifications
   - Offline functionality

## API Endpoints

### Authentication
1. **POST /api/auth/login**
   - Request Body:
     ```json
     {
       "email": "string",
       "password": "string"
     }
     ```
   - Task: Authenticate user and return JWT token

2. **POST /api/auth/register**
   - Request Body:
     ```json
     {
       "name": "string",
       "email": "string",
       "password": "string"
     }
     ```
   - Task: Register new user

### Clothing Management
1. **POST /api/clothing**
   - Request Body:
     ```json
     {
       "name": "string",
       "type": "string",
       "imageUrl": "string",
       "userId": "long"
     }
     ```
   - Task: Add new clothing item

2. **GET /api/clothing/{id}**
   - Task: Get clothing item details

3. **PUT /api/clothing/{id}**
   - Request Body:
     ```json
     {
       "name": "string",
       "type": "string",
       "imageUrl": "string"
     }
     ```
   - Task: Update clothing item

4. **DELETE /api/clothing/{id}**
   - Task: Delete clothing item

### Recommendations
1. **GET /api/recommendations?userId={userId}&occasion={occasion}**
   - Task: Get outfit recommendations based on occasion

2. **POST /api/recommendations**
   - Request Body:
     ```json
     {
       "userId": "long",
       "items": [
         {
           "itemId": "long",
           "type": "string"
         }
       ]
     }
     ```
   - Task: Save user's outfit combination

### Contact
1. **POST /api/contact**
   - Request Body:
     ```json
     {
       "contactName": "string",
       "contactEmail": "string",
       "message": "string",
       "uploaderId": "long"
     }
     ```
   - Task: Submit contact form

## How to Run
1. Clone the repository
2. Configure application.properties with your database and AWS credentials
3. Run `mvn clean install`
4. Start the application using `mvn spring-boot:run`
5. Access the API documentation at `http://localhost:8080/swagger-ui.html`

## Future Enhancements
1. Integration with weather APIs for real-time recommendations
2. Virtual try-on using AR technology
3. Integration with e-commerce platforms
4. AI-powered wardrobe optimization
5. Multi-language support

## Interview Talking Points
1. Explain the security implementation (JWT, OAuth, Spring Security)
2. Discuss the AWS S3 integration and file handling
3. Highlight the clean architecture and separation of concerns
4. Mention the use of DTO pattern and its benefits
5. Explain the exception handling strategy
6. Discuss potential scalability improvements
7. Talk about the recommendation system architecture
8. Explain the testing strategy and coverage
