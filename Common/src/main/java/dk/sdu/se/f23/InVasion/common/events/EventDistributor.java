package dk.sdu.se.f23.InVasion.common.events;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventDistributor {
    private static final Map<Class<?>, List<EventListener>> eventActivatorMap = new HashMap<>();

    public static void addListener(Class<?> eventClass, EventListener listener){
        eventActivatorMap.computeIfAbsent(eventClass, k -> new ArrayList<>());
        eventActivatorMap.get(eventClass).add(listener);
    }

    public static void sendEvent(Event event){
        for (EventListener eventListener : eventActivatorMap.get(event.getClass())) {
            eventListener.processEvent(event);
        }
    }
}
