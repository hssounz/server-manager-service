package org.hssounz.servermanagerservice.dao;

import org.hssounz.servermanagerservice.model.Server;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepository extends JpaRepository<Server, String> {
    Server findByIpAddress(String ipAddress);

}
