package com.student.demo.dto;

import java.util.List;

import org.springframework.data.domain.Page;

public class PageResponseDTO<T> {

    private List<T> content;
    private int currentPage;
    private int totalPages;
    private long totalElements;
    private boolean first;
    private boolean last;

    public PageResponseDTO(Page<T> page) {

        this.content = page.getContent();
        this.currentPage = page.getNumber();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.first = page.isFirst();
        this.last = page.isLast();

    }

    public List<T> getContent () {

        return content;

    }

    public int getCurrentPage () {

        return currentPage;

    }

    public int getTotalPages () {

        return totalPages;

    }

    public long getTotalElements () {

        return totalElements;

    }

    public boolean isFirst () {

        return first;

    }

    public boolean isLast () {

        return last;

    }
    
}
