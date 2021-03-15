package ship;

import exception.InvalidCardinalDirectionException;
import exception.InvalidDegreeException;
import exception.InvalidOperationException;

import java.util.regex.Pattern;

public class Ship {

    private int x = 0;
    private int y = 0;
    private FacingDirection facingDirection = FacingDirection.EAST; //USed for F only
    String directionX = "East";
    String directionY ="North";
    static int rotation = 0;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    public void move(String instructions) throws InvalidOperationException, InvalidCardinalDirectionException,InvalidDegreeException {


        String direction = instructions.substring(0,1);
        String coordinates = instructions.substring(1);

        processInstruction(direction, coordinates);
        processShipDirection();

        getManhattanDistance();

    }

    public int getManhattanDistance() {
        return Math.abs(x)+Math.abs(y);
    }

    private void processShipDirection() {
        if(x >= 0) {
            directionX ="East";
        }
        else {
            directionX = "West";
        }
        if(y >= 0) {
            directionY ="North";
        }
        else {
            directionY = "South";
        }

    }
    private void processInstruction(String direction, String coordinates) throws InvalidOperationException, InvalidCardinalDirectionException,
            InvalidDegreeException{
        if(direction.equals("N")){
            y += Integer.parseInt(coordinates);
        }
        else if(direction.equals("S")){
            y -= Integer.parseInt(coordinates);
        }
        else if(direction.equals("E")){
            x += Integer.parseInt(coordinates);
        }
        else if(direction.equals("W")){
            x -= Integer.parseInt(coordinates);
        }
        else if(direction.equals("F")){
            calculateFacingDirection(coordinates);

        }
        else if ( direction.equals("L")) {
            calculateLeftAngle(coordinates);
        }
        else if (direction.equals("R")){
            calculateRightAngle(coordinates);
        }
        else{
            throw new InvalidOperationException("Invalid Operation inserted");
        }

    }

    private void calculateFacingDirection(String coordinates) throws InvalidCardinalDirectionException {
        try {
            if(facingDirection == FacingDirection.EAST ){
                x += Integer.parseInt(coordinates);
            }
            else if (facingDirection ==FacingDirection.WEST){
                x -= Integer.parseInt(coordinates);
            }
            else if (facingDirection ==FacingDirection.NORTH){
                y += Integer.parseInt(coordinates);
            }
            else if (facingDirection ==FacingDirection.SOUTH){
                y -= Integer.parseInt(coordinates);
            }
        }catch (Exception e){
            throw new InvalidCardinalDirectionException("invalid Corrdinate ");
        }
    }

    private void calculateRightAngle(String coordinates) throws InvalidDegreeException {

        try {
            rotation -=Integer.parseInt(coordinates);
            while (rotation < -360) {
                rotation += 360;
            }
            rotation = degreeAdjuster(Math.abs(rotation)) * -1;
            if(rotation==0 || rotation == -360){
                facingDirection = FacingDirection.EAST;
                rotation = 0;
            }
            else if (rotation == -90){
                facingDirection = FacingDirection.SOUTH;
            }
            else if (rotation == -180){
                facingDirection = FacingDirection.WEST;
            }
            else if (rotation == -270){
                facingDirection = FacingDirection.NORTH;
            }
            else{
                throw new InvalidDegreeException("Invalid Right Angle inserted");
            }

        }catch (Exception e) {
            throw new InvalidDegreeException("Invalid Right Angle inserted");
        }

    }
    private void calculateLeftAngle(String coordinates) {  //Positive angles

        rotation +=Integer.parseInt(coordinates);
        while(rotation > 360) {
            rotation -= 360;
        }
        rotation = degreeAdjuster(rotation);
        if(rotation==0 || rotation == 360){
            facingDirection = FacingDirection.EAST;
            rotation = 0;
        }
        else if (rotation == 90){
            facingDirection = FacingDirection.NORTH;
        }
        else if (rotation == 180){
            facingDirection = FacingDirection.WEST;
        }
        else if (rotation == 270){
            facingDirection = FacingDirection.SOUTH;
        }

    }




    private int degreeAdjuster(int  cord) {
        int angles[] = {0, 90, 180, 270, 360};
        int val[] = {0,0,0,0,0};
        int valueForAdjustedAngle = 0;
        int coordinate = cord;
        for (int i=0; i<angles.length; i++){    //Subtracts given coordinate from angles
            int temp = angles[i];
            val[i] =Math.abs(temp - coordinate);
        }
        int min = val[0];
        for(int i=0;i<val.length;i++){     // Gives the minimun Difference
            if(val[i] < min){
                min = val[i];
            }
        }

        for ( int i = 0 ; i <angles.length;i++){
            if(val[i] == min ){
                if(i == 0)valueForAdjustedAngle=0;
                if(i == 1)valueForAdjustedAngle=90;
                if(i == 2)valueForAdjustedAngle=180;
                if(i == 3)valueForAdjustedAngle=270;
                if(i == 4)valueForAdjustedAngle=360;
            }
        }
        return valueForAdjustedAngle;
    }




}

