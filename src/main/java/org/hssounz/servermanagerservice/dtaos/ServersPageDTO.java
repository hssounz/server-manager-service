package org.hssounz.servermanagerservice.dtaos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hssounz.servermanagerservice.model.Server;

import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ServersPageDTO {

    private List<Server> servers;
    private int currentPage;
    private int totalPages;
    private int pageSize;
}
