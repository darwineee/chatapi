package com.darwin.dev.crmservice.service;

import com.darwin.dev.crmservice.core.dto.channel.request.*;
import com.darwin.dev.crmservice.core.dto.channel.response.*;
import com.darwin.dev.crmservice.core.exception.InvalidChannelId;
import com.darwin.dev.crmservice.core.repository.IChannelRepository;
import com.darwin.dev.crmservice.core.repository.IUserChannelRepository;
import com.darwin.dev.crmservice.core.service.IChannelService;
import com.darwin.dev.distributed.crm.Channel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChannelService implements IChannelService {

    private final IChannelRepository channelRepository;

    private final IUserChannelRepository userChannelRepository;

    @Override
    public GetChannelsResponse getChannels(GetChannelsRequest request) {
        List<Channel> channels = channelRepository.findAll(request.clientId());
        return GetChannelsResponse.from(channels);
    }

    @Override
    public CreateChannelResponse createChannel(CreateChannelRequest request) {
        return null;
    }

    @Override
    public UpdateChannelResponse updateChannel(UpdateChannelRequest request) {
        return null;
    }

    @Override
    public GetChannelResponse getChannel(GetChannelRequest request) throws InvalidChannelId {
        return null;
    }

    @Override
    public void deleteChannel(DeleteChannelRequest request) {

    }

    @Override
    public GetUsersInChannelResponse getUsersInChannel(GetUsersInChannelRequest request) {
        return null;
    }

    @Override
    public GetChannelsOfUserResponse getChannelsOfUser(GetChannelsOfUserRequest request) {
        return null;
    }

    @Override
    public boolean joinInChannel(JoinChannelRequest request) {
        return false;
    }

    @Override
    public boolean quitChannel(QuitChannelRequest request) {
        return false;
    }
}
