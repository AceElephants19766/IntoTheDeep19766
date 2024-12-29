package org.firstinspires.ftc.teamcode.Test.CommandTests.TeleOpTests;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Test.CommandTests.SubsystemTests.Roller;
@Disabled
public class RollerTest extends CommandOpMode {

    public Roller roller;


    @Override
    public void initialize() {
        roller = new Roller(hardwareMap);
    }
    @Override
    public void run() {
        super.run();
        roller.setPower(gamepad2.right_stick_x);
    }
}
