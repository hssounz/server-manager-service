package org.hssounz.servermanagerservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hssounz.servermanagerservice.enums.ServerStatus;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ServerRequestDTO {
    private String ipAddress;
    private String name;
    private String memory;
    private String type;
    private ServerStatus status;
}
