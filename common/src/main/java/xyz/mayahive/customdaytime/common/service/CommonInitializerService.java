package xyz.mayahive.customdaytime.common.service;

import xyz.mayahive.customdaytime.common.context.CustomDaytimeContext;
import xyz.mayahive.customdaytime.common.registry.CommonEventRegistry;

public class CommonInitializerService {

    public static void initialize(CustomDaytimeContext context) {
        CommonEventRegistry.initialize(context);
        context.getConfigService().saveDefaultConfig();
        new WorldInitializationService(context).syncExistingWorlds();
    }
}
