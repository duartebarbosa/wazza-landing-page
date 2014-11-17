
#Technical Documentation
 
## Getting Started  

### Registration
 
 Head on to the [register] page to register your account. If you have a promocode, use it and you will get early access.
 You will receive an e-mail whenever your account is activated and you may begin to use it as you will.

### Integration
 
 You can gain deep insight into your In-App Business very quickly and with little effort using Wazza. The integration process is designed to be as easy as possible with basic setup complete in under 5 minutes.

- Android
    1. Download the Wazza Android SDK
     The archive should contain these files:
      - Wazza_Android_SDK_x.y.z.jar : The library containing Wazza's analytic collection and reporting code (where x.y.x denotes the latest version of Wazza SDK).
      - ProjectApiKey.txt : This file contains the name of your project and your project's API key. Alternatively, you can also get the key in the Dashboard.
      - README.pdf : PDF file containing instructions (This exact same information).

    2. Add the Wazza lib to your project
      - If you're using Eclipse, modify your Java Build Path, and choose Add External JAR.
      - If you're using the SDK tools directly, drop it into your libs folder and the ant task will pick it up.

    3. Configure your AndroidManifest.xml to:
      - Have access to the Internet and allow Wazza SDK to check state of the network connectivity
      - You may specify a versionName attribute in the manifest to have data reported under that version name
      - Declare min version of Android OS the application supports. Please note that Wazza supports Android OS versions 15 and above.     
      {{create gist}}

    4. Incorporate the following lines of Wazza code:
      - For each activity of your application, add:
        ```sh
          import io.wazza.sdk.android.Wazza;
        ```
        and on onCreate():
        ```sh
          Wazza wazza = Wazza.init(this, "API KEY", "Application Name", "Company Name");
          wazza.sessionOpen();
        ```
        and on onStop():
        ```sh
          wazza.sessionClose();
        ```
      - Whenever you call the Google In-app Billing service, swap that call with Wazza's one:
        ```sh
          wazza.purchase("SKU");
        ```
    
    That's it. We recommend always calling Wazza from the main (UI) thread. The results are not guaranteed or supported when called from other threads.

- Apple
  
- Other Platforms/frameworks

    The support for other platforms and frameworks is coming. Drop us an e-mail and we may be able to get you early access to new SDKs.
    
### First time use
 
 Head on to [login] page and enter your credentials.
 
 You will be redirected to the Dashboard page and prompted to add a new Mobile Application.
 
 After this, Wazza will start to crunch data and it will soon be available here.
 
 If you click in any of the available applications, you will be able to see more KPIs.
 
 In the same way, if you click any of these KPIs, you will get access to detailed information including charts.
    
[register]:http://www.wazza.io/register
[login]:http://www.wazza.io/login