package com.darwin.dev.crmservice.service;

import com.darwin.dev.crmservice.core.dto.channel.response.GetChannelsResponse;
import com.darwin.dev.crmservice.core.dto.user.response.GetUsersResponse;
import com.darwin.dev.crmservice.core.dto.userchannel.request.GetChannelsOfUserRequest;
import com.darwin.dev.crmservice.core.dto.userchannel.request.GetUsersInChannelRequest;
import com.darwin.dev.crmservice.core.dto.userchannel.request.JoinChannelRequest;
import com.darwin.dev.crmservice.core.dto.userchannel.request.LeaveChannelRequest;
import com.darwin.dev.crmservice.core.exception.action.JoinChannelFailed;
import com.darwin.dev.crmservice.core.exception.action.LeaveChannelFailed;
import com.darwin.dev.crmservice.core.repository.IUserChannelRepository;
import com.darwin.dev.crmservice.core.service.IUserChannelService;
import com.darwin.dev.crmservice.core.model.Channel;
import com.darwin.dev.crmservice.core.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserChannelService implements IUserChannelService {

    private final IUserChannelRepository userChannelRepository;


    @Override
    public GetUsersResponse getUsersInChannel(GetUsersInChannelRequest request) {
        List<User> users = userChannelRepository.findAllUsersInChannel(request.channelId());
        return GetUsersResponse.from(users);
    }

    @Override
    public GetChannelsResponse getChannelsOfUser(GetChannelsOfUserRequest request) {
        List<Channel> channels = userChannelRepository.findAllChannelsOfUser(request.userId());
        return GetChannelsResponse.from(channels);
    }

    @Override
    public void joinInChannel(JoinChannelRequest request) throws JoinChannelFailed {
        boolean result = userChannelRepository.insertUser(request.channelId(), request.userId());
        if (!result) throw new JoinChannelFailed();
    }

    @Override
    public void leaveChannel(LeaveChannelRequest request) throws LeaveChannelFailed {
        boolean result = userChannelRepository.removeUser(request.channelId(), request.userId());
        if (!result) throw new LeaveChannelFailed();
    }
}
