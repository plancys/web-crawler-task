package com.web.crawler.model;

import java.util.Objects;

/**
 * This class contains page address and their body
 */
public class Page {

    private final String address;
    private final String body;

    public Page(String address, String body) {
        this.address = address;
        this.body = body;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Page{");
        sb.append("address='").append(address).append('\'');
        sb.append(", body='").append(body).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getAddress() {
        return address;
    }

    public String getBody() {
        return body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Page page = (Page) o;
        return Objects.equals(address, page.address) &&
                Objects.equals(body, page.body);
    }

    @Override
    public int hashCode() {

        return Objects.hash(address, body);
    }
}
