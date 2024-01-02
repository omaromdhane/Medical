package com.sayfr.deviceservice.connector;

import java.util.UUID;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import com.sayfr.deviceservice.domain.DeviceId;
import com.sayfr.deviceservice.services.DataReceiver;
import com.sayfr.deviceservice.services.Recorder;

@Configuration
public class MqttConnector {

	@Autowired
	DataReceiver receiver;
	@Autowired
	Recorder recorder;

	@Bean
	public MqttPahoClientFactory mqttClientFactory() {
		DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
		MqttConnectOptions options = new MqttConnectOptions();
		options.setServerURIs(new String[] { "tcp://localhost:1883" });
		options.setUserName("");
		String pass = "";
		options.setPassword(pass.toCharArray());
		options.setCleanSession(true);
		factory.setConnectionOptions(options);
		return factory;
	}

	@Bean
	public MessageChannel mqttInputChannel() {
		return new DirectChannel();
	}

	@Bean
	public MessageProducer inbound() {
		MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter("serverIn",
				mqttClientFactory(), "#");
		adapter.setCompletionTimeout(5000);
		adapter.setConverter(new DefaultPahoMessageConverter());
		adapter.setQos(2);
		adapter.setOutputChannel(mqttInputChannel());
		return adapter;
	}

	@Bean
	@ServiceActivator(inputChannel = "mqttInputChannel")
	public MessageHandler handler() {
		return new MessageHandler() {
			@Override
			public void handleMessage(Message<?> message) throws MessagingException {
				String topic = message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC).toString();
				DeviceId deviceId = new DeviceId(UUID.fromString(getIdFromTopic(topic)));
				receiver.receiveData(deviceId ,message.getPayload());
			}
		};
	}

	@Bean
	public MessageChannel mqttOutboundChannel() {
		return new DirectChannel();
	}

	@Bean
	@ServiceActivator(inputChannel = "mqttOutboundChannel")
	public MessageHandler mqttOutbound() {
		MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler("serverOut", mqttClientFactory());
		messageHandler.setAsync(true);
		messageHandler.setDefaultTopic("#");
		messageHandler.setDefaultRetained(false);
		return messageHandler;
	}

	private String getIdFromTopic(String topic) {
		String[] parts = topic.split("/");
		if (parts.length > 0) {
			String uuidString = parts[parts.length - 1];
			try {
				UUID uuid = UUID.fromString(uuidString);
				return uuid.toString();
			} catch (IllegalArgumentException e) {
				return null;
			}
		}
		return null;
	}
}
