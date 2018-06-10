package com.spsvn.api.domain;

import com.spsvn.api.domain.type.Client;

/**
 * Created by npkhanh on 6/8/2018.
 */
public class ClientContextHolder {
    private static ThreadLocal<ClientContext> CONTEXT = ThreadLocal.withInitial(()-> new ClientContext(Client.CLIENT_A));

    public static ClientContext getCurrentClientContext() {
        return CONTEXT.get();
    }

    public static void clear() {
        CONTEXT.remove();
    }
}
