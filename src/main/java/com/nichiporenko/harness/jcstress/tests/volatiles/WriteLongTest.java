package com.nichiporenko.harness.jcstress.tests.volatiles;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.J_Result;

/*
 * Of course, in order to see the partial write operation to the long field, this test must be run on a 32-bit JVM.
 */
@JCStressTest
@Outcome(id = "0", expect = Expect.ACCEPTABLE, desc = "Default value for the field.")
@Outcome(id = "-1", expect = Expect.ACCEPTABLE, desc = "The value set. Observer sees the full update.")
@Outcome(id = "", expect = Expect.ACCEPTABLE_INTERESTING, desc = "Observer sees the torn value.")
@State
public class WriteLongTest {

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