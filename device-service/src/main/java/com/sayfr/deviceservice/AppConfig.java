package com.sayfr.deviceservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sayfr.deviceservice.data_access.DeviceDao;
import com.sayfr.deviceservice.data_access.MedicalStaffMemberDao;
import com.sayfr.deviceservice.data_access.MedicalStaffMemberDaoMongoDbImpl;
import com.sayfr.deviceservice.data_access.MedicalStaffMemberRepository;
import com.sayfr.deviceservice.data_access.PatientDao;
import com.sayfr.deviceservice.data_access.PatientDaoMongoDbImpl;
import com.sayfr.deviceservice.data_access.PatientRepository;
import com.sayfr.deviceservice.data_access.RecordDao;
import com.sayfr.deviceservice.data_access.RecordDaoMongoDbImpl;
import com.sayfr.deviceservice.data_access.RecordRepository;
import com.sayfr.deviceservice.data_access.SensorDao;
import com.sayfr.deviceservice.data_access.SensorDaoMongoDbImpl;
import com.sayfr.deviceservice.data_access.SensorReadingRepository;
import com.sayfr.deviceservice.data_access.SensorRepository;
import com.sayfr.deviceservice.services.DataReceiver;
import com.sayfr.deviceservice.services.DataReceiverImpl;
import com.sayfr.deviceservice.services.DataRouter;
import com.sayfr.deviceservice.services.DataRouterImpl;
import com.sayfr.deviceservice.services.DevicesManagerImpl;
import com.sayfr.deviceservice.services.DevicesManger;
import com.sayfr.deviceservice.services.Recorder;

@Configuration
public class AppConfig {

    /*
     * App parameters
     * 
     */
    public static final int DATAFRAME_SIZE_IN_MB = 50;
    public static final int SNAPSHOT_MAXSIZE_IN_MB = 50;


    /*
     * bean definitions
     * 
     */
    @Bean
    public RecordDao recordDao(RecordRepository recordRepository, SensorReadingRepository sensorReadingRepository) {
        return new RecordDaoMongoDbImpl(sensorReadingRepository, recordRepository);
    }

    @Bean
    public MedicalStaffMemberDao medicalStaffMemberDao(MedicalStaffMemberRepository medicalStaffMemberRepository){
        return new MedicalStaffMemberDaoMongoDbImpl(medicalStaffMemberRepository);
    }

    @Bean
    public PatientDao patientDao(PatientRepository patientRepository){
        return new PatientDaoMongoDbImpl(patientRepository);
    }
    
    @Bean 
    public Recorder recorder(RecordDao recordDao, DataRouter dataRouter){
        return new Recorder(recordDao, dataRouter);
    }

    @Bean
    public SensorDao sensorDao(SensorRepository sensorRepository){
        return new SensorDaoMongoDbImpl(sensorRepository);
    }

    @Bean
    public DataRouter dataRouter(){
        return new DataRouterImpl();
    }

    @Bean 
    public DevicesManger devicesManger(DeviceDao deviceDao, SensorDao sensorDao){
        return new DevicesManagerImpl(deviceDao, sensorDao);
    }

    @Bean
    public DataReceiver dataReceiver(DataRouter dataRouter){
        return new DataReceiverImpl(dataRouter);
    }

}
