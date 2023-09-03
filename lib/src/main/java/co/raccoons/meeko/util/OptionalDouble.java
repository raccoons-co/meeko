/*
 * Copyright 2023, Raccoons. Developing simple way to change.
 *
 * @license GNU GPLv2
 */

/*
 * @summary Refactored java.util.OptionalDouble
 * @author Oleksii Kucheruk
 */

/*
 * Copyright (c) 2023, 2018, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package co.raccoons.meeko.util;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;

/**
 * A container object which may or may not contain a {@code double} value.
 * If a value is present, {@code isPresent()} returns {@code true}. If no
 * value is present, the object is considered <i>empty</i> and
 * {@code isPresent()} returns {@code false}.
 *
 * <p>Additional methods that depend on the presence or absence of a contained
 * value are provided, such as {@link #orElse(double) orElse()}
 * (returns a default value if no value is present) and
 * {@link #ifPresent(DoubleConsumer) ifPresent()} (performs
 * an action if a value is present).
 *
 * <p>This is a <a href="{@docRoot}/java.base/java/lang/doc-files/ValueBased.html">value-based</a>
 * class; programmers should treat instances that are
 * {@linkplain #equals(Object) equal} as interchangeable and should not
 * use instances for synchronization, or unpredictable behavior may
 * occur. For example, in a future release, synchronization may fail.
 *
 * @apiNote {@code OptionalDouble} is primarily intended for use as a method return type where
 * there is a clear need to represent "no result." A variable whose type is
 * {@code OptionalDouble} should never itself be {@code null}; it should always point
 * to an {@code OptionalDouble} instance.
 * @since 1.8
 */
public class OptionalDouble {

    private final double value;

    /**
     * Returns an empty {@code OptionalDouble} instance.  No value is present
     * for this {@code OptionalDouble}.
     *
     * @return an empty {@code OptionalDouble}.
     * @apiNote Though it may be tempting to do so, avoid testing if an object is empty
     * by comparing with {@code ==} or {@code !=} against instances returned by
     * {@code OptionalDouble.empty()}.  There is no guarantee that it is a singleton.
     * Instead, use {@link #isEmpty()} or {@link #isPresent()}.
     */
    public static OptionalDouble empty() {
        return new OptionalDouble(Double.NaN) {

            /**
             * @inheritDoc
             */
            @Override
            public boolean isEmpty() {
                return true;
            }

            /**
             * @inheritDoc
             */
            @Override
            public boolean isPresent() {
                return false;
            }

            /**
             * @inheritDoc
             */
            @Override
            public double getAsDouble() {
                throw new NoSuchElementException("No value present");
            }

            /**
             * @inheritDoc
             */
            @Override
            public void ifPresent(DoubleConsumer action) {
                // Intentionally empty
            }

            /**
             * @inheritDoc
             */
            @Override
            public void ifPresentOrElse(DoubleConsumer action, Runnable emptyAction) {
                Objects.requireNonNull(emptyAction);
                emptyAction.run();
            }

            /**
             * @inheritDoc
             */
            @Override
            public DoubleStream stream() {
                return DoubleStream.empty();
            }

            /**
             * @inheritDoc
             */
            @Override
            public double orElse(double other) {
                return other;
            }

            /**
             * @inheritDoc
             */
            @Override
            public double orElseGet(DoubleSupplier supplier) {
                Objects.requireNonNull(supplier);
                return supplier.getAsDouble();
            }

            /**
             * @inheritDoc
             */
            @Override
            public <X extends Throwable> double orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
                Objects.requireNonNull(exceptionSupplier);
                throw exceptionSupplier.get();
            }

            /**
             * @inheritDoc
             */
            @Override
            public int hashCode() {
                return 0;
            }

            /**
             * @inheritDoc
             */
            @Override
            public String toString() {
                return "OptionalDouble.empty";
            }
        };
    }

    /**
     * Construct an instance with the described value.
     *
     * @param value the double value to describe.
     */
    private OptionalDouble(double value) {
        this.value = value;
    }

    /**
     * Returns an {@code OptionalDouble} describing the given value.
     *
     * @param value the value to describe
     * @return an {@code OptionalDouble} with the value present
     */
    public static OptionalDouble of(double value) {
        return new OptionalDouble(value);
    }

    /**
     * If a value is present, returns the value, otherwise throws
     * {@code NoSuchElementException}.
     *
     * @return the value described by this {@code OptionalDouble}
     * @throws NoSuchElementException if no value is present
     * @apiNote The preferred alternative to this method is {@link #orElseThrow()}.
     */
    public double getAsDouble() {
        return value;
    }

    /**
     * If a value is present, returns {@code true}, otherwise {@code false}.
     *
     * @return {@code true} if a value is present, otherwise {@code false}
     */
    public boolean isPresent() {
        return true;
    }

    /**
     * If a value is not present, returns {@code true}, otherwise
     * {@code false}.
     *
     * @return {@code true} if a value is not present, otherwise {@code false}
     * @since 11
     */
    public boolean isEmpty() {
        return false;
    }

    /**
     * If a value is present, performs the given action with the value,
     * otherwise does nothing.
     *
     * @param action the action to be performed, if a value is present
     * @throws NullPointerException if value is present and the given action is
     *                              {@code null}
     */
    public void ifPresent(DoubleConsumer action) {
        Objects.requireNonNull(action);
        action.accept(value);
    }

    /**
     * If a value is present, performs the given action with the value,
     * otherwise performs the given empty-based action.
     *
     * @param action      the action to be performed, if a value is present
     * @param emptyAction the empty-based action to be performed, if no value is
     *                    present
     * @throws NullPointerException if a value is present and the given action
     *                              is {@code null}, or no value is present and the given empty-based
     *                              action is {@code null}.
     * @since 9
     */
    public void ifPresentOrElse(DoubleConsumer action, Runnable emptyAction) {
        ifPresent(action);
    }

    /**
     * If a value is present, returns a sequential {@link DoubleStream}
     * containing only that value, otherwise returns an empty
     * {@code DoubleStream}.
     *
     * @return the optional value as a {@code DoubleStream}
     * @apiNote This method can be used to transform a {@code Stream} of optional doubles
     * to a {@code DoubleStream} of present doubles:
     * <pre>{@code
     *     Stream<OptionalDouble> os = ..
     *     DoubleStream s = os.flatMapToDouble(OptionalDouble::stream)
     * }</pre>
     * @since 9
     */
    public DoubleStream stream() {
        return DoubleStream.of(value);
    }

    /**
     * If a value is present, returns the value, otherwise returns
     * {@code other}.
     *
     * @param other the value to be returned, if no value is present
     * @return the value, if present, otherwise {@code other}
     */
    public double orElse(double other) {
        return getAsDouble();
    }

    /**
     * If a value is present, returns the value, otherwise returns the result
     * produced by the supplying function.
     *
     * @param supplier the supplying function that produces a value to be returned
     * @return the value, if present, otherwise the result produced by the
     * supplying function
     * @throws NullPointerException if no value is present and the supplying
     *                              function is {@code null}
     */
    public double orElseGet(DoubleSupplier supplier) {
        return getAsDouble();
    }

    /**
     * If a value is present, returns the value, otherwise throws
     * {@code NoSuchElementException}.
     *
     * @return the value described by this {@code OptionalDouble}
     * @throws NoSuchElementException if no value is present
     * @since 10
     */
    public double orElseThrow() {
        return getAsDouble();
    }

    /**
     * If a value is present, returns the value, otherwise throws an exception
     * produced by the exception supplying function.
     *
     * @param <X>               Type of the exception to be thrown
     * @param exceptionSupplier the supplying function that produces an
     *                          exception to be thrown
     * @return the value, if present
     * @throws X                    if no value is present
     * @throws NullPointerException if no value is present and the exception
     *                              supplying function is {@code null}
     * @apiNote A method reference to the exception constructor with an empty argument
     * list can be used as the supplier. For example,
     * {@code IllegalStateException::new}
     */
    public <X extends Throwable> double orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        return getAsDouble();
    }

    /**
     * Indicates whether some other object is "equal to" this
     * {@code OptionalDouble}. The other object is considered equal if:
     * <ul>
     * <li>it is also an {@code OptionalDouble} and;
     * <li>both instances have no value present or;
     * <li>the present values are "equal to" each other via
     * {@code Double.compare() == 0}.
     * </ul>
     *
     * @param obj an object to be tested for equality
     * @return {@code true} if the other object is "equal to" this object
     * otherwise {@code false}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        return obj instanceof OptionalDouble other
                && (Double.compare(value, other.value) == 0);
    }

    /**
     * Returns the hash code of the value, if present, otherwise {@code 0}
     * (zero) if no value is present.
     *
     * @return hash code value of the present value or {@code 0} if no value is
     * present
     */
    @Override
    public int hashCode() {
        return Double.hashCode(value);
    }

    /**
     * Returns a non-empty string representation of this {@code OptionalDouble}
     * suitable for debugging.  The exact presentation format is unspecified and
     * may vary between implementations and versions.
     *
     * @return the string representation of this instance
     * @implSpec If a value is present the result must include its string representation
     * in the result.  Empty and present {@code OptionalDouble}s must be
     * unambiguously differentiable.
     */
    @Override
    public String toString() {
        return ("OptionalDouble[" + value + "]");
    }
}
