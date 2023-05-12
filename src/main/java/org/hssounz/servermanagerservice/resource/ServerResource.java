package org.hssounz.servermanagerservice.resource;

import lombok.RequiredArgsConstructor;
import org.hssounz.servermanagerservice.dtaos.ServerRequestDTO;
import org.hssounz.servermanagerservice.enums.ServerStatus;
import org.hssounz.servermanagerservice.model.Response;
import org.hssounz.servermanagerservice.model.Server;
import org.hssounz.servermanagerservice.service.ServerService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static java.time.LocalDateTime.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController @RequestMapping("/server")
@RequiredArgsConstructor
public class ServerResource {
    private final ServerService serverService;

    @GetMapping("/list")
    ResponseEntity<Response> getServers(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
            ) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("servers", serverService.pageServers(page, size)))
                        .message("Servers retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/ping/{ip}")
    ResponseEntity<Response> pingServer(@PathVariable(name = "ip") String ipAddress) throws IOException {
        Server server = serverService.ping(ipAddress);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("pingedServer", server))
                        .message(
                                server.getStatus() == ServerStatus.SERVER_UP
                                        ? "Ping succeed"
                                        : "Ping failed"
                        )
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @PostMapping("/ping")
    ResponseEntity<Response> saveServer(@RequestBody @Valid ServerRequestDTO serverRequest) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("server", serverService.create(serverRequest)))
                        .message("Server created successfully")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }
    @GetMapping("/get/{id}")
    ResponseEntity<Response> getServer(@PathVariable String id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("pingedServer", serverService.get(id)))
                        .message("Server retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
    @DeleteMapping("/delete/{id}")
    ResponseEntity<Response> deleteServer(@PathVariable String id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("deleted", serverService.delete(id)))
                        .message("Server deleted")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
    @GetMapping(path = "/image/{fileName}", produces = MediaType.IMAGE_PNG_VALUE)
    byte[] getServerImage(@PathVariable String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "Images/servers/" + fileName));
    }
}
