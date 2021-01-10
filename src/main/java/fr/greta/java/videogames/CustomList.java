package fr.greta.java.videogames;

import java.util.ArrayList;
import java.util.List;

public class CustomList<Param, OtherParam> {

    private List<Param> list = new ArrayList<>();
    private OtherParam value;


    public List<Param> getList() {
        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }

    public void setList(List<Param> list) {
        this.list = list;
    }

    public OtherParam getValue() {
        return value;
    }

    public void setValue(OtherParam value) {
        this.value = value;
    }
}
