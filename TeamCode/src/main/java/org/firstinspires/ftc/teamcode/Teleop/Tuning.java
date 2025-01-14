package org.firstinspires.ftc.teamcode.Teleop;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.ClawRollRotate;
import org.firstinspires.ftc.teamcode.Subsystems.ClawUpDown;
@TeleOp
public class Tuning extends CommandOpMode {
    public ClawUpDown clawUpDown;
    public ClawRollRotate clawRollRotate;

    @Override
    public void initialize() {
        clawUpDown = new ClawUpDown(hardwareMap);
        clawRollRotate = new ClawRollRotate(hardwareMap);

    }

    @Override
    public void run() {
        super.run();
        clawUpDown.setPos(gamepad2.right_trigger);
        clawRollRotate.SetPose(gamepad2.left_trigger);
        telemetry.addData(" Up down Pos",gamepad2.right_trigger);
        telemetry.addData("Roll Pos",gamepad2.left_trigger);
        telemetry.update();
    }
}