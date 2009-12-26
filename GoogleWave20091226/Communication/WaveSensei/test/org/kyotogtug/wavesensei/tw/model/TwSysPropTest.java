package org.kyotogtug.wavesensei.tw.model;

import org.junit.Test;
import org.kyotogtug.wavesensei.tw.model.TwSysProp;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TwSysPropTest {

    private TwSysProp model = new TwSysProp();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
