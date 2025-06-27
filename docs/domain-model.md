# Domain Model for Book Rent App

## Entities

### User
- id (UUID)
- name
- email
- passwordHash

### Book
- id (UUID)
- title
- author
- location (lat, lng)
- available (boolean)

### Rental
- id (UUID)
- bookId
- renterId
- renteeId
- deadline (timestamp)
- returnedAt

### Message
- id
- senderId
- receiverId
- content
- sentAt

### Donation
- id
- rentalId
- amount
- commission
