package org.kyotogtug.vnc;

import org.junit.Before;
import org.junit.Test;
import org.kyotogtug.vnc.events.ImageRequestEvent;
import org.kyotogtug.vnc.events.KeyPressEvent;
import org.kyotogtug.vnc.events.KeyReleaseEvent;
import org.kyotogtug.vnc.events.MousePressEvent;
import org.kyotogtug.vnc.events.MouseReleaseEvent;
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
	public void testマウスリリースイベントのパース(){
		String data = "MOUSE_RELEASE|6|123456789011|666,777,2";
		
		MouseReleaseEvent event = (MouseReleaseEvent) eventBuilder.parseEvent(data);
		
		assertThat(event.getEventType(), is("MOUSE_RELEASE"));
		assertThat(event.getSequence(), is(6));
		assertThat(event.getTimestamp(), is(123456789011L));
		assertThat(event.getX(), is(666));
		assertThat(event.getY(), is(777));
		assertThat(event.getButton(), is(2));
	}
	
	@Test
	public void testマウスプレスイベントのパース(){
		String data = "MOUSE_PRESS|6|123456789011|666,777,2";
		
		MousePressEvent event = (MousePressEvent) eventBuilder.parseEvent(data);
		
		assertThat(event.getEventType(), is("MOUSE_PRESS"));
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
	
	@Test
	public void testキー押下イベント(){
		String data = "KEY_PRESS|0|1|123";
		KeyPressEvent event = (KeyPressEvent)eventBuilder.parseEvent(data);
		assertThat(event.getEventType(), is("KEY_PRESS"));
		assertThat(event.getKeyCode(), is(123));
		assertThat(event.getSequence(), is(0));
		assertThat(event.getTimestamp(), is(1L));
	}
	
	@Test
	public void testキー解放イベント(){
		String data = "KEY_RELEASE|0|1|123";
		KeyReleaseEvent event = (KeyReleaseEvent)eventBuilder.parseEvent(data);
		assertThat(event.getEventType(), is("KEY_RELEASE"));
		assertThat(event.getKeyCode(), is(123));
		assertThat(event.getSequence(), is(0));
		assertThat(event.getTimestamp(), is(1L));
	}
}
