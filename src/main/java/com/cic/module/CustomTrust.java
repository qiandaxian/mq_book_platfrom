package com.cic.module;

import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import org.springframework.stereotype.Component;


@Component
public class CustomTrust {

    private static OkHttpClient client = null;
    public CustomTrust() {
        client = new OkHttpClient.Builder()
                .certificatePinner(new CertificatePinner.Builder()
                        //.add("publicobject.com", "sha256/afwiKY3RxoMmLkuRW1l7QsPZTJPwDS2pdDROQjXw8ig=")
                        .build())
                .build();
    }

    public OkHttpClient getClient() {
        return client;
    }
}
