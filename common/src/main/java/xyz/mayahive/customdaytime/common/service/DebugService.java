package xyz.mayahive.customdaytime.common.service;

import xyz.mayahive.customdaytime.common.context.CustomDaytimeContext;

public class DebugService {

    public static void log(CustomDaytimeContext context, String message){
        if (context.platform().debug()) {
            context.platform().getLogger().debug(message);
        }
    }
}
