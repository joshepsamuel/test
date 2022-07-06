public class NotificationWrapper {

    private static final String TAG = NotificationWrapper.class.getSimpleName();

    private static final String NOTIFICATION_CHANNEL_ID = TAG;
    private static final int NOTIFICATION_ID = 2;

    private NotificationManager getNotificationService(Context context) {
        return (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public void createNotificationChannel(Context context) {
        final NotificationManager nm = getNotificationService(context);
        nm.createNotificationChannel(
                new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                        context.getString(R.string.app_name),
                        NotificationManager.IMPORTANCE_MAX));
    }

    private Notification newNotification(Context context, String text) {
        final String title = context.getString(R.string.app_name);
        return new Notification.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.lb_ic_fast_forward)
                .setLocalOnly(true)
                .setColor(context.getResources().getColor(R.color.lb_background_protection))
                .setContentTitle(title)
                .setTicker(title)
                .setContentText(text)
                .setStyle(new Notification.BigTextStyle().bigText(text))
                .setVisibility(Notification.VISIBILITY_PUBLIC)
                .setCategory(Notification.CATEGORY_SYSTEM)
                //MUST HAVE. Available only on the Android TV platform.
                .extend(new Notification.TvExtender().setSuppressShowOverApps(true))
                .build();
    }

    public void showNotification(Context context, String status) {
        final NotificationManager nm = getNotificationService(context);
        nm.notify(TAG, NOTIFICATION_ID, newNotification(context, status));
    }

}
