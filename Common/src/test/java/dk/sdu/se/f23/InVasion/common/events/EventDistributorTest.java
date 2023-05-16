package dk.sdu.se.f23.InVasion.common.events;

import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.common.events.abstracts.Event;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

class EventDistributorTest {
    private EventListener eventListener;
    private Event event;

    private PrintStream originalSystemOut;
    private ByteArrayOutputStream systemOutContent;

    @BeforeEach
    void redirectSystemOutStream() {

        originalSystemOut = System.out;

        // given
        systemOutContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(systemOutContent));
    }

    @AfterEach
    void restoreSystemOutStream() {
        System.setOut(originalSystemOut);
    }

    @BeforeEach
    private void setUp() {
        this.eventListener = mock(EventListener.class);
        this.event = mock(Event.class);

        Class<EventDistributor> clazz = EventDistributor.class;
        try {
            Field eventActivatorMap = clazz.getDeclaredField("eventActivatorMap");
            setFinalStatic(eventActivatorMap, new HashMap<>());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    void setFinalStatic(Field field, Object newValue) throws Exception {
        field.setAccessible(true);
        field.set(null, newValue);
        field.setAccessible(false);
    }

    @Test
    void addListener() {
        EventDistributor.addListener(event.getClass(), eventListener);

        assertTrue(EventDistributor.getEventActivatorMap().containsKey(event.getClass()));
        assertTrue(EventDistributor.getEventActivatorMap().get(event.getClass()).contains(eventListener));
    }

    @Test
    void removeListener() {
        EventDistributor.addListener(event.getClass(), eventListener);

        EventDistributor.removeListener(event.getClass(), eventListener);

        assertTrue(EventDistributor.getEventActivatorMap().containsKey(event.getClass()));
        assertFalse(EventDistributor.getEventActivatorMap().get(event.getClass()).contains(eventListener));
    }

    @Test
    void sendEvent() {
        EventDistributor.addListener(event.getClass(), eventListener);

        World world = mock(World.class);

        EventDistributor.sendEvent(event, world);

        verify(eventListener, atLeastOnce()).processEvent(event, world);
    }

    @Test
    void sendEventDontBotherIrrelevantListeners() {
        Event otherEvent = mock(Event.class);
        EventListener otherListener = mock(EventListener.class);

        EventDistributor.addListener(otherEvent.getClass(), otherListener);

        EventDistributor.addListener(event.getClass(), eventListener);

        World world = mock(World.class);

        EventDistributor.sendEvent(event, world);

        verify(eventListener, atLeastOnce()).processEvent(event, world);
        verify(otherListener, never()).processEvent(otherEvent, world);
    }

    @Test
    void sendEventNoListener() {
        class TestEvent extends Event {}

        TestEvent testEvent = new TestEvent();

        EventDistributor.addListener(event.getClass(), eventListener);

        World world = mock(World.class);

        EventDistributor.sendEvent(testEvent, world);

        verify(eventListener, never()).processEvent(event, world);

        assertEquals(systemOutContent.toString().trim(), ("No listeners for event: " + testEvent.getClass().getName()).trim());
    }
}