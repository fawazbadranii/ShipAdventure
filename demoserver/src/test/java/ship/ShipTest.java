package ship;

import exception.InvalidCardinalDirectionException;
import exception.InvalidDegreeException;
import exception.InvalidOperationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipTest {
    Ship ship = new Ship();
    FacingDirection TestingFacingDirection = FacingDirection.SOUTH; //USed for F only
    int expectedX = 17;
    int expectedY = -8;
    int expectedManhattan = 25;
    int expectedRotation = -90;
    @Test
    void ManhattanTest() throws InvalidOperationException, InvalidCardinalDirectionException, InvalidDegreeException {

        ship.move("F10");
        ship.move("N3");
        ship.move("F7");
        ship.move("R90");
        ship.move("F11");
        System.out.println("The manhattan test is "+ship.getManhattanDistance());
        System.out.println("The rotation  is "+ship.rotation);
        Assertions.assertEquals(expectedManhattan,ship.getManhattanDistance());
        System.out.println(ship.getManhattanDistance());


    }

    @Test
    void ManhattanTest2() throws InvalidOperationException, InvalidCardinalDirectionException, InvalidDegreeException {
        ship.move("F10");
        ship.move("N3");
        ship.move("F7");
        ship.move("R440");
        ship.move("F11");
        System.out.println("The manhattan test is "+ship.getManhattanDistance());
        System.out.println("The rotation  is "+ship.rotation);
        Assertions.assertEquals(expectedManhattan,ship.getManhattanDistance());
        Assertions.assertEquals(expectedX,ship.getX());
        Assertions.assertEquals(expectedY,ship.getY());
        Assertions.assertEquals(expectedRotation,ship.rotation);
        Assertions.assertEquals(TestingFacingDirection,ship.getFacingDirection());
    }

    @Test
    void whenInvalidInstruction_throwException(){
        Assertions.assertThrows(InvalidOperationException.class, () -> ship.move("K90"));
    }


}