# Products API
This API has been built using Springboot and uses MongoDB as the datastore.
This API has two functionalities
  - For a given product ID gets the decription and current price information.
  - For a give product ID updates the current price information
  
Pre-requisites
---------------
- Java 8 installed
- MongoDB installed

Setting up the service
-----------------------
  - Clone the code from Github
  - Open the project in an IDE (Eclipse/Intellij)
  - Edit Run configuration and add a new Java application
  - Assign a port on which service will be run
  
Insert some data in MongoDB
---------------------------
  Create a DB & Collections called product
  Open a Mongo shell and run the following script:
  
  db.getCollection('product').insertMany([{
    "productId" : 13860428,
    "value" : 123,
    "currencyCode" : "USD"
  },
  {
    "productId" : 15117729,
    "value" : 467,
    "currencyCode" : "USD"
  },
  {
    "productId" : 16696652,
    "value" : 789,
    "currencyCode" : "USD"
  }]
  )
Running the API:
-----------------
  **GET METHOD**
  - Start the API in the IDE
  - Open a Rest Client
  - Hit the URL: http://localhost:< port no that has been assigned >/products/{id}
  
  Example ids - 13860428, 15117729, 16696652
  
**PUT METHOD**
  - Start the API in the IDE
  - Open a Rest Client
  - Hit the URL: http://localhost:< port no that has been assigned >/products/{id}
  
  Example ids - 13860428, 15117729, 16696652
  Example RequestBody - {"value":1500.00, "currencyCode":"INR"}
  
