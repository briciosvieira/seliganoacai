package com.seliganoacai.acai.webConfig.dto.responseDto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class PageableDto {

    private List content = new ArrayList<>();
    private boolean fist;
    private boolean last;
    @JsonProperty("page")
    private int number;
    private int size;
    @JsonProperty("PagesElements")
    private int numberOfElements;
    private int totalPages;
    private int totalelements;

    public List getContent() {
        return content;
    }

    public void setContent(List content) {
        this.content = content;
    }

    public boolean isFist() {
        return fist;
    }

    public void setFist(boolean fist) {
        this.fist = fist;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalelements() {
        return totalelements;
    }

    public void setTotalelements(int totalelements) {
        this.totalelements = totalelements;
    }
}
