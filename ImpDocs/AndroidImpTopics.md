How do you find memory leaks in android Application?
Refer:->  http://blog.nimbledroid.com/2016/05/23/memory-leaks.html

What are the exceptions you gone through in android ?
1.) InflateException : This exception is thrown When an error conditions are occurred due to some error in xml file.
2.) Surface.OutOfResourceException: This exception is thrown When a surface is not created or resized.
3.) WindowManager.BadTokenException: This exception is thrown at the time of trying to add view an invalid WindowManager.LayoutParamstoken.
4.) ActivityNotFoundException
5.) WindowManager.InvalidDisplayException
6.) ArithmeticException
7.) ArrayIndexOutOfBoundException
----------------------------------------------------------------
What are the core building blocks of android?
The core building blocks of android are:
o	Activity
o	View
o	Intent
o	Service
o	Content Provider
o	Fragment etc.

------------------------------
1) Explain Android Activity lifecycle ?
2) Explain Android Fragment lifecycle ?
3) Why Fragments are preferred more than activity ?
4) What are Android App Components ?
5) Difference btw Jcenter and MavenCentral Repositories ?
6) How many ways data can be stored in Android ?
7) What is Intent ? Explain its types with example ?


In App Billing:
Plz refer :
https://developer.android.com/training/in-app-billing/preparing-iab-app.html     *********
https://developer.android.com/training/in-app-billing/preparing-iab-app.html#GetSample    ***********
Or
http://www.androidhub4you.com/2013/03/how-to-inegrate-in-app-purchase-billing.html
----------------------------------------------------------------------------
Android Sandbox : Android Application Sandbox isolates your app data and code executions from other apps.
* Every apps runs in secure sandbox environment, so that other process in the system cannot access your code or private data.
* To do this, android assigns a unique user id (UID) at kernel level to each android application and runs it is in its own process.
* The application sandbox is implemented at the OS Level.
-------------------------------------------------------------------------------------------------------------------
Android Namespace :
From developer.android.com
xmlns:android
Defines the Android namespace. This attribute should always be set to "http://schemas.android.com/apk/res/android".
* xmlns:android is for identification that this xml is used for android, not for other function.
* Namespaces uniquely identify code/libraries. If I write an api that uses all the same names and such as the android api
the only way to distinguish between my api and 	android api is to use the android namespace, or mine.
-----------------------------------------------------------------------------------------------------------------------
Difference between Soap and Rest Api.
Soap :
1)It is a standard communication protocol specification for XML-based msg xchange.
2)Soap uses different transport protocol such as Http,Smtp.
3)The main advantage of Soap is that it provides a mechanism for services to describe
  themselves to clients, and to advertise their existence.
4)In general, when you are publishing an api to the outside world that is either
complex or likely to change.Soap will be more useful.

REST :
1) Describe set of architectural principle by which data can be transmitted over
   standardize interface such as HTTP.
2) REST is always going to be faster, coz it is much more lightweight and can be
   implemented using almost any tool,leading to lower bandwidth and shorter learning curve.
3) In REST the clients have to know what to send and what to expect.
4) If the Api is simple and existing calls are not likely to change,then REST is fine.

----------------------------------------------------------------------------------------------
Task : A task is collection of activities that user interact with while performing a certain job.
OR : A task is collection of apps that user traverse while performing some job.
Ex : while sending msg, he starts with messaging application and may move to gallery or camera to attach
	pictures to send.=

Process : Activities,Services and other Components of application runs in a single process.There
		  can be some situations where we can allow to run in a different process,but this possible
		  only if the USERID for those two processes are same.
----------------------------------------------------------------------------------------------------------

