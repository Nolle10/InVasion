package dk.sdu.se.f23.InVasion.commonenemy;

import dk.sdu.se.f23.InVasion.common.data.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
class EnemyTest {
    private Enemy enemy;
    private ArrayList<Point> points;
    @BeforeEach
    void setUp() {
        points = new ArrayList<>();
        points.add(new Point(1,2));
        points.add(new Point(2,3));
        points.add(new Point(3,4));

        enemy = new Enemy(points);
    }

    @Test
    void getNextPointShouldNotMove() {
        Point point = enemy.getNextPoint(0.0f);
        Point nextPoint = enemy.getNextPoint(0.0f);

        assertEquals(point, points.get(0));
        assertEquals(point, nextPoint);
    }

    @Test
    void getNextPointShouldMove() {
        Point point = enemy.getNextPoint(0.0f);
        Point nextPoint = enemy.getNextPoint(99.0f);

        assertEquals(point, points.get(0));
        assertNotEquals(point, nextPoint);
        assertEquals(nextPoint, points.get(1));
    }
}