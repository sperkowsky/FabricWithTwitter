package samples.twitterkit.fabric.twitter.com.wearexample;

import android.content.Intent;
import android.app.PendingIntent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Wearable;
import com.google.android.gms.wearable.WearableListenerService;


public class ListenerService extends WearableListenerService {
    private GoogleApiClient client;

    @Override
    public void onCreate() {
        super.onCreate();
        client = new GoogleApiClient.Builder(this)
                .addApi(Wearable.API)
                .build();
        client.connect();
        Wearable.MessageApi.addListener( client, this );
    }

    @Override
    public void onMessageReceived( final MessageEvent messageEvent ) {
        int notificationId = 001;

        // Build intent for notification content
        Intent viewIntent = new Intent(this, NotificationActivity.class);
        String tweet = new String(messageEvent.getData());
        viewIntent.putExtra("txt", tweet);
        PendingIntent viewPendingIntent =
                PendingIntent.getActivity(this, 0, viewIntent, 0);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle("Tweet")
                        .setContentText(tweet)
                        .setContentIntent(viewPendingIntent);

        // Get an instance of the NotificationManager service
        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);

        // Build the notification and issues it with notification manager.
        notificationManager.notify(notificationId, notificationBuilder.build());

    }
}