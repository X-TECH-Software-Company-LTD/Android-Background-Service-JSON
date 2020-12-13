# Android-Background-Service-JSON
get JSON from URL and save to storage periodically.

## Import Library to Gradle

1 - Download "Android JSON.zip" , extract and place inside your "projectName/" folder .

https://cdn.xtsmm.com/android/libraries/Android%20JSON.zip


2 - Import Module

```
File -> New -> Import Module -> select Downloaded "Android JSON" Folder
```

3 - Add to App Gradle (path : Folder Name)

```
implementation(project(path: ':Android JSON'))
```

4 - Edit Application Manifest

```
<uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED"/>

...
<application>
....
<service android:name=".MyJobIntentService" android:permission="android.permission.BIND_JOB_SERVICE"/>
<receiver android:name=".StartActivityOnBootReceiver">
<intent-filter>
  <action android:name="android.intent.action.BOOT_COMPLETED"/>
  <action android:name="android.intent.action.QUICKBOOT_POWERON"/>
</intent-filter>
</receiver>
...
</application>
...
```

5 - Download and Add these Classes to your App Path

https://cdn.xtsmm.com/android/libraries/Background-JSON.zip

6 - Start Service at your MainActivity.class

```
jobScheduler=(JobScheduler)getSystemService(JOB_SCHEDULER_SERVICE);
serviceIntent=new Intent(getApplicationContext(),MyJobIntentService.class);
MyJobIntentService.enqueueWork(MainActivity.this,serviceIntent);
```

7 - Change JSON URL at MyJobIntentService.class

```
//find the line
"https://............json"
// and change it to your json

```

8 - Change JSON saving local path at MyJobIntentService.class

```
//find the method writeToFile(String name,String data) and change to desire path

```
