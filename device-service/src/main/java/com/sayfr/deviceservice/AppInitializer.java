package com.sayfr.deviceservice;

import org.springframework.stereotype.Component;
import com.mongodb.client.model.CreateCollectionOptions;
import com.mongodb.client.model.TimeSeriesGranularity;
import com.mongodb.client.model.TimeSeriesOptions;
import com.sayfr.deviceservice.data_access.MongoDBUtil;

@Component
public class AppInitializer {
    
    public AppInitializer() {
        initializeDatabase(false);
    }

    private void initializeDatabase(Boolean override) {
        MongoDBUtil.createCollectionIfNotExists("Patients");        
        MongoDBUtil.createCollectionIfNotExists("Devices");
        MongoDBUtil.createCollectionIfNotExists("Records");        
        MongoDBUtil.createCollectionIfNotExists("Sensors");
        MongoDBUtil.getDatabase();
        TimeSeriesOptions tsOptions = new TimeSeriesOptions("timestamp").granularity(TimeSeriesGranularity.SECONDS);
        CreateCollectionOptions collOptions = new CreateCollectionOptions().timeSeriesOptions(tsOptions);
        MongoDBUtil.createCollectionIfNotExists("Readings", collOptions);

    }
}
