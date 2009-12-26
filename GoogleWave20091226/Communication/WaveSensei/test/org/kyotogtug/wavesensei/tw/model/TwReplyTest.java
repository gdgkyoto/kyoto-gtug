package org.kyotogtug.wavesensei.tw.model;

import org.junit.Test;
import org.kyotogtug.wavesensei.tw.model.TwReply;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TwReplyTest {

    private TwReply model = new TwReply();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
