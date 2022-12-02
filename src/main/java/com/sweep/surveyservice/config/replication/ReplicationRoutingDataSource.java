//package com.sweep.surveyservice.config.replication;
//
//
//import com.sweep.surveyservice.common.DataSourceType;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
//import org.springframework.transaction.support.TransactionSynchronizationManager;
//
//@Slf4j
//public class ReplicationRoutingDataSource extends AbstractRoutingDataSource {
//
//    @Override
//    protected Object determineCurrentLookupKey() {
//        DataSourceType dataSourceType = TransactionSynchronizationManager.isCurrentTransactionReadOnly() ? DataSourceType.Slave : DataSourceType.Master;
//        return dataSourceType;
//    }
//}