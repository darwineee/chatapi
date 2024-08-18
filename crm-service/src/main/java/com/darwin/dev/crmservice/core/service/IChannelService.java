package com.darwin.dev.crmservice.core.service;

import com.darwin.dev.crmservice.core.dto.channel.request.*;
import com.darwin.dev.crmservice.core.dto.channel.response.*;
import com.darwin.dev.crmservice.core.exception.action.DeleteChannelFailed;
import com.darwin.dev.crmservice.core.exception.resource.InvalidChannelId;

public interface IChannelService {
    GetChannelsResponse getChannels(GetChannelsRequest request);
    CreateChannelResponse createChannel(CreateChannelRequest request);
    UpdateChannelResponse updateChannel(UpdateChannelRequest request);
    GetChannelResponse getChannel(GetChannelRequest request) throws InvalidChannelId;
    void deleteChannel(DeleteChannelRequest request) throws DeleteChannelFailed;
}
