package com.nichiporenko.harness.jcstress.tests.volatiles;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.J_Result;

/*
 * Of course, this test should be run on 32-bit JVM due to the possibility of non-atomic write operations
 * to 8-byte long primitive (not for this specific case - volatile guarantees that the other thread
 * will not see the torn value).
 */
@JCStressTest
@Outcome(id = "0", expect = Expect.ACCEPTABLE, desc = "Default value for the field.")
@Outcome(id = "-1", expect = Expect.ACCEPTABLE, desc = "The value set. Observer sees the full update.")
@Outcome(id = "", expect = Expect.FORBIDDEN, desc = "Observer sees the torn value.")
@State
public class VolatileWriteLongTest {

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