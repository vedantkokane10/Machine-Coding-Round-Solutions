# Food Ordering System – Machine Coding (OOP Design)

## Problem Statement

Design and implement a **Food Ordering System** for a restaurant platform using **Object-Oriented Programming (OOP)** principles.

The system should allow **Customers** to place food orders from **Restaurants**, manage restaurant catalogs, and track orders.  
All data must be stored **in-memory** (no external databases).

---

## Actors / Entities

- Customer  
- Restaurant  
- Item  
- Order  
- FoodOrderingSystem (Service / Manager class)

---

## Functional Requirements

### 1. User Registration
- A customer should be able to register on the platform.
- Each customer must have a unique `userId`.


---

### 2. Restaurant Registration
- A restaurant should be able to register on the platform.
- Each restaurant must have a unique `restaurantId`.


---

### 3. Catalog Management (Restaurant)
- Restaurants can add items to their catalog.
- Each item has:
  - itemName
  - price
  - availableQuantity

---

### 4. Search Items (Customer)
- Customers can search items within a specific restaurant.
- Search results must be sorted by **price (ascending)**.

---

### 5. Place Order (Customer)
- Customers can place an order for a specific item from a restaurant.
- Quantity must be validated against available stock.
- On successful order placement:
  - An `orderId` is generated
  - Inventory is updated

---

### 5. Place Order (Customer)
- Customers can place an order for a specific item from a restaurant.
- Quantity must be validated against available stock.
- On successful order placement:
  - An `orderId` is generated
  - Inventory is updated

---

### 6. Get Orders (Customer)
- Customers can view all orders placed by them.
- Each order should display:
  - orderId
  - itemName
  - quantity
  - orderStatus (CONFIRMED / CANCELLED)

---

### 7. Bonus: Cancel Order
- Customers can cancel an order.
- Cancellation rules:
  - Only CONFIRMED orders can be cancelled
  - Inventory should be restored on cancellation

---

## Assumptions

- Users have unlimited money (no wallet or payment handling).
- One order contains only one item.
- Multiple orders can exist for the same user.
- All operations are performed in-memory.

---

## Expected OOP Design

### Entities
- User
- Restaurant
- Item
- Order
- OrderStatus (enum)

### Relationships
- A restaurant owns a catalog of items
- A user owns multiple orders
- An order references a restaurant and an item

### Design Principles
- Encapsulation
- Single Responsibility Principle
- Clear separation of concerns
- Proper use of enums
- Meaningful method and variable names

---

## Driver / Main Class

- A `main()` method (or test class) must be provided.
- Inputs can be hard-coded.
- The evaluator should be able to run the program and see:
  - Registration logs
  - Catalog updates
  - Search results
  - Order placement
  - Order cancellation

---

## Sample Flow
restaurantRegistration("Donald", "GST10905804580", "donald@mail.com
", 1234567890)

addItemsInCatalog("Donald", "Sandwich", 100.0, 5)
addItemsInCatalog("Donald", "Burger", 250.0, 2)

userRegistration("User1", "user@mail.com
", 9876543210)

searchItem("Donald", "Sandwich")

placeOrder(556473942, "Donald", "Sandwich", 2)

getOrders(556473942)

cancelOrder(67234667) 


---

## Evaluation Criteria

### Functional Correctness
- All features work as expected
- Edge cases handled:
  - Invalid IDs
  - Insufficient quantity
  - Duplicate or invalid cancellations

### Object-Oriented Design
- Clean class structure
- Logical separation of responsibilities
- Proper use of collections and enums

### Code Quality
- Readable and maintainable code
- Meaningful method and variable names
- Graceful error handling
