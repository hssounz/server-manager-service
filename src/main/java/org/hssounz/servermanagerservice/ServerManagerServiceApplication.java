package org.hssounz.servermanagerservice;

import org.hssounz.servermanagerservice.dao.ServerRepository;
import org.hssounz.servermanagerservice.enums.ServerStatus;
import org.hssounz.servermanagerservice.model.Server;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
}
