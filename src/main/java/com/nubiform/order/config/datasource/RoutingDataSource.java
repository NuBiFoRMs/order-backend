package com.nubiform.order.config.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import static com.nubiform.order.constant.DataSourceType.READ_ONLY;
import static com.nubiform.order.constant.DataSourceType.WRITE;

@Slf4j
public class RoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        log.debug("determineCurrentLookupKey: {}", TransactionSynchronizationManager.isCurrentTransactionReadOnly());
        return TransactionSynchronizationManager.isCurrentTransactionReadOnly() ? READ_ONLY : WRITE;
    }
}
