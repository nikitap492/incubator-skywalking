/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.apache.skywalking.apm.collector.storage.es.dao.alarm;

import org.apache.skywalking.apm.collector.client.elasticsearch.ElasticSearchClient;
import org.apache.skywalking.apm.collector.core.annotations.trace.GraphComputingMetric;
import org.apache.skywalking.apm.collector.storage.dao.alarm.IServiceReferenceAlarmListPersistenceDAO;
import org.apache.skywalking.apm.collector.storage.es.base.dao.AbstractPersistenceEsDAO;
import org.apache.skywalking.apm.collector.storage.table.alarm.ServiceReferenceAlarmList;
import org.apache.skywalking.apm.collector.storage.table.alarm.ServiceReferenceAlarmListTable;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.update.UpdateRequestBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author peng-yongsheng
 */
public class ServiceReferenceAlarmListEsPersistenceDAO extends AbstractPersistenceEsDAO<ServiceReferenceAlarmList> implements IServiceReferenceAlarmListPersistenceDAO<IndexRequestBuilder, UpdateRequestBuilder, ServiceReferenceAlarmList> {

    public ServiceReferenceAlarmListEsPersistenceDAO(ElasticSearchClient client) {
        super(client);
    }

    @Override protected String tableName() {
        return ServiceReferenceAlarmListTable.TABLE;
    }

    @Override protected ServiceReferenceAlarmList esDataToStreamData(Map<String, Object> source) {
        ServiceReferenceAlarmList serviceReferenceAlarmList = new ServiceReferenceAlarmList();
        serviceReferenceAlarmList.setFrontApplicationId(((Number)source.get(ServiceReferenceAlarmListTable.FRONT_APPLICATION_ID.getName())).intValue());
        serviceReferenceAlarmList.setBehindApplicationId(((Number)source.get(ServiceReferenceAlarmListTable.BEHIND_APPLICATION_ID.getName())).intValue());
        serviceReferenceAlarmList.setFrontInstanceId(((Number)source.get(ServiceReferenceAlarmListTable.FRONT_INSTANCE_ID.getName())).intValue());
        serviceReferenceAlarmList.setBehindInstanceId(((Number)source.get(ServiceReferenceAlarmListTable.BEHIND_INSTANCE_ID.getName())).intValue());
        serviceReferenceAlarmList.setFrontServiceId(((Number)source.get(ServiceReferenceAlarmListTable.FRONT_SERVICE_ID.getName())).intValue());
        serviceReferenceAlarmList.setBehindServiceId(((Number)source.get(ServiceReferenceAlarmListTable.BEHIND_SERVICE_ID.getName())).intValue());
        serviceReferenceAlarmList.setSourceValue(((Number)source.get(ServiceReferenceAlarmListTable.SOURCE_VALUE.getName())).intValue());

        serviceReferenceAlarmList.setAlarmType(((Number)source.get(ServiceReferenceAlarmListTable.ALARM_TYPE.getName())).intValue());
        serviceReferenceAlarmList.setAlarmContent((String)source.get(ServiceReferenceAlarmListTable.ALARM_CONTENT.getName()));

        serviceReferenceAlarmList.setTimeBucket(((Number)source.get(ServiceReferenceAlarmListTable.TIME_BUCKET.getName())).longValue());
        return serviceReferenceAlarmList;
    }

    @Override protected Map<String, Object> esStreamDataToEsData(ServiceReferenceAlarmList streamData) {
        Map<String, Object> target = new HashMap<>();
        target.put(ServiceReferenceAlarmListTable.FRONT_APPLICATION_ID.getName(), streamData.getFrontApplicationId());
        target.put(ServiceReferenceAlarmListTable.BEHIND_APPLICATION_ID.getName(), streamData.getBehindApplicationId());
        target.put(ServiceReferenceAlarmListTable.FRONT_INSTANCE_ID.getName(), streamData.getFrontInstanceId());
        target.put(ServiceReferenceAlarmListTable.BEHIND_INSTANCE_ID.getName(), streamData.getBehindInstanceId());
        target.put(ServiceReferenceAlarmListTable.FRONT_SERVICE_ID.getName(), streamData.getFrontServiceId());
        target.put(ServiceReferenceAlarmListTable.BEHIND_SERVICE_ID.getName(), streamData.getBehindServiceId());
        target.put(ServiceReferenceAlarmListTable.SOURCE_VALUE.getName(), streamData.getSourceValue());

        target.put(ServiceReferenceAlarmListTable.ALARM_TYPE.getName(), streamData.getAlarmType());
        target.put(ServiceReferenceAlarmListTable.ALARM_CONTENT.getName(), streamData.getAlarmContent());

        target.put(ServiceReferenceAlarmListTable.TIME_BUCKET.getName(), streamData.getTimeBucket());
        return target;
    }

    @Override protected String timeBucketColumnNameForDelete() {
        return ServiceReferenceAlarmListTable.TIME_BUCKET.getName();
    }

    @GraphComputingMetric(name = "/persistence/get/" + ServiceReferenceAlarmListTable.TABLE)
    @Override public ServiceReferenceAlarmList get(String id) {
        return super.get(id);
    }
}
