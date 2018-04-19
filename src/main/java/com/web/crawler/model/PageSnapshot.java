package com.web.crawler.model;

import java.util.Collection;
import java.util.Objects;

public class PageSnapshot {

    private final Page page;
    private final Collection<PageSnapshot> links;

    public PageSnapshot(Page page, Collection<PageSnapshot> links) {
        this.page = page;
        this.links = links;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PageNode{");
        sb.append("page=").append(page);
        sb.append(", links=").append(links);
        sb.append('}');
        return sb.toString();
    }

    public Page getPage() {
        return page;
    }

    public Collection<PageSnapshot> getLinks() {
        return links;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageSnapshot that = (PageSnapshot) o;
        return Objects.equals(page, that.page) &&
                Objects.equals(links, that.links);
    }

    @Override
    public int hashCode() {

        return Objects.hash(page, links);
    }
}
