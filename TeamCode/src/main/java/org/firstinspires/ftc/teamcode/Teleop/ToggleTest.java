package org.firstinspires.ftc.teamcode.Teleop;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Commands.CommandTest;
import org.firstinspires.ftc.teamcode.Subsystems.RightElbow;

@TeleOp
public class ToggleTest extends CommandOpMode {

    public GamepadEx gamepadEx1;
    @Override
    public void initialize() {
        RightElbow rightElbow= new RightElbow(hardwareMap);
        gamepadEx1 = new GamepadEx(gamepad1);
        gamepadEx1.getGamepadButton(GamepadKeys.Button.A).whenPressed(new CommandTest(rightElbow));
        new InstantCommand(() -> rightElbow.setPower(0.5));
        new SequentialCommandGroup(new CommandTest(rightElbow));


    }
}
