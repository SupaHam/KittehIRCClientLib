/*
 * * Copyright (C) 2013-2016 Matt Baxter http://kitteh.org
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use, copy,
 * modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS
 * BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
 * ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.kitteh.irc.client.library.element;

import java.util.Optional;

/**
 * Describes WHOIS data received. Note that away status is unknown, so
 * {@link User#isAway()} always returns false. This information also is
 * always {@link User#isStale()}.
 */
public interface WhoisData extends User {
    /**
     * Gets a user's operator privileges.
     * <p>
     * Example: "is an Operator".
     *
     * @return privileges, or empty if none
     */
    Optional<String> getOperatorPrivileges();

    /**
     * Gets the description of the server the user is on.
     *
     * @return description or empty if none present
     */
    Optional<String> getServerDescription();

    /**
     * Gets if the user is connected securely.
     *
     * @return true if secure connection
     */
    boolean isSecure();
}