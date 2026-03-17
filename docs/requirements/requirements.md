# Requirement Document: Online Grocery Order Management System

---

## 1. Introduction

### 1.1 Purpose
The purpose of this document is to describe the requirements for the **Online Grocery Order Management System**. It defines system functionality, constraints, and expected behavior.

### 1.2 Scope
A web-based application allowing customers to browse grocery products, manage carts, place orders, and make payments, while administrators manage inventory, users, and reports.

### 1.3 Objectives
- Provide a convenient online shopping experience  
- Enable efficient product and order management  
- Ensure secure transactions  
- Support business analytics for admins  

---

## 2. Stakeholders

### Primary Stakeholders
- **Customers (Users)** – Purchase groceries online  
- **Admin** – Manage system operations  

### Secondary Stakeholders
- **System Owner / Manager** – Monitor performance and revenue  
- **Delivery Personnel (Optional)** – Handle deliveries  

---

## 3. System Overview

The system consists of the following modules:

- **User Management**
- **Product Management**
- **Cart Management**
- **Order Management**
- **Payment Management**
- **Admin Dashboard**

---

## 4. Functional Requirements

### 4.1 User Management
- [x] User registration  
- [x] Login / Logout  
- [x] Profile update (email, password, address)  
- [x] Delete user account  
- [x] Admin can view all users  

---

### 4.2 Product Management
- [x] Add new products  
- [x] Update product details (price, stock)  
- [x] Delete products  
- [x] View product list  
- [x] Search products  
- [x] Filter by category and price  
- [x] Display "Out of Stock" items  
- [x] Low stock alerts  

---

### 4.3 Cart Management
- [x] Add items to cart  
- [x] Update item quantity  
- [x] Remove items from cart  
- [x] Automatic total price calculation  

---

### 4.4 Order Management
- [x] Place orders  
- [x] View order history  
- [x] Cancel orders  
- [x] Update order status (Pending / Delivered)  
- [x] Invoice generation (Subtotal, Discount, Delivery Fee, Total)  
- [x] Delivery tracking  

---

### 4.5 Payment Management
- [x] Payment processing (Card / Cash on Delivery)  
- [x] Apply promo codes (e.g., SAVE10, FLAT500)  
- [x] Discount calculation  
- [x] Payment history  

---

### 4.6 Admin Management
- [x] Manage users, products, and orders  
- [x] Generate sales reports  
- [x] Monitor stock levels  
- [x] View most sold products  

---

## 5. Non-Functional Requirements

### Performance
- [x] System response time within 2–3 seconds  
- [x] Support multiple concurrent users  

### Security
- [x] Password encryption  
- [x] Secure authentication  
- [x] Prevent duplicate accounts  

### Usability
- [x] User-friendly interface  
- [x] Easy navigation  

### Reliability
- [x] No failures during transactions  
- [x] Data consistency ensured  

### Scalability
- [x] Supports system growth (users/products)  

---

## 6. Assumptions and Constraints

### Assumptions
- Users have internet access  
- Users are familiar with online shopping  

### Constraints
- Web-based system only  
- Limited to card and cash-on-delivery payments  

---

## 7. User Stories

### Customer
- As a user, I want to register so I can access the system  
- As a user, I want to search products easily  
- As a user, I want to add items to a cart  
- As a user, I want to place orders and make payments  
- As a user, I want to view my order history  

### Admin
- As an admin, I want to manage products so inventory is accurate  
- As an admin, I want to view orders to track sales  
- As an admin, I want to generate reports to analyze performance  

---

## 8. Use Cases

### Use Case: User Registration
1. User enters details  
2. System validates input  
3. Account is created  

---

### Use Case: Place Order
1. User logs in  
2. Adds products to cart  
3. Proceeds to checkout  
4. Applies promo code (optional)  
5. Makes payment  
6. Order is confirmed  

---

### Use Case: Manage Products (Admin)
1. Admin logs in  
2. Adds/updates/deletes product  
3. System updates product catalog  

---

## 9. Conclusion

This document defines the requirements for the **Online Grocery Order Management System** and serves as a foundation for design, development, and testing.

---