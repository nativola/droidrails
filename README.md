## Basic Android REST App consuming API Ruby on Rails and Mongodb 

Basic Android and Rails project setup using retrofit for consume API Rails

### Setup API environment without Vagrant(again, you MUST use it!)
* Install Mongo DB
* Install Ruby
* Clone the project and run
~~~
~$ cd apiRoR && bundle install
~~~
* then 
~~~
~$ rails s
~~~

Now you have the server running in your local enviroment.

### Setup Android App
* Install Android Studio
* Open the project called /AndroidRestClient with Android Studio 
* this is an **important**  part, update the config file, app/src/main/java/config/Config.java , set your ip address
```java
  public final static String APIURL = "http://192.168.0.0:3000";
```

### Contributors

* @HanzelCruz
