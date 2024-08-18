package com.darwin.dev.crmservice.controller.api.v1;

import com.darwin.dev.crmservice.core.dto.channel.response.GetChannelsResponse;
import com.darwin.dev.crmservice.core.dto.user.response.GetUsersResponse;
import com.darwin.dev.crmservice.core.dto.userchannel.request.GetChannelsOfUserRequest;
import com.darwin.dev.crmservice.core.dto.userchannel.request.GetUsersInChannelRequest;
import com.darwin.dev.crmservice.core.dto.userchannel.request.JoinChannelRequest;
import com.darwin.dev.crmservice.core.dto.userchannel.request.LeaveChannelRequest;
import com.darwin.dev.crmservice.core.exception.action.JoinChannelFailed;
import com.darwin.dev.crmservice.core.exception.action.LeaveChannelFailed;
import com.darwin.dev.crmservice.core.service.IUserChannelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/userchannel")
@RequiredArgsConstructor
public class UserChannelController {
    private final IUserChannelService userChannelService;

    @GetMapping("/channels")
    ResponseEntity<GetChannelsResponse> getChannelsOfUser(
            @RequestParam("userId") int userId
    ) {
        GetChannelsOfUserRequest request = GetChannelsOfUserRequest.builder()
                .userId(userId)
                .build();
        GetChannelsResponse rsp = userChannelService.getChannelsOfUser(request);
        return ResponseEntity.ok(rsp);
    }

    @GetMapping("/users")
    ResponseEntity<GetUsersResponse> getUsersInChannel(
            @RequestParam("channelId") int channelId
    ) {
        GetUsersInChannelRequest request = GetUsersInChannelRequest.builder()
                .channelId(channelId)
                .build();
        GetUsersResponse rsp = userChannelService.getUsersInChannel(request);
        return ResponseEntity.ok(rsp);
    }

    @PostMapping("/join")
    ResponseEntity<Void> joinChannel(
            @RequestParam("userId") int userId,
            @RequestParam("channelId") int channelId
    ) throws JoinChannelFailed {
        JoinChannelRequest request = JoinChannelRequest.builder()
                .userId(userId)
                .channelId(channelId)
                .build();
        userChannelService.joinInChannel(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/leave")
    ResponseEntity<Void> leaveChannel(
            @RequestParam("userId") int userId,
            @RequestParam("channelId") int channelId
    ) throws LeaveChannelFailed {
        LeaveChannelRequest request = LeaveChannelRequest.builder()
                .userId(userId)
                .channelId(channelId)
                .build();
        userChannelService.leaveChannel(request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
