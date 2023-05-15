package org.hssounz.servermanagerservice;

import org.hssounz.servermanagerservice.dao.ServerRepository;
import org.hssounz.servermanagerservice.enums.ServerStatus;
import org.hssounz.servermanagerservice.model.Server;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@SpringBootApplication
public class ServerManagerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerManagerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ServerRepository serverRepository){
		return args -> {

			serverRepository.save(
					Server.builder()
							.ipAddress("192.168.1.8")
							.type("Personal PC")
							.name("Ubuntu Linux")
							.memory("16 GB")
							.imageUrl("http://localhost:8088/server/image/server1.png")
							.status(ServerStatus.SERVER_UP)
							.build()
			);
			serverRepository.save(
					Server.builder()
							.ipAddress("196.254.106.47")
							.type("Dell Tower")
							.name("Fedora Linux")
							.memory("16 GB")
							.imageUrl("http://localhost:8088/server/image/server2.png")
							.status(ServerStatus.SERVER_DOWN)
							.build()
			);
			serverRepository.save(
					Server.builder()
							.ipAddress("172.28.144.1")
							.type("Web Server")
							.name("MS 2008")
							.memory("32 GB")
							.imageUrl("http://localhost:8088/server/image/server3.png")
							.status(ServerStatus.SERVER_UP)
							.build()
			);
			serverRepository.save(
					Server.builder()
							.ipAddress("169.254.56.232")
							.type("Mail Server")
							.name("Red Hat Entreprise Linux")
							.memory("64 GB")
							.imageUrl("http://localhost:8088/server/image/server3.png")
							.status(ServerStatus.SERVER_DOWN)
							.build()
			);
		};
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList(
				"http://localhost:3000",
				"http://localhost:4200"
		));
		corsConfiguration.setAllowedHeaders(Arrays.asList(
				"Origin", "Access-Control-Allow-Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
				"Origin, Accept", "X-Requested-With", "Access-Control-Request-Method", "Access-Control-Request-Headers"
		));
		corsConfiguration.setExposedHeaders(Arrays.asList(
				"Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Filename"
		));
		corsConfiguration.setAllowedMethods(Arrays.asList(
				"GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"
		));
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}
}
