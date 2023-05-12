package org.hssounz.servermanagerservice.mappers;

import org.hssounz.servermanagerservice.dtos.ServerRequestDTO;
import org.hssounz.servermanagerservice.model.Server;

public interface DTOMapper {
    Server fromServerRequest(ServerRequestDTO serverRequest);
}
