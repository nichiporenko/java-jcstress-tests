package com.nichiporenko.harness.jcstress.tests.volatiles;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.D_Result;

/*
 * Of course, this test should be run on 32-bit JVM due to the possibility of non-atomic write operations
 * to 8-byte double primitive (not for this specific case - volatile guarantees that the other thread
 * will not see the torn value).
 */
@JCStressTest
@Outcome(id = "0.0", expect = Expect.ACCEPTABLE, desc = "Default value for the field.")
@Outcome(id = "123.12345678", expect = Expect.ACCEPTABLE, desc = "The value set. Observer sees the full update.")
@Outcome(id = "", expect = Expect.FORBIDDEN, desc = "Observer sees the torn value.")
@State
public class VolatileWriteDoubleTest {

    volatile double v;

    @Actor
    public void actor1(D_Result r) {
        v = 123.12345678;
    }

    @Actor
    public void actor2(D_Result r) {
        r.r1 = v;
    }
}
