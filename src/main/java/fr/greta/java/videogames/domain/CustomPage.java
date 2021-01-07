package fr.greta.java.videogames.domain;

import java.util.ArrayList;
import java.util.List;

public class CustomPage<T> {

    private List<T> elements = new ArrayList<>();
    private int totalPage;


    public List<T> getElements() {
        return elements;
    }

    public void setElements(List<T> elements) {
        this.elements = elements;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

}
