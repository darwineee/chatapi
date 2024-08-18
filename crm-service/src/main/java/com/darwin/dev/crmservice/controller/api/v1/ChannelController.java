package com.darwin.dev.crmservice.controller.api.v1;

import com.darwin.dev.crmservice.core.dto.channel.request.*;
import com.darwin.dev.crmservice.core.dto.channel.response.CreateChannelResponse;
import com.darwin.dev.crmservice.core.dto.channel.response.GetChannelResponse;
import com.darwin.dev.crmservice.core.dto.channel.response.GetChannelsResponse;
import com.darwin.dev.crmservice.core.dto.channel.response.UpdateChannelResponse;
import com.darwin.dev.crmservice.core.exception.action.DeleteChannelFailed;
import com.darwin.dev.crmservice.core.exception.resource.InvalidChannelId;
import com.darwin.dev.crmservice.core.service.IChannelService;
import com.darwin.dev.distributed.util.RequestCst;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/channels")
@RequiredArgsConstructor
public class ChannelController {
    private final IChannelService channelService;

    @GetMapping
    ResponseEntity<GetChannelsResponse> getChannels(
            @RequestParam(RequestCst.CLIENT_ID) int clientId
    ) {
        GetChannelsRequest request = GetChannelsRequest.builder()
                .clientId(clientId)
                .build();
        GetChannelsResponse rsp = channelService.getChannels(request);
        return ResponseEntity.ok(rsp);
    }

    @GetMapping("/{channelId}")
    ResponseEntity<GetChannelResponse> getChannel(
            @PathVariable int channelId,
            @RequestParam(RequestCst.CLIENT_ID) int clientId
    ) throws InvalidChannelId {
        GetChannelRequest request = GetChannelRequest.builder()
                .clientId(clientId)
                .channelId(channelId)
                .build();
        GetChannelResponse rsp = channelService.getChannel(request);
        return ResponseEntity.ok(rsp);
    }

    @PostMapping
    ResponseEntity<CreateChannelResponse> createChannel(
            @RequestBody CreateChannelRequest request,
            @RequestParam(RequestCst.CLIENT_ID) int clientId
    ) {
        request.setClientId(clientId);
        CreateChannelResponse rsp = channelService.createChannel(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(rsp);
    }

    @PutMapping
    ResponseEntity<UpdateChannelResponse> updateChannel(
            @RequestBody UpdateChannelRequest request,
            @RequestParam(RequestCst.CLIENT_ID) int clientId
    ) {
        request.setClientId(clientId);
        UpdateChannelResponse rsp = channelService.updateChannel(request);
        return ResponseEntity.ok(rsp);
    }

    @DeleteMapping("/{channelId}")
    ResponseEntity<Void> deleteChannel(
            @PathVariable int channelId,
            @RequestParam(RequestCst.CLIENT_ID) int clientId
    ) throws DeleteChannelFailed {
        DeleteChannelRequest request = DeleteChannelRequest.builder()
                .clientId(clientId)
                .channelId(channelId)
                .build();
        channelService.deleteChannel(request);
        return ResponseEntity.accepted().build();
    }
}
