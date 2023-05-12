package org.hssounz.servermanagerservice.mappers.impl;

import org.hssounz.servermanagerservice.dtos.ServerRequestDTO;
import org.hssounz.servermanagerservice.mappers.DTOMapper;
import org.hssounz.servermanagerservice.model.Server;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class DTOMapperImpl implements DTOMapper {
    @Override
    public Server fromServerRequest(ServerRequestDTO serverRequest) {
        Server server = Server.builder().build();
        BeanUtils.copyProperties(serverRequest, server);
        return server;
    }
}
