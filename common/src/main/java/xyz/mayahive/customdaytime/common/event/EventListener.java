package xyz.mayahive.customdaytime.common.event;

public interface EventListener<T extends Event> {
    void handle(T event);
}
