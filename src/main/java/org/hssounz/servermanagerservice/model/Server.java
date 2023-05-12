package org.hssounz.servermanagerservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hssounz.servermanagerservice.enums.ServerStatus;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Entity @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Server {
    @Id
    private String id;
    @Column(unique = true) @NotEmpty(message = "IP Address cannot be empty or null")
    private String ipAddress;
    private String name;
    private String memory;
    private String type;
    private String imageUrl;
    @Enumerated(EnumType.STRING)
    private ServerStatus status;
    
    @PrePersist
    void creation(){
        this.id = UUID.randomUUID().toString();
    }
    
}
