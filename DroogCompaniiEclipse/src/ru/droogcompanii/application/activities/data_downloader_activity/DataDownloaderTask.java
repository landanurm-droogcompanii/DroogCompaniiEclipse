package ru.droogcompanii.application.activities.data_downloader_activity;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import ru.droogcompanii.application.R;
import ru.droogcompanii.application.activities.helpers.task.Task;
import ru.droogcompanii.application.data.DataUpdater;
import ru.droogcompanii.application.util.DataUrlProvider;
import ru.droogcompanii.application.util.LogTagProvider;
import android.content.Context;
import android.os.PowerManager;
import android.util.Log;

/**
 * Created by ls on 26.12.13.
 */
public class DataDownloaderTask extends Task {
    private final Context context;
    private final DataUpdater dataUpdater;

    public DataDownloaderTask(Context context) {
        this.context = context;
        this.dataUpdater = new DataUpdater(context, prepareXmlProviderFromResources());
    }

    private DataUpdater.XmlProvider prepareXmlProviderFromResources() {
        return new DataUpdater.XmlProvider() {
            @Override
            public InputStream getXml() throws Exception {
                return context.getResources().openRawResource(R.raw.test_data);
            }
        };
    }

    @SuppressWarnings("unused")
	private DataUpdater.XmlProvider prepareXmlProviderFromInternet() {
        return new DataUpdater.XmlProvider() {
            @Override
            public InputStream getXml() throws Exception {
                URL url = new URL(DataUrlProvider.getDataUrl());
                URLConnection connection = url.openConnection();
                return connection.getInputStream();
            }
        };
    }

    @Override
    protected Void doInBackground(Void... params) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, getClass().getName());
        wakeLock.acquire();
        try {
            dataUpdater.update();
        } catch (Exception e) {
            processUpdatingException(e);
        } finally {
            wakeLock.release();
        }
        return null;
    }

    private void processUpdatingException(Exception e) {
        StackTraceElement[] stackTrace = e.getStackTrace();
        for (StackTraceElement each : stackTrace) {
            Log.d(LogTagProvider.get(), each.toString());
        }
    }
}
