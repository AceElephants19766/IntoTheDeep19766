package org.firstinspires.ftc.teamcode.Teleop;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Commands.ClawCommand;
import org.firstinspires.ftc.teamcode.Commands.ClawRotateCommand;
import org.firstinspires.ftc.teamcode.Commands.CollectingArmCommand;

import org.firstinspires.ftc.teamcode.Commands.CollectingArmElbowCommnad;
import org.firstinspires.ftc.teamcode.Commands.DriveCommand;
import org.firstinspires.ftc.teamcode.Commands.ResetImu;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.ClawRotat;
import org.firstinspires.ftc.teamcode.Subsystems.CollectingArmElbowPID;
import org.firstinspires.ftc.teamcode.Subsystems.CollectingArmPID;
import org.firstinspires.ftc.teamcode.Subsystems.DriveTrainMecanum;

@TeleOp
public class CollectingArmPIDTesting extends CommandOpMode {

    public GamepadEx gamepadEx1;
    public GamepadEx gamepadEx2;

    //Subsystems
    public DriveTrainMecanum driveTrainMecanum;

    public Claw claw;
    public ClawRotat clawRotat;
    public CollectingArmPID collectingArmPID;
    public CollectingArmElbowPID collectingArmElbowPID;

    @Override
    public void initialize() {
        gamepadEx1 = new GamepadEx(gamepad1);
        gamepadEx2 = new GamepadEx(gamepad2);

        //Subsystems
        driveTrainMecanum = new DriveTrainMecanum(hardwareMap);

        claw = new Claw(hardwareMap);
        clawRotat = new ClawRotat(hardwareMap);
        collectingArmPID = new CollectingArmPID(hardwareMap);
        collectingArmElbowPID = new CollectingArmElbowPID(hardwareMap);

        //mecanum
        driveTrainMecanum.setDefaultCommand(
                new DriveCommand(
                        driveTrainMecanum,
                        gamepadEx2
                )
        );
        gamepadEx2.getGamepadButton(GamepadKeys.Button.START).whenPressed(
                new ResetImu(driveTrainMecanum)
        );

        gamepadEx1.getGamepadButton(GamepadKeys.Button.A).whenPressed(
                new CollectingArmCommand(collectingArmPID, -8)
        );
        gamepadEx1.getGamepadButton(GamepadKeys.Button.Y).whenPressed(
                new CollectingArmCommand(collectingArmPID, -4)
        );

        gamepadEx1.getGamepadButton(GamepadKeys.Button.X).whenPressed(
                new CollectingArmElbowCommnad(collectingArmElbowPID,0.7)
        );
        gamepadEx1.getGamepadButton(GamepadKeys.Button.B).whenPressed(
                new CollectingArmElbowCommnad(collectingArmElbowPID,-1.7)
        );

        gamepadEx1.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).toggleWhenPressed(
                new ClawCommand(claw)
        );
        gamepadEx1.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).toggleWhenPressed(
                new ClawRotateCommand(clawRotat)
        );

    }

    @Override
    public void run() {
        super.run();
        telemetry.addData("elevator revs", collectingArmPID.getRevs());
        telemetry.addData("Elbow revs", collectingArmElbowPID.getRevs());
        telemetry.update();
    }
}
