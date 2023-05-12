package org.hssounz.servermanagerservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hssounz.servermanagerservice.dao.ServerRepository;
import org.hssounz.servermanagerservice.dtos.ServerRequestDTO;
import org.hssounz.servermanagerservice.dtos.ServersPageDTO;
import org.hssounz.servermanagerservice.enums.ServerStatus;
import org.hssounz.servermanagerservice.mappers.DTOMapper;
import org.hssounz.servermanagerservice.model.Server;
import org.hssounz.servermanagerservice.service.ServerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

import static java.lang.Boolean.*;

@Service @Transactional
@RequiredArgsConstructor @Slf4j
public class ServerServiceImpl implements ServerService {

    private final ServerRepository serverRepository;
    private final DTOMapper dtoMapper;

    @Override
    public Server create(ServerRequestDTO serverRequest) {
        Server server = dtoMapper.fromServerRequest(serverRequest);
        server.setImageUrl(setServerImageUrl());
        log.info("Saving new server :{}", server.getName());
        return serverRepository.save(server);
    }
    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("Pining server IP :{}", ipAddress);
        Server server = serverRepository.findByIpAddress(ipAddress);

        InetAddress address = InetAddress.getByName(ipAddress);
        server.setStatus(
                        address.isReachable(10000)
                                ? ServerStatus.SERVER_UP
                                : ServerStatus.SERVER_DOWN
                );
        return serverRepository.save(server);
    }

    @Override
    public Collection<Server> list(int limit) {
        log.info("Fetching all servers");
        return serverRepository.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public ServersPageDTO pageServers(int page, int size) {
        log.info("Fetching servers page :{} with size :{}", page, size);
        Page<Server> servers = serverRepository.findAll(PageRequest.of(page, size));
        return ServersPageDTO.builder()
                .currentPage(page)
                .pageSize(servers.getSize())
                .totalPages(servers.getTotalPages())
                .servers(servers.stream().toList())
                .build();
    }

    @Override
    public Server get(String id) {
        log.info("Fetching server Id :{}", id);
        return serverRepository
                .findById(id)
                .orElseThrow(
                        () -> new RuntimeException(String.format("Server %s not found", id))
                );
    }

    @Override
    public Server update(String id, ServerRequestDTO serverRequest) {
        log.info("Updating server :{}", id);
        Server server = serverRepository
                .findById(id)
                .orElseThrow(
                        () -> new RuntimeException(String.format("Server %s not found", id))
                );
        log.info("Updating server :{} new: {}", server.getName(), serverRequest.getName());
        server.setName(serverRequest.getName());
        server.setMemory(serverRequest.getMemory());
        server.setType(serverRequest.getType());
        server.setIpAddress(serverRequest.getIpAddress());

        return serverRepository.save(server);
    }

    @Override
    public Boolean delete(String id) {
        log.info("Deleting server :{}", id);
        if (serverRepository.findById(id).isPresent())
        {
            serverRepository.deleteById(id);
            return TRUE;
        }
        return FALSE;
    }


    private String setServerImageUrl() {

        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(
                        "/server/image/server" +
                                (1 + new Random().nextInt(4))
                        + ".png"
                )
                .toUriString();
    }
}
