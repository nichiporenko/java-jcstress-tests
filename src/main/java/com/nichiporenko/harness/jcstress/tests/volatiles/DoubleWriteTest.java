package com.nichiporenko.harness.jcstress.tests.volatiles;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.D_Result;

/**
 * Tests non-atomic write operations to double primitive.
 * This test must be run on 32-bit JVM to see the torn value — the partial result of
 * non-atomic write operation to an 8-byte double primitive.
 *
 * @author Dmitry Nichiporenko
 */
@JCStressTest
@Outcome(id = "0.0", expect = Expect.ACCEPTABLE, desc = "Default value for the field.")
@Outcome(id = "123.12345678", expect = Expect.ACCEPTABLE, desc = "The value set. Observer sees the full update.")
@Outcome(id = "", expect = Expect.ACCEPTABLE_INTERESTING, desc = "Observer sees the torn value.")
@State
public class DoubleWriteTest {

    double v;

    @Actor
    public void actor1(D_Result r) {
        v = 123.12345678;
    }

    @Actor
    public void actor2(D_Result r) {
        r.r1 = v;
    }
}
