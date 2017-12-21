package io.embry.monsieurrobot;

import org.junit.Test;

import io.embry.monsieurrobot.data.Robot;
import io.embry.monsieurrobot.domain.Usecases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MonsieurRobotUnitTests {

    private Usecases mUsecases = new Usecases(new Robot("North", 0, 1));

    @Test
    public void givenPlaceCommand_whenValid_placeRobot() {
        //when
        String direction = "NORTH";
        int x = 0;
        int y = 4;
        mUsecases.placeRobot(direction, x, y);

        //then
        assertEquals("0,4,NORTH", mUsecases.getRobotPositionReport());
    }

    @Test
    public void givenMoveCommand_whenValid_moveRobot() {
        //when
        mUsecases.placeRobot("NORTH", 0, 2);

        //then
        assertTrue(mUsecases.moveRobot());
    }

    @Test
    public void givenMoveCommand_whenInvalid_doNotMoveRobot() {
        //when
        mUsecases.placeRobot("SOUTH", 0, 0);

        //then
        assertFalse(mUsecases.moveRobot());
    }

    @Test
    public void givenLeftCommand_moveRobotLeft() {
        //when
        mUsecases.placeRobot("EAST", 0, 4);
        mUsecases.rotateRobotLeft();

        //then
        assertEquals("0,4,SOUTH", mUsecases.getRobotPositionReport());
    }

}