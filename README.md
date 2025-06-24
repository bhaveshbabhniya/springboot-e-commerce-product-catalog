# 🛒 E-commerce Product Catalog

This is a role-based E-commerce Product Catalog web application developed using **Spring Boot 3**, **Spring Security 6**, **JWT**, and **MySQL**.  
It supports separate functionalities for **Sellers** and **Buyers**.

📁 Project Path: [`/e_commerce_product_catalog`](https://github.com/bhaveshbabhniya/springboot-e-commerce-product-catalog)

---

## 🚀 Features
### 👤 Authentication
- JWT-based Login
- Role-based Access Control (`SELLER`, `BUYER`)

### 🧾 Seller Features
- Login to Seller Account
- Add/Upload Single or Multiple Products
- View Placed Orders (on their products)

### 🛍️ Buyer Features
- Register/Login to Buyer Account
- Browse Products
- Add to Cart and Place Orders
- View Order History

---

## 🛠️ Tech Stack

- Java 17
- **Spring Boot 3**
- **Spring Security 6**
- **JWT (JSON Web Token)**
- **MySQL**
- **Swagger UI (springdoc-openapi)**
- **Lombok**
- **Hibernate + JPA**

---

## 📂 Project Structure
```
springboot-e-commerce-product-catalog/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── springboot/
│   │   │           └── ecommerce_product_catalog/
│   │   │               ├── config/
│   │   │               │   ├── JwtAuthFilter.java
│   │   │               │   ├── SecurityConfig.java
|   |   |               |   └── SwaggerConfig.java
|   |   |               |
|   |   |               ├── controller/
│   │   │               │   ├── AuthController.java
|   |   |               |   ├── CartController.java
│   │   │               │   ├── CategoryController.java
│   │   │               │   ├── OrderController.java
│   │   │               │   ├── ProductController.java
|   |   |               |   └── SellerOrderController.java
│   │   │               │
│   │   │               ├── dto/
│   │   │               │   ├── AuthRequest.java
│   │   │               │   ├── CartItemDTO.java
│   │   │               │   ├── OrderItemDTO.java
│   │   │               │   ├── OrderRequestDTO.java
│   │   │               │   ├── OrderResponseDTO.java
│   │   │               │   ├── ProductDTO.java
│   │   │               │   ├── ProductResponseDTO.java
│   │   │               │   ├── RegisterRequest.java
│   │   │               │   └── SellerOrderDTO.java
│   │   │               │
│   │   │               ├── entity/
|   |   |               |   ├── Cart.java
|   |   |               |   ├── Category.java
│   │   │               │   ├── Order.java
│   │   │               │   ├── OrderItem.java
│   │   │               │   ├── OrderStatus.java
│   │   │               │   ├── Product.java
│   │   │               │   ├── Role.java
│   │   │               │   └── User.java
│   │   │               │
│   │   │               ├── repository/
|   |   |               |   ├── CartRepository.java
│   │   │               │   ├── CategoryRepository.java
│   │   │               │   ├── OrderItemRepository.java
│   │   │               │   ├── OrderRepository.java
│   │   │               │   ├── ProductRepository.java
│   │   │               │   └── UserRepository.java
│   │   │               │
│   │   │               ├── service/
│   │   │               │   ├── AuthService.java
|   |   |               |   ├── CartService.java
|   |   |               |   ├── CategoryService.java
│   │   │               │   ├── CustomUserDetailsService.java
│   │   │               │   ├── OrderService.java
│   │   │               │   ├── ProductService.java
│   │   │               │   └── SellerOrderService.java
│   │   │               │
│   │   │               ├── util/
│   │   │               │   ├── JwtUtil.java
│   │   │               │   └── SecurityUtil.java
│   │   │               │
│   │   │               └── LibraryManagementSystemApplication.java
│   │   │
│   │   └── resources/
│   │       ├── application.properties
├── pom.xml
└── README.md
```
---

## ⚙️ Setup Instructions

- Java 17+
- Maven 3.6+
- MySQL Server
- Swagger (for API testing)
- Git (optional, for cloning)

### 1. Clone the Repository
```bash
git clone https://github.com/bhaveshbabhniya/springboot-e-commerce-product-catalog.git
cd springboot-e-commerce-product-catalog
```

### 2. Create database in MYSQL
```bash
CREATE DATABASE ecommercedb;
```
Update your application.properties file with your MySQL credentials:
```
spring.datasource.username=root
spring.datasource.password=your_password
```

### 3. ▶️ Run the Application
```bash
./mvnw spring-boot:run
```
Or in Eclipse IDE, run EcommerceProductCatalogApplication.java as a Java application.

---

## 📫 API Endpoints
### Auth
```
POST | /api/auth/register       #Register new user (Role : Public)
POST | /api/auth/login          #Login and get JWT (Role : Public)
```
### Product
```
GET  | /api/products            #View all Products (Role : SELLER/BUYER)
POST | /api/products            #Upload new Products (Role : SELLER)
GET  | /api/products/my         #View Products of current user (Role : SELLER)
```
### Categories
```
GET  | /api/categories          #View all Categories (Role : SELLER/BUYER)
POST | /api/categories          #Upload new Categories (Role : SELLER)
```
### Orders
```
GET  | /api/orders              #View all placed orders for current user (Role : BUYER)
POST | /api/orders              #Place new orders (Role : BUYER)
```
### Cart
```
POST | /api/cart/add            #Add to cart Product (Role : BUYER)
GET  | /api/cart                #View Cart Products (Role : BUYER)
GET  | /api/cart/clear          #Remove Products from Cart (Role : BUYER)
```
### Seller Orders
```
GET | /api/seller/orders        #View all placed orders for current user (Role : SELLER)
```
### 🧪 Sample JSON for Testing
Register new user
```
POST /api/auth/register
Body: {
  "username": "string",
  "password": "string",
  "role": "SELLER"
}
```
Login to get JWT Token:
```
POST /api/auth/login
Body: {
  "username": "john",
  "password": "john123"
}
Response: {
  "token": "eyJhbGciOiJIUzI1NiIs..."
}
```
Use this token in Swagger:
```
Authorization: Bearer <your-token>
```

## 📘 Swagger UI

Access API documentation and test requests from browser:
```
http://localhost:8080/swagger-ui/index.html
```
1. Click **"Authorize"** button
2. Enter your token:
   ```
   Bearer <your-token>
   ```
3. Test secure endpoints
---

# 📌 Notes
- Lombok must be installed and enabled in your IDE.
- Make sure MySQL is running and accessible.
- Consider securing the endpoints for production use.

---

# 👤 Author
  Bhavesh Babhniya
🔗 [`GitHub`](https://github.com/bhaveshbabhniya)
