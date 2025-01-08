package org.firstinspires.ftc.teamcode.Teleop;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.ClawUpDown;
@TeleOp
public class Tuning extends CommandOpMode {
    public ClawUpDown clawUpDown;

    @Override
    public void initialize() {
        clawUpDown = new ClawUpDown(hardwareMap);
    }

    @Override
    public void run() {
        super.run();
        clawUpDown.setPos(gamepad2.right_trigger);
        telemetry.addData("Pos",gamepad2.right_trigger);
        telemetry.update();
    }
}
