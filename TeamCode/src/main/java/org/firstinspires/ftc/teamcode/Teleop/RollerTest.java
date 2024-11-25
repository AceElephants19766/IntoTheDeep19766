package org.firstinspires.ftc.teamcode.Teleop;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.Roller;
@TeleOp
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
