package com.darwin.dev.crmservice.core.service;

import com.darwin.dev.crmservice.core.dto.channel.response.GetChannelsResponse;
import com.darwin.dev.crmservice.core.dto.user.response.GetUsersResponse;
import com.darwin.dev.crmservice.core.dto.userchannel.request.GetChannelsOfUserRequest;
import com.darwin.dev.crmservice.core.dto.userchannel.request.GetUsersInChannelRequest;
import com.darwin.dev.crmservice.core.dto.userchannel.request.JoinChannelRequest;
import com.darwin.dev.crmservice.core.dto.userchannel.request.LeaveChannelRequest;
import com.darwin.dev.crmservice.core.exception.action.JoinChannelFailed;
import com.darwin.dev.crmservice.core.exception.action.LeaveChannelFailed;

public interface IUserChannelService {
    GetUsersResponse getUsersInChannel(GetUsersInChannelRequest request);
    GetChannelsResponse getChannelsOfUser(GetChannelsOfUserRequest request);
    void joinInChannel(JoinChannelRequest request) throws JoinChannelFailed;
    void leaveChannel(LeaveChannelRequest request) throws LeaveChannelFailed;
}
