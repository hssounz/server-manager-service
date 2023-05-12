package org.hssounz.servermanagerservice.dtaos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hssounz.servermanagerservice.enums.ServerStatus;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ServerRequestDTO {
    private String ipAddress;
    private String name;
    private String memory;
    private String type;
}
