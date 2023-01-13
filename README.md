# ticketing

# Event booking mobile app

## Tech Stack Used:
- Kotlin
- Android Studio
- Room Database
- MVVM Architecture with Data binding and View Binding
- Glide – image library for android

## Tables Used (Room Database – SQLite based):
### Event:
- eventID : Primary Key
- name 
- Description
- typeEvent : Comedy Show, Movies, Play
- Photo – url of banner
- Seating – contains string based 2d array converted using type convertor to store 2D array in Room Database
- Date
### Order
- orderID: Primary Key
- eventID
- Status – Confirmed / Cancelled
- orderSeats -  2D matrix with details of seats booked converted to String for storage in Room
- userID: String // Can be used to further enhance project constant string for now
