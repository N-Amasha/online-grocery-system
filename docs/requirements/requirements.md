# Requirement Document: Online Grocery Order Management System

---

## 1. Introduction

### 1.1 Purpose
The purpose of this document is to describe the requirements for the **Online Grocery Order Management System**, including functionality, constraints, and system behavior.

### 1.2 Scope
A web-based application allowing customers to browse grocery products, manage carts, place orders, and make payments, while administrators handle inventory and reporting.

### 1.3 Objectives
- Provide a reliable online grocery shopping experience 
- Enable accurate inventory and order processing 
- Maintain secure transactions  
- Support efficient admin operations   

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
- [x] Admin CRUD operations for products  
- [x] Users can search and filter products   
- [x] Display stock availability    
- [x] Low stock alerts  
- [x] Stock reconciliation support for accurate inventory tracking  

**Enhancement: Real-Time Inventory Sync**
- [x] System updates stock instantly to prevent "Ghost Stock"  
- [x] Automatically marks items as "Out of Stock" when unavailable 

---

### 4.3 Cart Management
- [x] Add/remove items    
- [x] Update quantities     
- [x] Automatic total price calculation  

---

### 4.4 Order Management
- [x] Place and cancel orders  
- [x] View order history  
- [x] Track order status    
- [x] Generate invoices   
- [x] Support actual weight input during order processing  
- [x] Automatically adjust final order price based on measured weight  

**Enhancement: Substitution Logic**
- [x] Allow customers to select substitute items  
- [x] System automatically replaces unavailable products based on user preference  

---

### 4.5 Payment Management
- [x] Payment processing (Card / Cash on Delivery)  
- [x] Apply promo codes (e.g., SAVE10, FLAT500)  
- [x] Discount calculation  
- [x] View payment history  

**Enhancement: Price Adjustment System**
- [x] Apply safety buffer for variable-weight items (e.g., vegetables)  
- [x] Adjust final price after order confirmation  
- [x] Refund excess charged amount if necessary  

---

### 4.6 Admin Management
- [x] Manage users, products, and orders  
- [x] Generate reports  
- [x] Monitor stock    

**Enhancement: Staff-Friendly Dashboard**
- [x] Simple and clear interface for quick order processing  
- [x] Real-time updates for inventory and orders  

---

### 4.7 Additional System Requirements

#### REQ-01: Stock Reconciliation
- [x] The system shall provide a mechanism for administrators to reconcile digital inventory with physical stock levels  
- [x] This ensures real-time accuracy and prevents "Ghost Stock" issues  
- [x] The system must log discrepancies and allow manual correction  

---

#### REQ-02: Variable Weight Entry
- [x] The system shall allow staff to input the exact weight of variable items (e.g., vegetables, meat) during packing  
- [x] The system shall automatically recalculate the final order price based on actual weight  
- [x] Any price difference must be adjusted (charged/refunded) accordingly  

---

## 5. Non-Functional Requirements

### Performance
- [x] System response time within 2–3 seconds  
- [x] Support multiple concurrent users  

### Data Accuracy
- [x] Prevent "Ghost Stock" using real-time updates  
- [x] Ensure consistent stock levels across system  

### Security
- [x] Password encryption  
- [x] Secure authentication  
- [x] Protection against Account Takeovers (ATO)  
- [x] Input validation and session management  

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
- No external API integrations  

---

## 7. Key System Challenges & Impact

### Data Accuracy Issues
- "Ghost Stock" may lead to order cancellations  
- Real-time synchronization is required  

### Concurrency Issues
- Multiple users ordering the same item can cause "Race Conditions"  
- System must prevent overselling  

### Performance Challenges
- High traffic may slow down system  
- Efficient processing required  

### Security Risks
- Risk of Account Takeovers (ATO)  
- Requires strong authentication and validation  

---

## 8. User Stories

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

## 9. Additional Enhancements (Bonus Features)

This document defines the requirements for the **Online Grocery Order Management System** and serves as a foundation for design, development, and testing.

### Smart Search
- [x] Fuzzy search to handle typos 

### Buy It Again
- [x] One-click reorder of previous purchases

### Route Optimization
- [x] Optimize delivery routes for faster service  

---

## 10. Conclusion

This document defines both basic and advanced requirements for the system, addressing real-world challenges such as inventory accuracy, concurrency, and security. It provides a strong foundation for building a reliable and scalable solution.

---
