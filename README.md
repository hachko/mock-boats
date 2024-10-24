# mock-boats
Fullstack mock project with boats (crud)

>2 folders : 
>> back : springboot back-end
<br>
>> front : angular front-end

>Spring boot back-end  : 
>>Bootstrap the Springboot application using your ide by running BoatsApplication.java
>>* An h2 embedded database mocks some initial data for boats and authorized users
>>* default rest url is localhost:8080
>>* 2 Authorized users (login:password):
>>    * user:user
>>    * admin:admin
>>* "user" can access with basic auth to boat crud endpoints only
>>* Likewise, "admin" can additionnaly access user crud endpoints.

>front-end project :
>>ng serve in ./front folder
<br>
>>default url is localhost:4200
>>* You can authenticate and access the whole app in login screen using user:user
>>* if you want to administrate users, use directly back-end endpoints using an http client like Postman
>>* An admin section in front end will be added (branch : [7-add-a-small-crud-for-user-management](https://github.com/hachko/mock-boats/tree/7-add-a-small-crud-for-user-management))
