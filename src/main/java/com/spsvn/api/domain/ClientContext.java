package com.spsvn.api.domain;

import com.spsvn.api.domain.type.Client;

/**
 * Created by npkhanh on 6/8/2018.
 */
public class ClientContext {
    private Client client;

    public ClientContext(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
