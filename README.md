This is a web application that allows customers place order from their fav restaurant 

 API
what a service provider can do: 
- create account   (working)
- login   (working)
- add meal   (working)


- verify customer
- update profile
- delete a meal
- edit a meal
- send push notifications to all my customers
- get all customers info that has ordered from me

--------------------------------------------------------------------------------

what a customer can do on the platform:
- create account (working)
- login  (working)
- update profile  (working)
- find all the service providers in a city  (working)
- place order (working)


- find customer by date (not working)
- verify customer
- pay with paystack 



to do
- service provider can be in diff cities
- find all service providers in a city, you are passing a string
- add a type of food created by service provider
- add a order status
- create a cart class, add order to cart 
- no customer_order and order mapping in db
- for the update api, use path param for the email 

LocalDateTime localDateTime = "12/23/2021"
localDateTime = LocalDateTime.parse(dateTime.toUpperCase()),
                         DateTimeFormatter.ofPattern("M/d/yyyy h:mm a", Locale.US));

  .getDateTime().format(DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a"));


private LocalDate today;

LocalDateTime localDateTime = DateTimeConverter.convertStringToDateTime(dateTime, today);



