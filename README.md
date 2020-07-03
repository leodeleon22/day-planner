# day-planner
Employee management app that that uses the [Planday API](https://openapi.planday.com/gettingstarted/authorization#authorization-flow-for-customers) as a backend


## How to build
Obtain the refresh token and client id and put them in your local **gradle.properties** file:

Windows: 
`C:\Users\YOUR-USERNAME\.gradle\gradle.properties`

Mac: 
`Users/YOUR-USERNAME/.gradle/gradle.properties`

	...
	PLANDAY_CLIENT_ID="YOUR-CLIENT_ID-HERE"
	PLANDAY_REFRESH_TOKEN="YOUR-REFRESH_TOKEN-HERE"
  
  
## Libraries used

Please check the file [Dependencies.kt](https://github.com/leodeleon22/day-planner/blob/master/buildSrc/src/main/kotlin/Dependencies.kt)
