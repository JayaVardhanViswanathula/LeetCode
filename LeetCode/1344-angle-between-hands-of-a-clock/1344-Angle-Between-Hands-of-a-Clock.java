class Solution {
    public double angleClock(int hour, int minutes) {

        // Calculate hour hand angle
        double hourAngle = (hour % 12) * 30 + minutes * 0.5;

        // Calculate minute hand angle
        double minuteAngle = minutes * 6;

        // Calculate difference
        double angle = Math.abs(hourAngle - minuteAngle);

        // Return smaller angle
        return Math.min(angle, 360 - angle);
    }
}