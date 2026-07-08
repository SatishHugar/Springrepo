# SatPall Crochet E-Commerce Application

## 📱 Overview
SatPall Crochet is an enterprise-grade, production-ready e-commerce web application for handmade crochet products. Built with Spring Boot 2.7.x, this application features a modern UI, comprehensive admin panel, and secure payment processing.

## 🎯 Features

### Customer Website
- ✅ Beautiful Home Page with Hero Banner
- ✅ Product Shop with Filters & Sorting
- ✅ Product Search Functionality
- ✅ Product Details with Reviews & Ratings
- ✅ Shopping Cart Management
- ✅ Secure Checkout Process
- ✅ Order Success Page
- ✅ Customer Information Pages (About, Contact, FAQ, Privacy, Terms)
- ✅ Responsive Design (Mobile, Tablet, Desktop)
- ✅ Wishlist Feature
- ✅ Product Reviews & Ratings

### Admin Panel
- ✅ Dashboard with Statistics
- ✅ Product Management (CRUD)
- ✅ Category Management (CRUD)
- ✅ Order Management & Status Updates
- ✅ Image Upload & Management
- ✅ Sales Reports
- ✅ Secure Admin Login

## 🏗️ Architecture

### Technology Stack
- **Backend**: Java 8, Spring Boot 2.7.x, Spring MVC, Spring Security
- **Database**: H2 File Database
- **Frontend**: HTML5, CSS3, Bootstrap 5, Vanilla JavaScript
- **ORM**: Spring Data JPA / Hibernate
- **Build Tool**: Maven
- **IDE**: Spring Tool Suite (STS)

### Package Structure
```
com.satpallcrochet
├── controller/          # Spring Controllers
├── service/             # Service Interfaces
├── service/impl/        # Service Implementations
├── repository/          # Data Access Layer
├── entity/              # JPA Entities
├── dto/                 # Data Transfer Objects
├── config/              # Spring Configuration
├── security/            # Security Configuration
├── util/                # Utility Classes
├── exception/           # Custom Exceptions
└── SatpallCrochetApplication.java
```

## 📂 Project Structure

```
src/main/
├── java/com/satpallcrochet/
│   ├── controller/
│   │   ├── HomeController.java
│   │   ├── ShopController.java
│   │   ├── ProductController.java
│   │   ├── CartController.java
│   │   ├── CheckoutController.java
│   │   ├── OrderSuccessController.java
│   │   ├── AdminLoginController.java
│   │   ├── AdminDashboardController.java
│   │   ├── AdminProductController.java
│   │   ├── AdminCategoryController.java
│   │   └── AdminOrderController.java
│   ├── service/
│   │   ├── CategoryService.java
│   │   ├── ProductService.java
│   │   ├── OrderService.java
│   │   └── ReviewService.java
│   ├── service/impl/
│   │   ├── CategoryServiceImpl.java
│   │   ├── ProductServiceImpl.java
│   │   ├── OrderServiceImpl.java
│   │   └── ReviewServiceImpl.java
│   ├── repository/
│   │   ├── CategoryRepository.java
│   │   ├── ProductRepository.java
│   │   ├── OrderRepository.java
│   │   ├── CustomerRepository.java
│   │   ├── ReviewRepository.java
│   │   ├── WishlistRepository.java
│   │   ├── AdminUserRepository.java
│   │   └── BannerRepository.java
│   ├── entity/
│   │   ├── Category.java
│   │   ├── Product.java
│   │   ├── Order.java
│   │   ├── OrderItem.java
│   │   ├── Customer.java
│   │   ├── AdminUser.java
│   │   ├── Review.java
│   │   ├── Wishlist.java
│   │   └── Banner.java
│   ├── dto/
│   │   ├── CartItemDto.java
│   │   ├── ProductDto.java
│   │   ├── OrderDto.java
│   │   ├── OrderItemDto.java
│   │   ├── CategoryDto.java
│   │   └── CustomerDto.java
│   ├── config/
│   │   └── SecurityConfig.java
│   ├── exception/
│   │   ├── ResourceNotFoundException.java
│   │   └── GlobalExceptionHandler.java
│   ├── util/
│   │   ├── FileUploadUtil.java
│   │   └── CartManager.java
│   └── SatpallCrochetApplication.java
├── resources/
│   ├── templates/
│   │   ├── index.html
│   │   ├── shop.html
│   │   ├── product-detail.html
│   │   ├── cart.html
│   │   ├── checkout.html
│   │   ├── order-success.html
│   │   ├── about.html
│   │   ├── contact.html
│   │   ├── faq.html
│   │   ├── privacy.html
│   │   ├── terms.html
│   │   └── admin/
│   │       ├── login.html
│   │       ├── dashboard.html
│   │       ├── products/
│   │       │   ├── list.html
│   │       │   └── form.html
│   │       ├── categories/
│   │       │   ├── list.html
│   │       │   └── form.html
│   │       └── orders/
│   │           ├── list.html
│   │           └── detail.html
│   ├── static/
│   │   ├── css/
│   │   │   ├── style.css
│   │   │   └── admin.css
│   │   ├── js/
│   │   │   └── main.js
│   │   ├── images/
│   │   └── uploads/
│   │       ├── products/
│   │       └── categories/
│   └── application.properties
└── pom.xml
```

## 🚀 Installation & Setup

### Prerequisites
- Java 8 or higher
- Maven 3.6+
- Spring Tool Suite (STS) or Eclipse
- Windows/Mac/Linux

### Steps

1. **Clone the Repository**
   ```bash
   git clone https://github.com/SatishHugar/Springrepo.git
   cd Springrepo
   ```

2. **Import into Spring Tool Suite**
   - Open STS
   - File → Import → Existing Maven Projects
   - Select the project folder
   - Click Finish

3. **Build the Project**
   ```bash
   mvn clean install
   ```

4. **Run the Application**
   - Right-click on project → Run As → Spring Boot App
   - Or use: `mvn spring-boot:run`

5. **Access the Application**
   - Customer Site: http://localhost:8080
   - Admin Panel: http://localhost:8080/admin/dashboard
   - H2 Console: http://localhost:8080/h2-console

### Database Setup
- H2 file database is automatically created at: `./database/satpall`
- No manual setup required
- Database is persistent across application restarts

## 👤 Default Admin Credentials

```
Username: admin
Password: admin123
```

## 🎨 Design Features

### Color Scheme
- **Primary Color**: #d8a5a5 (Dusty Rose)
- **Secondary Color**: #f5e6d3 (Cream)
- **Dark Background**: #2c3e50
- **Text Color**: #2c3e50

### UI/UX Elements
- Modern, elegant design
- Smooth animations and transitions
- Hover effects on products and buttons
- Responsive grid layouts
- Professional navigation
- Beautiful product cards
- Secure checkout flow

## 🔒 Security Features

- **Spring Security**: User authentication & authorization
- **CSRF Protection**: Enabled by default
- **Password Encoding**: BCrypt password hashing
- **Session Management**: Secure session handling
- **File Upload**: Secure file validation
- **Role-Based Access**: Admin-only sections protected

## 📊 Database Entities

### Products
- ID, Name, Description, Price, Stock
- Category ID, Image, Specifications
- Is Featured, Is Active, Rating, Review Count
- Created At, Updated At

### Categories
- ID, Name, Description, Image
- Is Active, Created At, Updated At

### Orders
- ID, Order Number, Customer Info
- Subtotal, Tax, Shipping, Discount, Grand Total
- Status, Payment Method, Notes
- Shipping Address Details, Created At, Updated At

### Order Items
- ID, Order ID, Product ID
- Quantity, Unit Price, Total Price

### Customers
- ID, First Name, Last Name, Email, Phone
- Address, City, Postal Code, State
- Created At, Updated At

### Reviews
- ID, Product ID, Reviewer Name
- Title, Content, Rating
- Is Approved, Created At, Updated At

### Wishlist
- ID, Product ID, Session ID
- Created At

### Admin Users
- ID, Username, Password, Email
- Full Name, Is Active
- Created At, Updated At

### Banners
- ID, Title, Subtitle, Image
- Link, Is Active, Display Order
- Created At, Updated At

## 🛠️ Configuration

### application.properties

```properties
# Server Configuration
server.port=8080
server.servlet.context-path=/

# Database Configuration
spring.datasource.url=jdbc:h2:file:./database/satpall
spring.jpa.hibernate.ddl-auto=update

# Thymeleaf Configuration
spring.thymeleaf.cache=false

# File Upload
spring.servlet.multipart.max-file-size=10MB

# Logging
logging.level.com.satpallcrochet=DEBUG
```

## 📝 API Endpoints

### Customer Endpoints
```
GET     /                       - Home Page
GET     /shop                   - Shop/Products
GET     /shop?categoryId=1      - Products by Category
GET     /product/{id}           - Product Details
POST    /cart/add               - Add to Cart
GET     /cart                   - View Cart
POST    /cart/update/{id}       - Update Cart Item
POST    /cart/remove/{id}       - Remove from Cart
GET     /checkout               - Checkout Page
POST    /checkout/process       - Process Order
GET     /order-success          - Order Success
```

### Admin Endpoints
```
GET     /login                  - Admin Login
POST    /login                  - Process Login
GET     /logout                 - Logout
GET     /admin/dashboard        - Admin Dashboard
GET     /admin/products         - List Products
GET     /admin/products/create  - Create Product Form
POST    /admin/products/create  - Save Product
GET     /admin/products/{id}/edit - Edit Product
POST    /admin/products/{id}/update - Update Product
POST    /admin/products/{id}/delete - Delete Product
GET     /admin/categories       - List Categories
GET     /admin/orders           - List Orders
GET     /admin/orders/{id}      - Order Details
```

## 🧪 Testing

### Sample Test Data
The application creates sample data on first run:
- 5 Sample Categories
- 20 Sample Products
- 1 Admin User (admin/admin123)
- 3 Banners

### Testing Workflow
1. Browse products on home page
2. Search/filter products
3. Add products to cart
4. Proceed to checkout
5. Fill customer details
6. Place order
7. Login to admin panel
8. View/manage orders
9. Update order status

## 📦 Dependencies

- Spring Boot 2.7.14
- Spring Security
- Spring Data JPA
- Thymeleaf
- H2 Database
- Lombok
- Bootstrap 5.3
- Font Awesome 6.4

## 🐛 Troubleshooting

### Issue: Port 8080 already in use
**Solution**: Change port in application.properties
```properties
server.port=8081
```

### Issue: Database connection error
**Solution**: Ensure ./database directory exists
```bash
mkdir database
```

### Issue: Images not uploading
**Solution**: Check directory permissions and upload folder path
```properties
app.upload.dir=src/main/resources/static/uploads/
```

## 📚 Additional Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Security Guide](https://spring.io/projects/spring-security)
- [Thymeleaf Docs](https://www.thymeleaf.org/)
- [Bootstrap 5 Documentation](https://getbootstrap.com/)
- [H2 Database](http://www.h2database.com/)

## 📞 Support

For issues or questions:
- Create an issue on GitHub
- Email: info@satpallcrochet.com
- Documentation: Check wiki for detailed guides

## 📄 License

This project is licensed under the MIT License - see LICENSE file for details.

## 👨‍💻 Authors

**Satish Hugar**
- Senior Java Developer
- Full Stack Engineer
- UI/UX Designer

## 🎉 Acknowledgments

Thanks to the Spring Boot, Bootstrap, and open-source community for incredible tools and libraries.

---

**Made with ❤️ for SatPall Crochet**

Version: 1.0.0
Last Updated: 2024
