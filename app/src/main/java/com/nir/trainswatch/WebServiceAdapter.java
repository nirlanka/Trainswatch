package com.nir.trainswatch;

import java.util.ArrayList;

/**
 * Created by Nirmal on 11/9/2016.
 */

public abstract class WebServiceAdapter {
    protected String url, start, end;
    protected ArrayList<Train> trains;
    public WebServiceAdapter(String start, String end) {
        this.start = start;
        this.end = end;
    }
    public abstract ArrayList<Train> getResults();
}
