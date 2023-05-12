package org.hssounz.servermanagerservice.service;

import org.hssounz.servermanagerservice.dtaos.ServerRequestDTO;
import org.hssounz.servermanagerservice.dtaos.ServersPageDTO;
import org.hssounz.servermanagerservice.model.Server;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Collection;

public interface ServerService {
    Server create(ServerRequestDTO serverRequest);
    Server ping(String ipAddress) throws IOException;
    Collection<Server> list(int limit);
    ServersPageDTO pageServers(int page, int size);
    Server get(String id);
    Server update(String id, ServerRequestDTO serverRequest);
    Boolean delete(String id);
}
