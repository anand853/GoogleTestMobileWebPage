package com.test.core;

/**
 * Created by Anand on 6/18/2016.
 */
public class RobotElementNotFound extends Exception{

   public RobotElementNotFound(){
        super();

    }
    public RobotElementNotFound(String message){
        super(message);
    }
}
