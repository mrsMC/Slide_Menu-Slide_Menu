package app.z0nen.slidemenu;

/**
 * Created by mel76 on 16/03/2016.
 */
import android.content.Context;
import android.content.Intent;

public class CommonUtilities {

    // give your server registration url here
    static final String SERVER_URL = "http://82.141.235.157:50010/mApp/register.php";

    // Google project id
    static final String SENDER_ID = "945604381803";

    /**
     * Tag used on log messages.
     */
    static final String TAG = "FuelWatch GCM";

    static final String DISPLAY_MESSAGE_ACTION =
            "app.z0nen.slidemenu.DISPLAY_MESSAGE";

    static final String EXTRA_MESSAGE = "message";

    /**
     * Notifies UI to display a message.
     * <p>
     * This method is defined in the common helper because it's used both by
     * the UI and the background service.
     *
     * @param context application's context.
     * @param message message to be displayed.
     */
    static void displayMessage(Context context, String message) {
        Intent intent = new Intent(DISPLAY_MESSAGE_ACTION);
        intent.putExtra(EXTRA_MESSAGE, message);
        context.sendBroadcast(intent);
    }
}
