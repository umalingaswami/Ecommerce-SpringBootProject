---
title: Functional spec Admin Controller
---
# Introduction

| Aspect           | Description                                                                                          |
| ---------------- | ---------------------------------------------------------------------------------------------------- |
| Application      | Web-based admin and user management system for product, category, and user profile handling.         |
| Business context | Supports admin and user login, product and category CRUD, and user profile management.               |
| Objectives       | Enable secure access control, manage product catalog and categories, and allow user profile updates. |

---

# UC-001 | User login and session management

| Description    | Allows users to log in with credentials and maintains session state for access control.          | | Primary Actor  | End user                                                                                         | | Preconditions  | User must have valid credentials stored in the system.                                           | | Postconditions | User is authenticated and redirected to the main index page; session state is updated.           |

### Basic flow

| Step | User Actions                       | System Actions                                           |
| ---- | ---------------------------------- | -------------------------------------------------------- |
| 1    | User navigates to login page       | System displays login form                               |
| 2    | User submits username and password | System validates credentials against stored user data    |
| 3    | Credentials valid                  | System sets session username and redirects to index page |

### Alternate flow

| Step | User Actions                     | System Actions                                       |
| ---- | -------------------------------- | ---------------------------------------------------- |
| 1    | User submits invalid credentials | System displays error message and reloads login page |

### Exception flow

| Step | User Actions              | System Actions                               |
| ---- | ------------------------- | -------------------------------------------- |
| 1    | Database connection fails | System logs exception and reloads login page |

### Business rules

| Rule ID | Description                                                     |
| ------- | --------------------------------------------------------------- |
| BR-001  | Only users with matching username and password can log in.      |
| BR-002  | Session state must track logged-in username for access control. |

### Validation rules

| Rule ID | Description                                     |
| ------- | ----------------------------------------------- |
| VR-001  | Username and password fields must not be empty. |

### Entities and data model

| Entity | Attributes                                 | Relationships | Validation rules               | Error messages                 |
| ------ | ------------------------------------------ | ------------- | ------------------------------ | ------------------------------ |
| User   | userid, username, password, email, address | None          | Username and password required | "Invalid Username or Password" |

### Validation and testing

| Scenario         | Test data                  | Expected result                   |
| ---------------- | -------------------------- | --------------------------------- |
| Valid login      | Existing username/password | Redirect to index page            |
| Invalid login    | Wrong username/password    | Show error message                |
| Database failure | Simulated DB down          | Show login page with error logged |

---

# UC-002 | Admin login and access control

| Description    | Allows admin to log in with fixed credentials and access admin-specific pages.                 | | Primary Actor  | Admin user                                                                                      | | Preconditions  | Admin credentials are predefined and known.                                                    | | Postconditions | Admin session state is set; admin can access admin home and management pages.                  |

### Basic flow

| Step | User Actions                        | System Actions                                             |
| ---- | ----------------------------------- | ---------------------------------------------------------- |
| 1    | Admin navigates to admin login page | System displays admin login form                           |
| 2    | Admin submits username and password | System checks credentials against fixed values             |
| 3    | Credentials valid                   | System sets admin session flag and redirects to admin home |

### Alternate flow

| Step | User Actions                      | System Actions                                             |
| ---- | --------------------------------- | ---------------------------------------------------------- |
| 1    | Admin submits invalid credentials | System displays error message and reloads admin login page |

### Exception flow

| Step | User Actions                    | System Actions                                     |
| ---- | ------------------------------- | -------------------------------------------------- |
| 1    | System error during login check | System logs exception and reloads admin login page |

### Business rules

| Rule ID | Description                                               |
| ------- | --------------------------------------------------------- |
| BR-003  | Admin login requires username "admin" and password "123". |
| BR-004  | Admin session flag controls access to admin-only pages.   |

### Validation rules

| Rule ID | Description                                           |
| ------- | ----------------------------------------------------- |
| VR-002  | Admin username and password fields must not be empty. |

### Entities and data model

| Entity | Attributes         | Relationships | Validation rules                     | Error messages                 |
| ------ | ------------------ | ------------- | ------------------------------------ | ------------------------------ |
| Admin  | username, password | None          | Fixed username and password required | "Invalid Username or Password" |

### Validation and testing

| Scenario            | Test data                      | Expected result             |
| ------------------- | ------------------------------ | --------------------------- |
| Valid admin login   | Username: admin, Password: 123 | Redirect to admin home page |
| Invalid admin login | Wrong credentials              | Show error message          |

---

# UC-003 | Category management (add, update, delete)

| Description    | Enables admin to add, update, and delete product categories.                                  | | Primary Actor  | Admin user                                                                                      | | Preconditions  | Admin must be logged in.                                                                        | | Postconditions | Categories are created, updated, or removed from the system accordingly.                       |

### Basic flow (Add category)

| Step | User Actions                    | System Actions                      |
| ---- | ------------------------------- | ----------------------------------- |
| 1    | Admin submits new category name | System inserts category into system |

### Basic flow (Update category)

| Step | User Actions                           | System Actions                         |
| ---- | -------------------------------------- | -------------------------------------- |
| 1    | Admin submits category ID and new name | System updates category name in system |

### Basic flow (Delete category)

| Step | User Actions                              | System Actions                      |
| ---- | ----------------------------------------- | ----------------------------------- |
| 1    | Admin requests deletion of category by ID | System removes category from system |

### Exception flow

| Step | User Actions             | System Actions                                       |
| ---- | ------------------------ | ---------------------------------------------------- |
| 1    | Database operation fails | System logs exception and redirects to category page |

### Business rules

| Rule ID | Description                                      |
| ------- | ------------------------------------------------ |
| BR-005  | Only logged-in admins can manage categories.     |
| BR-006  | Category names must be unique within the system. |

### Validation rules

| Rule ID | Description                                              |
| ------- | -------------------------------------------------------- |
| VR-003  | Category name must not be empty when adding or updating. |

### Entities and data model

| Entity   | Attributes       | Relationships | Validation rules         | Error messages           |
| -------- | ---------------- | ------------- | ------------------------ | ------------------------ |
| Category | categoryid, name | None          | Name required and unique | "Category name required" |

### Validation and testing

| Scenario                     | Test data                         | Expected result               |
| ---------------------------- | --------------------------------- | ----------------------------- |
| Add category with valid name | New unique category name          | Category added successfully   |
| Add category with empty name | Empty string                      | Validation error              |
| Update category name         | Existing category ID and new name | Category updated successfully |
| Delete category              | Existing category ID              | Category removed successfully |

---

# UC-004 | Product management (add, update, delete)

| Description    | Allows admin to add new products, update existing products, and delete products.              | | Primary Actor  | Admin user                                                                                      | | Preconditions  | Admin must be logged in; product categories must exist.                                        | | Postconditions | Products are created, updated, or removed accordingly.                                         |

### Basic flow (Add product)

| Step | User Actions                                          | System Actions                                         |
| ---- | ----------------------------------------------------- | ------------------------------------------------------ |
| 1    | Admin submits product details including category name | System resolves category ID and inserts product record |

### Basic flow (Update product)

| Step | User Actions                                 | System Actions                            |
| ---- | -------------------------------------------- | ----------------------------------------- |
| 1    | Admin submits product ID and updated details | System updates product record accordingly |

### Basic flow (Delete product)

| Step | User Actions                             | System Actions                |
| ---- | ---------------------------------------- | ----------------------------- |
| 1    | Admin requests deletion of product by ID | System removes product record |

### Exception flow

| Step | User Actions             | System Actions                                      |
| ---- | ------------------------ | --------------------------------------------------- |
| 1    | Database operation fails | System logs exception and redirects to product page |

### Business rules

| Rule ID | Description                                            |
| ------- | ------------------------------------------------------ |
| BR-007  | Products must be associated with an existing category. |
| BR-008  | Only logged-in admins can manage products.             |

### Validation rules

| Rule ID | Description                                                                                        |
| ------- | -------------------------------------------------------------------------------------------------- |
| VR-004  | Product name, category, price, quantity, and description must be provided when adding or updating. |

### Entities and data model

| Entity  | Attributes                                                        | Relationships                    | Validation rules         | Error messages               |
| ------- | ----------------------------------------------------------------- | -------------------------------- | ------------------------ | ---------------------------- |
| Product | id, name, image, categoryid, quantity, price, weight, description | Linked to Category by categoryid | Required fields as above | "Product details incomplete" |

### Validation and testing

| Scenario                    | Test data                        | Expected result              |
| --------------------------- | -------------------------------- | ---------------------------- |
| Add product with valid data | Complete product info            | Product added successfully   |
| Update product details      | Existing product ID and new data | Product updated successfully |
| Delete product              | Existing product ID              | Product removed successfully |

---

# UC-005 | User profile display and update

| Description    | Allows logged-in users to view and update their profile information.                           | | Primary Actor  | End user                                                                                       | | Preconditions  | User must be logged in.                                                                         | | Postconditions | User profile data is displayed or updated accordingly.                                        |

### Basic flow (Display profile)

| Step | User Actions               | System Actions                                  |
| ---- | -------------------------- | ----------------------------------------------- |
| 1    | User requests profile page | System retrieves user data and displays profile |

### Basic flow (Update profile)

| Step | User Actions                      | System Actions                                            |
| ---- | --------------------------------- | --------------------------------------------------------- |
| 1    | User submits updated profile data | System updates user record and refreshes session username |

### Exception flow

| Step | User Actions          | System Actions                                 |
| ---- | --------------------- | ---------------------------------------------- |
| 1    | Database update fails | System logs exception and reloads profile page |

### Business rules

| Rule ID | Description                                            |
| ------- | ------------------------------------------------------ |
| BR-009  | Users can only update their own profile information.   |
| BR-010  | Username changes update the session state accordingly. |

### Validation rules

| Rule ID | Description                               |
| ------- | ----------------------------------------- |
| VR-005  | Email must be in valid format.            |
| VR-006  | Password must meet security requirements. |

### Entities and data model

| Entity | Attributes                                 | Relationships | Validation rules                   | Error messages              |
| ------ | ------------------------------------------ | ------------- | ---------------------------------- | --------------------------- |
| User   | userid, username, email, password, address | None          | Email format and password strength | "Invalid email or password" |

### Validation and testing

| Scenario                          | Test data                 | Expected result                       |
| --------------------------------- | ------------------------- | ------------------------------------- |
| Display profile                   | Logged-in user            | Profile data shown                    |
| Update profile with valid data    | New username, email, etc. | Profile updated and session refreshed |
| Update profile with invalid email | Invalid email format      | Validation error                      |

---

# Business rules summary

| Use Case ID | Rule ID | Description                                                     | Conditions                    |
| ----------- | ------- | --------------------------------------------------------------- | ----------------------------- |
| UC-001      | BR-001  | Only users with matching username and password can log in.      | User submits credentials      |
| UC-001      | BR-002  | Session state must track logged-in username for access control. | After successful login        |
| UC-002      | BR-003  | Admin login requires username "admin" and password "123".       | Admin login attempt           |
| UC-002      | BR-004  | Admin session flag controls access to admin-only pages.         | After successful admin login  |
| UC-003      | BR-005  | Only logged-in admins can manage categories.                    | Admin category management     |
| UC-003      | BR-006  | Category names must be unique within the system.                | Adding or updating categories |
| UC-004      | BR-007  | Products must be associated with an existing category.          | Adding or updating products   |
| UC-004      | BR-008  | Only logged-in admins can manage products.                      | Admin product management      |
| UC-005      | BR-009  | Users can only update their own profile information.            | Profile update                |
| UC-005      | BR-010  | Username changes update the session state accordingly.          | Profile update                |

---

# Integration requirements

| Integration Aspect     | Description                                                                  |
| ---------------------- | ---------------------------------------------------------------------------- |
| Database               | MySQL database stores users, products, categories, and session-related data. |
| Communication protocol | HTTP/HTTPS for web requests and responses.                                   |
| Data exchange format   | Form data submitted via HTTP POST and GET requests.                          |
| Authentication         | Session-based authentication for users and admins.                           |

---

# Use case dependencies

|                                   | User login and session management | Admin login and access control | Category management | Product management | User profile display and update |
| :-------------------------------: | :-------------------------------: | :----------------------------: | :-----------------: | :----------------: | :-----------------------------: |
| User login and session management |                 X                 |                                |                     |                    |                                 |
|   Admin login and access control  |                                   |                X               |                     |                    |                                 |
|        Category management        |                                   |                X               |          X          |                    |                                 |
|         Product management        |                                   |                X               |          X          |          X         |                                 |
|  User profile display and update  |                 X                 |                                |                     |                    |                X                |

---

# Use case to table dependencies

| Use Case                          | User Table | Category Table | Product Table |
| --------------------------------- | :--------: | :------------: | :-----------: |
| User login and session management |      X     |                |               |
| Admin login and access control    |            |                |               |
| Category management               |            |        X       |               |
| Product management                |            |        X       |       X       |
| User profile display and update   |      X     |                |               |

---

# Activity diagram for user login and session management

@startuml start :User navigates to login page; note right Displays login form for user input end note :User submits username and password; if (Credentials valid?) then (yes) :Set session username; note right Store logged-in user info for access control end note :Redirect to index page; else (no) :Display error message; note left Inform user of invalid credentials end note :Reload login page; endif stop @enduml

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBRWNvbW1lcmNlLVNwcmluZ0Jvb3RQcm9qZWN0JTNBJTNBdW1hbGluZ2Fzd2FtaQ==" repo-name="Ecommerce-SpringBootProject"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
