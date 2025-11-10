# Loan Management System (LMS) – Spring Boot Backend

This project is a backend implementation of a **Loan Management System (LMS)** developed using **Spring Boot**, **Hibernate/JPA**, and **Oracle SQL**.  
It includes modules for loan application, approval workflow, EMI calculation, customer management, and secure authentication.

The system is designed for banks/financial institutions to manage customers, loan products, loan approval, repayment schedules, and loan status tracking.

---

## 🚀 Features

### ✅ Customer Management
- Add new customers  
- Update customer information  
- View customer details  
- Maintain customer loan history  

### ✅ Loan Products
- Create different loan types (Home Loan, Personal Loan, Education Loan, etc.)  
- Define interest rate, tenure, processing fee  
- Fetch loan product details  

### ✅ Loan Application Workflow
- Customer applies for a loan  
- Loan verification steps (basic workflow)  
- Admin/Manager can approve or reject loan  
- Store application status (PENDING, APPROVED, REJECTED)  

### ✅ EMI Calculation
- Auto-calculate EMI using formula  
- Generate repayment schedule  
- Track upcoming EMIs  
- Update payment status  

### ✅ Authentication & Security
- JWT-based login  
- Role-based access (Customer, LoanOfficer, Admin)  
- Secure protected endpoints  

---

## 🧱 Tech Stack

| Layer | Technology |
|-------|------------|
| Backend | Java 8+, Spring Boot |
| Security | Spring Security + JWT |
| ORM | Hibernate / JPA |
| Database | Oracle SQL |
| Build Tool | Maven |
| Testing Tool | Postman |

---

## ⚙️ Getting Started

### ✅ 1. Clone the Repository
```bash
git clone https://github.com/<your-username>/<repo-name>.git
cd <repo-name>


