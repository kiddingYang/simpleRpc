package com.gs.rpc.core.register;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Service {

    private String id;
    private String serviceName;
    private String address;
    private Integer port;

}
