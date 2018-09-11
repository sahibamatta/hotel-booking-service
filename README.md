# hotel-booking-service

steps to run the application :
1. mvn clean install
2. nohup java -jar target/hotelbookingservice.jar &
3. Application base url : http://localhost:8088/hotelbookingservice
4. Swagger url : http://localhost:8088/hotelbookingservice/swagger-ui.html . Please use swagger to test the APIS.

consists of 2 controllers :
1. room-type-controller
2. booking-controller

room-type-controller
2 Apis :
I) GET /room/types : lists the roomtypes in the hotel along with their information.
II) GET /room/{type} : lists the information about a particular roomtype in a hotel.

booking-controller
2 Apis
I)POST /booking/select/room : request made to select a particular room from given checkin to checkout date before its booking done . So here a count is maintained which is incremneted each time a user selects a room from checin to checkout . 
Note : USer will only be able to select a room , if it is available and if it isnt blocked by any other user.
II)POST /booking/makeBooking : This request creates a booking in the backend and after successful creation decrements the count which is incremented in the /booking/select/room request.






