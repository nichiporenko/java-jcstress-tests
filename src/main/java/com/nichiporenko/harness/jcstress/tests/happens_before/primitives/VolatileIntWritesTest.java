package com.nichiporenko.harness.jcstress.tests.happens_before.primitives;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.II_Result;

/**
 * Tests impossibility of instructions reordering.<br>
 * After writing a new value to field <b><u>c</u></b> and then to volatile field
 * <b><u>d</u></b> in one thread, another thread can't see a write to volatile field
 * <b><u>d</u></b> without seeing a write to field <b><u>c</u></b>. There is
 * happens-before relationship between writing to volatile field <b><u>d</u></b>
 * in one thread and reading its value from another thread.
 *
 * @author Dmitry Nichiporenko
 */
@JCStressTest
@Outcome(id = "0, 0", expect = Expect.ACCEPTABLE, desc = "Observer thread doesn't see any updates.")
@Outcome(id = "5, 0", expect = Expect.ACCEPTABLE, desc = "Observer thread sees an update of the field `c`.")
@Outcome(id = "5, 10", expect = Expect.ACCEPTABLE, desc = "Observer thread sees updates of two fields.")
@Outcome(id = "0, 10", expect = Expect.ACCEPTABLE_INTERESTING, desc = "Observer thread sees an update of the field `d`.")
@Outcome(id = "", expect = Expect.FORBIDDEN, desc = "Observer sees forbidden state.")
@State
public class VolatileIntWritesTest {

    int c;
    volatile int d;

    @Actor
    public void actor1() {
        c = 5;
        d = 10;
    }

    @Actor
    public void actor2(II_Result r) {
        r.r2 = d;
        r.r1 = c;
    }
}
