package org.firstinspires.ftc.teamcode.Teleop;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


import org.firstinspires.ftc.teamcode.Subsystems.DriveTrainMecanum;
import org.firstinspires.ftc.teamcode.Subsystems.LeftElbow;
import org.firstinspires.ftc.teamcode.Subsystems.LeftElevator;
import org.firstinspires.ftc.teamcode.Subsystems.RightElbow;
import org.firstinspires.ftc.teamcode.Subsystems.RightElevator;
import org.firstinspires.ftc.teamcode.Commands.RightElbowSetPower;

@TeleOp
public class MechnumDrive extends CommandOpMode
{


    boolean firstRun = true;

    //subsystems
    public DriveTrainMecanum mecanumDrive;
    public RightElbow rightElbow;
    public RightElevator rightElevator;
    public LeftElbow leftElbow;
    public LeftElevator leftElevator;

    //commands
    public Command runForFiveSec;


    @Override
    public void initialize() {
        //Subsystems
        mecanumDrive = new DriveTrainMecanum(hardwareMap);
        rightElbow = new RightElbow(hardwareMap);
        rightElevator = new RightElevator(hardwareMap);
        leftElbow = new LeftElbow(hardwareMap);
        leftElevator = new LeftElevator(hardwareMap);

        //Commands
        runForFiveSec = new RightElbowSetPower(rightElbow);


    }

    @Override
    public void run() {
        super.run();

        if(firstRun) {
            runForFiveSec.schedule();
            firstRun = false;
        }

        //mecanum
        double y = -gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x*1.1;
        double rx = gamepad1.right_stick_x;
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double[] powers = {
                (y + x + rx)/denominator,
                (y - x + rx)/denominator,
                (y - x - rx)/denominator,
                (y + x - rx)/denominator
        };
        mecanumDrive.setPower(powers);


    }
}
