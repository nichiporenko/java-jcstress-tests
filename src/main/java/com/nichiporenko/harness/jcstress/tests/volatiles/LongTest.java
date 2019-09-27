package com.nichiporenko.harness.jcstress.tests.volatiles;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.J_Result;

/*
 * Of course, this test must be run on 32-bit JVM to see the torn value — the result of non-atomic read/write
 * operations for an 8-byte long primitive.
 */
@JCStressTest
@Outcome(id = "0", expect = Expect.ACCEPTABLE, desc = "Default value for the field.")
@Outcome(id = "-1", expect = Expect.ACCEPTABLE, desc = "The value set. Observer sees the full update.")
@Outcome(id = "", expect = Expect.ACCEPTABLE_INTERESTING, desc = "Observer sees the torn value.")
@State
public class LongTest {

    long v;

    @Actor
    public void actor1(J_Result r) {
        v = -1L;
    }

    @Actor
    public void actor2(J_Result r) {
        r.r1 = v;
    }
}