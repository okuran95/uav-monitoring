UAV Monitoring App

JDK 21

Postgresql (Optional)

An application that allows listing, adding, updating and deleting unmanned aerial vehicles. Developed with java swing

When the application is run, 25 fake records are created.

By default the application is not connected to the database. It performs operations on a static list that is accessed throughout the project. If you want to connect to the database, you must update the database configs. Then, you should implement uavdao instead of mockuacdao in the uavservice.
