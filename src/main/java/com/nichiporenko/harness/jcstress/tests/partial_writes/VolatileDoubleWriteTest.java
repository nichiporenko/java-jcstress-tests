package com.nichiporenko.harness.jcstress.tests.partial_writes;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.D_Result;

/**
 * Tests the absence of non-atomic write operations to volatile double primitive.
 * This test must be run on 32-bit JVM in order to properly check the absence of
 * visibility of torn values from another thread. Write operation to an 8-byte
 * volatile double primitive is always atomic.
 * * @author Dmitry Nichiporenko
 */
@JCStressTest
@Outcome(id = "0.0", expect = Expect.ACCEPTABLE, desc = "Default value for the field.")
@Outcome(id = "123.12345678", expect = Expect.ACCEPTABLE, desc = "The value set. Observer sees the full update.")
@Outcome(id = "", expect = Expect.FORBIDDEN, desc = "Observer sees the torn value.")
@State
public class VolatileDoubleWriteTest {

    volatile double v;

    @Actor
    public void actor1() {
        v = 123.12345678;
    }

    @Actor
    public void actor2(D_Result r) {
        r.r1 = v;
    }
}
