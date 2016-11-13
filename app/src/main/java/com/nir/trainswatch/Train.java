package com.nir.trainswatch;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Nirmal on 11/8/2016.
 */

public class Train implements Serializable {
    String name, type, frequency, arrivalTime, startTime, endTime;
    ArrayList<String> classes;
    int number;
    boolean directTrain;
}
