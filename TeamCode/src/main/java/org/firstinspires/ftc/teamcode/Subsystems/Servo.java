package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Servo extends SubsystemBase {

    private CRServo testServo;

    public Servo (HardwareMap hardwareMap) {
        testServo=hardwareMap.get(CRServo.class,"testServo");
    }
public void setPower(double power) {
    testServo.setPower(power);
}


}
