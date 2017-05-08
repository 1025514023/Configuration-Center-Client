package xzfm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import xzfm.common.domain.EnableJpaEntityManager;
import xzfm.core.EnableConfigurationCenterClient;
import xzfm.monitor.EnableDataMonitor;

@EnableDataMonitor
@SpringBootApplication
@EnableJpaEntityManager
@EnableConfigurationCenterClient(refresh = false)
public class ConfigurationCenterClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigurationCenterClientApplication.class, args);
	}
}
