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

<img width="300" alt="image" src="https://user-images.githubusercontent.com/64903405/212312123-5d61c0a8-57e9-42fe-a4f7-199c366ed7b5.png">
<img width="310" alt="image" src="https://user-images.githubusercontent.com/64903405/212312230-a804d4bb-aa5d-43d0-9c5c-a79dbb620c06.png">
