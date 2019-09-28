package com.nichiporenko.harness.jcstress.tests.volatiles;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.J_Result;

/**
 * Tests the absence of non-atomic write operations to volatile long primitive.
 * This test must be run on 32-bit JVM in order to properly check the absence of
 * visibility of torn values from another thread. Write operation to an 8-byte
 * volatile long primitive is always atomic.
 *
 * @author Dmitry Nichiporenko
 */
@JCStressTest
@Outcome(id = "0", expect = Expect.ACCEPTABLE, desc = "Default value for the field.")
@Outcome(id = "-1", expect = Expect.ACCEPTABLE, desc = "The value set. Observer sees the full update.")
@Outcome(id = "", expect = Expect.FORBIDDEN, desc = "Observer sees the torn value.")
@State
public class VolatileLongWriteTest {

    volatile long v;

    @Actor
    public void actor1() {
        v = -1L;
    }

    @Actor
    public void actor2(J_Result r) {
        r.r1 = v;
    }
}