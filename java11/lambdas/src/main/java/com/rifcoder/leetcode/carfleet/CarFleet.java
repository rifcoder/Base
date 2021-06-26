package com.rifcoder.leetcode.carfleet;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CarFleet {
    public int carFleet(int target, int[] position, int[] speed) {
        if (position.length <= 1) {
            return position.length;
        }

        List<CarInfo> sortedCarList = sortCarsByPosition(target, position, speed);

        var carFleet = sortedCarList.get(0);
        var result = 1;
        for (int i = 1; i < sortedCarList.size(); i++) {
            CarInfo currentCar = sortedCarList.get(i);
            if (currentCar.time > carFleet.time) {
                result++;
                carFleet = currentCar;
            }
        }
        return result;
    }

    private List<CarInfo> sortCarsByPosition(int target, int[] position, int[] speed) {
        return IntStream.range(0, position.length)
                .mapToObj(i -> new CarInfo(position[i], speed[i], (target - position[i]) / (double) speed[i]))
                .sorted((car1, car2) -> car2.position - car1.position)
                .collect(Collectors.toList());
    }
}

class CarInfo {
    public int position;
    public int speed;
    public double time;

    public CarInfo(int position, int speed, double time) {
        this.position = position;
        this.speed = speed;
        this.time = time;
    }
}
