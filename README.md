# onboardingExercise

# Missing requirements: MongoDb - WEbFlux

-First to configure the project go to application.properties and change the db password from 10452 to your db pass
spring.datasource.password=10452.
-Then make sure that you have Mysql version 8 or change the org.hibernate.dialect.MySQL8Dialect

Now the application should run normally.

My approach to this exercise was the following:
  -Once we run the project a scheduler will autocheck if the db is empty and will go and call the 3 apis and fill our db with all the relations.
  -the 3 apis get-all-{photos,users,albums} will return huge amount of data, and they was for testing purposes.
  
Now run the project and wait till you receive log saying db fetched successfully and you are ready to go.
First go to login api and make sure the username and password are both "admin","admin".
Once you send you should receive an access token that will give you authentication to access the other apis for 20min.
Copy this token and add it to every api you use.
Add it to the authorization ex: Authorization: Bearer {access-token}
GetUserDetails is a get request that gets a query parameter id and return an user with all the albums and photos of everyAlbum,
they will be returned sorted by dateUpdated as you request.

-you can delete any Album/Photo but be aware that deleting an album will delete all their childs.
-Also you can create or update each of the albums and photos and be awar that providing any false id is handled.

there may be another branch develop that I will be adding to use mongoDb and webflux as I finished my courses.
