package com.web.crawler.model;

import java.util.Collection;

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
}
