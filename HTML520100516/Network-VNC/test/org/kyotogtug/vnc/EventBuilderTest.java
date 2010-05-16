package org.kyotogtug.vnc;

import org.junit.Before;
import org.junit.Test;
import org.kyotogtug.vnc.events.ImageRequestEvent;
import org.kyotogtug.vnc.events.MouseClickEvent;
import org.kyotogtug.vnc.events.MouseMoveEvent;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.*;


public class EventBuilderTest {
	
	private EventBuilder eventBuilder;
	
	@Before
	public void setUp(){
		eventBuilder = new EventBuilder();
	}
	
	@Test
	public void testマウス移動イベントのパース(){
		String data = "CURSOR_MOVE|5|123456789012|123,456";
		
		MouseMoveEvent event = (MouseMoveEvent) eventBuilder.parseEvent(data);
		
		assertThat(event.getEventType(), is("CURSOR_MOVE"));
		assertThat(event.getSequence(), is(5));
		assertThat(event.getTimestamp(), is(123456789012L));
		assertThat(event.getX(), is(123));
		assertThat(event.getY(), is(456));
	}
	
	@Test
	public void testマウスクリックイベントのパース(){
		String data = "MOUSE_CLICK|6|123456789011|666,777,2";
		
		MouseClickEvent event = (MouseClickEvent) eventBuilder.parseEvent(data);
		
		assertThat(event.getEventType(), is("MOUSE_CLICK"));
		assertThat(event.getSequence(), is(6));
		assertThat(event.getTimestamp(), is(123456789011L));
		assertThat(event.getX(), is(666));
		assertThat(event.getY(), is(777));
		assertThat(event.getButton(), is(2));
	}
	
	@Test
	public void test画像更新リクエストイベント(){
		String data = "IMAGE_REQUEST|2|123|DUMMY";
		ImageRequestEvent event = (ImageRequestEvent)eventBuilder.parseEvent(data);
		
		assertThat(event.getEventType(), is("IMAGE_REQUEST"));
		
	}
}
