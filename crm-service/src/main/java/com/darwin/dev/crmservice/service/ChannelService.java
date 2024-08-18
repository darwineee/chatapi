package com.darwin.dev.crmservice.service;

import com.darwin.dev.crmservice.core.dto.channel.request.*;
import com.darwin.dev.crmservice.core.dto.channel.response.*;
import com.darwin.dev.crmservice.core.exception.action.DeleteChannelFailed;
import com.darwin.dev.crmservice.core.exception.resource.InvalidChannelId;
import com.darwin.dev.crmservice.core.repository.IChannelRepository;
import com.darwin.dev.crmservice.core.service.IChannelService;
import com.darwin.dev.distributed.model.crm.Channel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChannelService implements IChannelService {

    private final IChannelRepository channelRepository;

    @Override
    public GetChannelsResponse getChannels(GetChannelsRequest request) {
        List<Channel> channels = channelRepository.findAll(request.clientId());
        return GetChannelsResponse.from(channels);
    }

    @Override
    public CreateChannelResponse createChannel(CreateChannelRequest request) {
        Channel channel = Channel.builder()
                .clientId(request.getClientId())
                .name(request.getName())
                .build();
        int id = channelRepository.save(channel);
        Channel insertedChannel = channelRepository.findById(request.getClientId(), id).orElse(channel);
        return CreateChannelResponse.from(insertedChannel);
    }

    @Override
    public UpdateChannelResponse updateChannel(UpdateChannelRequest request) {
        Channel channel = Channel.builder()
                .id(request.getChannelId())
                .clientId(request.getClientId())
                .name(request.getName())
                .build();
        int id = channelRepository.update(channel);
        Channel updatedChannel = channelRepository.findById(request.getClientId(), id).orElse(channel);
        return UpdateChannelResponse.from(updatedChannel);
    }

    @Override
    public GetChannelResponse getChannel(GetChannelRequest request) throws InvalidChannelId {
        Channel channel = channelRepository
                .findById(request.clientId(), request.channelId())
                .orElseThrow(InvalidChannelId::new);
        return GetChannelResponse.from(channel);
    }

    @Override
    public void deleteChannel(DeleteChannelRequest request) throws DeleteChannelFailed {
        boolean result = channelRepository.delete(request.clientId(), request.channelId());
        if (!result) throw new DeleteChannelFailed();
    }
}
