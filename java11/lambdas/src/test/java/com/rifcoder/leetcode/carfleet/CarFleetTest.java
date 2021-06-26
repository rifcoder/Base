package com.rifcoder.leetcode.carfleet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class CarFleetTest {

    @Test
    @DisplayName("Zero participant car")
    void zeroParticipantCar() {
        assertThat(new CarFleet().carFleet(10, new int[0], new int[0])).isEqualTo(0);
    }

    @Test
    @DisplayName("One car test")
    void oneCarTest() {
        assertThat(new CarFleet().carFleet(1, new int[]{0}, new int[]{1})).isEqualTo(1);
    }

    @Test
    @DisplayName("Two cars with same speed and different positions")
    void twoCarsWithSameSpeedAndDifferentPositions() {
        var distance = 10;
        var carSpeed = 2;
        var positions = new int[]{0, 2};
        var speed = new int[]{carSpeed, carSpeed};

        assertThat(new CarFleet().carFleet(distance, positions, speed))
                .isEqualTo(2);
    }

    @Test
    @DisplayName("One car are chased another one")
    void oneCarChasedAnotherOne() {
        var distance = 10;
        var positions = new int[]{0, 2};
        var speed = new int[]{5, 1};

        assertThat(new CarFleet().carFleet(distance, positions, speed)).isEqualTo(1);
    }

    @Test
    @DisplayName("Three cars race, second are chased by third one")
    void threeCarsRaceSecondAreChasedByThirdOne() {
        var distance = 10;
        var positions = new int[]{0, 2, 4};
        var speed = new int[]{8, 2, 3};

        assertThat(new CarFleet().carFleet(distance, positions, speed)).isEqualTo(2);
    }

    @Test
    @DisplayName("Complex test")
    void complexTest() {
        var target = 12;
        int[] position = {10,8,0,5,3};
        int[] speed = {2,4,1,1,3};

        assertThat(new CarFleet().carFleet(target, position, speed)).isEqualTo(3);
    }
}