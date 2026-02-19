package xyz.mayahive.customdaytime.common.event.listener;

import lombok.RequiredArgsConstructor;
import xyz.mayahive.customdaytime.api.platform.Platform;
import xyz.mayahive.customdaytime.common.context.CustomDaytimeContext;
import xyz.mayahive.customdaytime.common.event.type.TimeSkipCause;
import xyz.mayahive.customdaytime.common.event.type.TimeSkipEvent;

@RequiredArgsConstructor
public class TimeSkipListener {

    private final CustomDaytimeContext context;

    public void onTimeSkipEvent(TimeSkipEvent event) {
        Platform platform = context.platform();

        TimeSkipCause cause = event.timeSkipCause();

        if (platform.debug()) platform.getLogger().debug("Registered TimeSkipEvent. TimeSkipCause: " + cause);
    }

}
