# onboardingExercise

# Missing requirements: MongoDb - WEbFlux

-First to configure the project go to application.properties and change the db password from 10452 to your db pass<br />
spring.datasource.password=10452 and connect to your mysql localhost and write the command "create root;"<br />
-Then make sure that you have Mysql version 8 or change the org.hibernate.dialect.MySQL8Dialect<br />

Now the application should run normally.<br />

My approach to this exercise was the following:<br />
  -Once we run the project a scheduler will autocheck if the db is empty and will go and call the 3 apis and fill our db with all the relations.<br />
  -the 3 apis get-all-{photos,users,albums} will return huge amount of data, and they was for testing purposes.<br />
  
Now run the project and wait till you receive log saying db fetched successfully and you are ready to go.<br />
First go to login api and make sure the username and password are both "admin","admin".<br />
Once you send you should receive an access token that will give you authentication to access the other apis for 20min.<br />
Copy this token and add it to every api you use.<br />
Add it to the authorization ex: Authorization: Bearer {access-token}<br />
GetUserDetails is a get request that gets a query parameter id and return an user with all the albums and photos of everyAlbum,<br />
they will be returned sorted by dateUpdated as you request.<br />

-you can delete any Album/Photo but be aware that deleting an album will delete all their childs.<br />
-Also you can create or update each of the albums and photos and be awar that providing any false id is handled.<br />

there may be another branch develop that I will be adding to use mongoDb and webflux as I finished my courses.<br />
