package dk.sdu.se.f23.InVasion.common.events;


import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.common.events.abstracts.Event;

import java.util.*;

public class EventDistributor {
    private static Map<Class<?>, List<EventListener>> eventActivatorMap = new HashMap<>();

    public static void addListener(Class<?> eventClass, EventListener listener){
        eventActivatorMap.computeIfAbsent(eventClass, k -> new ArrayList<>());
        eventActivatorMap.get(eventClass).add(listener);
    }

    public static boolean removeListener(Class<? extends Event> eventClass, EventListener eventListener){
        return eventActivatorMap.get(eventClass).remove(eventListener);

    }

    public static void sendEvent(Event event, World world){
        if (!eventActivatorMap.containsKey(event.getClass())) {
            System.out.println("No listeners for event: " + event.getClass().getName());
            return;
        }

        for (EventListener eventListener : eventActivatorMap.get(event.getClass())) {
            eventListener.processEvent(event, world);
        }
    }

    public static Map<Class<?>, List<EventListener>> getEventActivatorMap() {
        return eventActivatorMap;
    }


}
