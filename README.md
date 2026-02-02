This is a webservice that calculate the basket of a bookstore, depending on the basket discount can be applied.

This application is written in Java 17

To compile it execute: mvn clean install

To run it locally use the command: mvn spring-boot:run

To test it via postman locally:
base_url= http://localhost:8080/api
to access to the service add "/price" 
and don't forget the body, an example is available int the resources folder "body_example.json" 

I added some more checks in case of encoding errors and make it more general in case if the price is different (example: damaged book, previous discount)

I didn't commit the first steps, I will explain it here (sorry for that)

I initially considered using a Map element when receiving the web service. However, the body would be difficult to read and write, so I opted for a list. 
Then I considered different problematic scenarios: an empty list, a null or zero amount, or a negative amount.
Next, I wondered what would happen if I included the same book twice, with different quantities or not.
I wrote the UnitTests after that the code, forgot the merge, added the merge adapt the tests, test locally also.