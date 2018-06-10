package com.spsvn.api.domain;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by npkhanh on 6/8/2018.
 */
public class ClientDataSourceRouter extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return ClientContextHolder.getCurrentClientContext().getClient();
    }
}
