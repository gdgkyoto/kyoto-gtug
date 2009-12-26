package org.kyotogtug.wavesensei.tw.model;

import org.junit.Test;
import org.kyotogtug.wavesensei.tw.model.TwPost;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TwPostTest {

    private TwPost model = new TwPost();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
