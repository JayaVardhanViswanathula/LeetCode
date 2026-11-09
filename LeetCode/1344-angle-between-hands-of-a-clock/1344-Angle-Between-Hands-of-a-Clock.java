
class Solution {
    public double angleClock(int hour, int minutes) {

        // Minute hand angle
        double minuteAngle = minutes * 6;

        // Hour hand angle
        double hourAngle = (hour % 12) * 30 + minutes * 0.5;

        // Difference between both hands
        double angle = Math.abs(hourAngle - minuteAngle);

        // Return smaller angle
        return Math.min(angle, 360 - angle);
    }
}
