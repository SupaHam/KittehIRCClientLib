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
package org.kitteh.irc.client.library.implementation;

import org.kitteh.irc.client.library.Client;
import org.kitteh.irc.client.library.element.WhoisData;
import org.kitteh.irc.client.library.util.ToStringer;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

class WhoisBuilder {
    private class Whois implements WhoisData {
        private final Client client;
        private final Optional<String> account;
        private final Set<String> channels;
        private final String name;
        private final String nick;
        private final String userString;
        private final String host;
        private final long creationTime;
        private final Optional<String> realName;
        private final Optional<String> server;
        private final Optional<String> serverDescription;
        private final boolean secure;
        private final Optional<String> operatorPrivileges;

        private Whois(Client client, String account, Set<String> channels, String nick, String userString, String host, String realName, String server, String serverDescription, boolean secure, String operatorPrivileges) {
            this.client = client;
            this.account = Optional.ofNullable(account);
            this.channels = Collections.unmodifiableSet(new HashSet<>(channels));
            this.name = nick + '!' + userString + '@' + host;
            this.nick = nick;
            this.userString = userString;
            this.host = host;
            this.realName = Optional.ofNullable(realName);
            this.server = Optional.ofNullable(server);
            this.serverDescription = Optional.ofNullable(serverDescription);
            this.operatorPrivileges = Optional.ofNullable(operatorPrivileges);
            this.secure = secure;
            this.creationTime = System.currentTimeMillis();
        }

        @Nonnull
        @Override
        public Optional<String> getAccount() {
            return this.account;
        }

        @Nonnull
        @Override
        public Set<String> getChannels() {
            return this.channels;
        }

        @Nonnull
        @Override
        public String getHost() {
            return this.host;
        }

        @Nonnull
        @Override
        public String getNick() {
            return this.nick;
        }

        @Nonnull
        @Override
        public Optional<String> getRealName() {
            return this.realName;
        }

        @Nonnull
        @Override
        public Optional<String> getServer() {
            return this.server;
        }

        @Nonnull
        @Override
        public String getUserString() {
            return this.userString;
        }

        @Override
        public boolean isAway() {
            return false; // TODO not trackable by WHOIS
        }

        @Nonnull
        @Override
        public String getMessagingName() {
            return this.nick;
        }

        @Nonnull
        @Override
        public String getName() {
            return this.name;
        }

        @Nonnull
        @Override
        public boolean isStale() {
            return true; // Instantly stale
        }

        @Nonnull
        @Override
        public Client getClient() {
            return this.client;
        }

        @Override
        public long getCreationTime() {
            return this.creationTime;
        }

        @Nonnull
        @Override
        public Optional<String> getOperatorPrivileges() {
            return this.operatorPrivileges;
        }

        @Nonnull
        @Override
        public Optional<String> getServerDescription() {
            return this.serverDescription;
        }

        @Override
        public boolean isSecure() {
            return this.secure;
        }

        @Nonnull
        @Override
        public String toString() {
            return new ToStringer(this).add("client", this.client).add("account", this.account).add("channels", this.channels).add("name", this.name).add("creationTime", this.creationTime).add("realName", this.realName).add("server", this.server).add("serverDescription", this.serverDescription).add("secure", this.secure).add("operatorPrivileges", this.operatorPrivileges).toString();
        }
    }

    private final Client client;
    private String account;
    private final Set<String> channels = new HashSet<>();
    private final String nick;
    private String userString;
    private String host;
    private String realName;
    private String server;
    private String serverDescription;
    private boolean secure;
    private String operatorPrivileges;

    WhoisBuilder(Client client, String nick) {
        this.client = client;
        this.nick = nick;
    }

    @Nonnull
    public String getNick() {
        return this.nick;
    }

    void setAccount(@Nonnull String account) {
        this.account = account;
    }

    void addChannels(@Nonnull String channels) {
        for (String channel : channels.split(" ")) {
            this.channels.add(channel);
        }
    }

    void setUserString(@Nonnull String userString) {
        this.userString = userString;
    }

    void setHost(@Nonnull String host) {
        this.host = host;
    }

    void setRealName(@Nonnull String realName) {
        this.realName = realName;
    }

    void setServer(@Nonnull String server) {
        this.server = server;
    }

    void setServerDescription(@Nonnull String serverDescription) {
        this.serverDescription = serverDescription;
    }

    void setSecure() {
        this.secure = true;
    }

    void setOperatorPrivileges(@Nonnull String operatorPrivileges) {
        this.operatorPrivileges = operatorPrivileges;
    }

    WhoisData build() {
        return new Whois(this.client, this.account, this.channels, this.nick, this.userString, this.host, this.realName, this.server, this.serverDescription, this.secure, this.operatorPrivileges);
    }
}