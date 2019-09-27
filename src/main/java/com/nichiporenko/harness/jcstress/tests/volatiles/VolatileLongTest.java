package com.nichiporenko.harness.jcstress.tests.volatiles;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.J_Result;

/*
 * Of course, this test must be run on 32-bit JVM in order to correctly check the absence of visibility of torn values
 * from other threads. Read/write operations for 8-byte volatile primitives are always atomic.
 */
@JCStressTest
@Outcome(id = "0", expect = Expect.ACCEPTABLE, desc = "Default value for the field.")
@Outcome(id = "-1", expect = Expect.ACCEPTABLE, desc = "The value set. Observer sees the full update.")
@Outcome(id = "", expect = Expect.FORBIDDEN, desc = "Observer sees the torn value.")
@State
public class VolatileLongTest {

    volatile long v;

    @Actor
    public void actor1(J_Result r) {
        v = -1L;
    }

    @Actor
    public void actor2(J_Result r) {
        r.r1 = v;
    }
}