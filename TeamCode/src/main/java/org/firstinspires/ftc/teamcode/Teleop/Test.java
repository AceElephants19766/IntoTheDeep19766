package org.firstinspires.ftc.teamcode.Teleop;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Commands.RightElbowToggle;
import org.firstinspires.ftc.teamcode.Subsystems.RightElbow;
import org.firstinspires.ftc.teamcode.Commands.RightElbowSetPower;


@TeleOp
public class Test extends CommandOpMode {

    //subsystem
    public RightElbow rightElbow;

    //command
    public RightElbowSetPower runForFiveSec;
    public RightElbowToggle rightElbowToggle;

    public GamepadEx gamepadEx1;
    public GamepadEx gamepadEx2;

    @Override
    public void initialize() {

        gamepadEx1 = new GamepadEx(gamepad1);
        //subsystem
        rightElbow = new RightElbow(hardwareMap);

        //command
        runForFiveSec = new RightElbowSetPower(rightElbow);
        rightElbowToggle = new RightElbowToggle(rightElbow);

        gamepadEx1.getGamepadButton(GamepadKeys.Button.A).whenPressed(runForFiveSec);
        gamepadEx1.getGamepadButton(GamepadKeys.Button.B).toggleWhenPressed(rightElbowToggle);
    }

}

