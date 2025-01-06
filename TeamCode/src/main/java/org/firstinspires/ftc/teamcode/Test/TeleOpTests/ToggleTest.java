package org.firstinspires.ftc.teamcode.Test.TeleOpTests;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Test.CommandTests.CommandTest;
import org.firstinspires.ftc.teamcode.Test.SubsystemTests.RightElbow;

@Disabled
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
