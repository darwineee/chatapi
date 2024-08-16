package com.darwin.dev.crmservice.controller.api.v1;

import com.darwin.dev.crmservice.core.dto.client.GetClientInfoResponse;
import com.darwin.dev.crmservice.core.exception.InvalidClientId;
import com.darwin.dev.crmservice.service.ClientService;
import com.darwin.dev.distributed.util.RequestCst;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/info")
    ResponseEntity<GetClientInfoResponse> getClientInfo(
            @RequestParam(RequestCst.CLIENT_ID) int clientId
    ) throws InvalidClientId {
        GetClientInfoResponse rsp = clientService.getClientInfo(clientId);
        return ResponseEntity.ok(rsp);
    }
}
