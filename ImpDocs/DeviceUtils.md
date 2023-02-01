# Get Device ID and Device name
Sometimes we need to know about the device ID and Device name as a requirement, so with the below class 
example you can easily know both of them

```` 
public class DeviceUtils {
    private static final String BLUETOOTH_NAME = "bluetooth_name";
    private static final String DEFAULT_DEVICE_NAME = "device-name-default";

    private DeviceUtils() { }

    public static String getDeviceName(@NonNull Context context) {
        String name = Settings.Secure.getString(context.getContentResolver(), BLUETOOTH_NAME);
        if (StringUtils.isNullOrEmpty(name)) {
            name = DEFAULT_DEVICE_NAME;
        }

        int deviceNameMaxLength = context.getResources().getInteger(R.integer.device_list_item_max_length);
        if (name.length() <= deviceNameMaxLength) {
            return name;
        } else {
            return name.substring(0, deviceNameMaxLength);
        }
    }

    /**
     * @implNote: better to use an InstanceID here if possible, but we require GPS for that.
     *
     * @param context
     * @return The unique device ID
     */
    public static String getDeviceID(@NonNull Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }
}
````
* <b>Now to get the device ID you can simply call </b>

`` DeviceUtils.getDeviceID(context) ``

* <b>For Device name you can  call </b>
* 
  `` DeviceUtils.getDeviceName(context) ``