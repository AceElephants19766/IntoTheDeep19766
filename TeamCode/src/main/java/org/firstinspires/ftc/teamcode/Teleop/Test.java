package org.firstinspires.ftc.teamcode.Teleop;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Commands.RightElbowRunForSecondsCommandGroup;
import org.firstinspires.ftc.teamcode.Commands.RightElbowSetPower;
import org.firstinspires.ftc.teamcode.Commands.RightElbowToggle;
import org.firstinspires.ftc.teamcode.Subsystems.RightElbow;
import org.firstinspires.ftc.teamcode.Commands.RightElbowRunForSeconds;


@Disabled
public class Test extends CommandOpMode {

    //subsystem
    public RightElbow rightElbow;

    public GamepadEx gamepadEx1;
    public GamepadEx gamepadEx2;

    @Override
    public void initialize() {

        //GamePad
        gamepadEx1 = new GamepadEx(gamepad1);

        //subsystem
        rightElbow = new RightElbow(hardwareMap);

        gamepadEx1.getGamepadButton(GamepadKeys.Button.A).whenPressed(
                new RightElbowRunForSeconds(rightElbow, 5)
        );

        gamepadEx1.getGamepadButton(GamepadKeys.Button.B).toggleWhenPressed(
                new RightElbowToggle(rightElbow)
        );

        gamepadEx1.getGamepadButton(GamepadKeys.Button.X).whenPressed(
                new SequentialCommandGroup(
                        new RightElbowSetPower(rightElbow, 0.5),
                        new WaitCommand(1000),
                        new RightElbowSetPower(rightElbow,0)
                )
        );

        gamepadEx1.getGamepadButton(GamepadKeys.Button.Y).whenPressed(
                new RightElbowRunForSecondsCommandGroup(rightElbow,1,5)
        );
    }
}
