package org.firstinspires.ftc.teamcode.auto;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Autonomous
@Configurable
public class AutonomosBlue extends LinearOpMode {

    private CRServo servo;
    public static double outakeVelocity = 1250;

    public static double servoPower = .75;
    private boolean outakeOn = false;
    private double directionFlip = 1;

     enum States { //These are all the states our robot can be in
        SPINUP,
        SHOOT,
        DRIVE_TO_POINT_1
    }
    private States state = States.SPINUP;

    private ElapsedTime timer = new ElapsedTime();

    private TelemetryManager panelsTelemetry; // Telemetry thing



    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration
        panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();

        servo = hardwareMap.get(CRServo.class, "servo0");
        DcMotor FL = hardwareMap.dcMotor.get("FL");
        DcMotor BL = hardwareMap.dcMotor.get("BL");
        DcMotor FR = hardwareMap.dcMotor.get("FR");
        DcMotor BR = hardwareMap.dcMotor.get("BR");
        DcMotor intake = hardwareMap.dcMotor.get("intake");
        DcMotorEx outake = hardwareMap.get(DcMotorEx.class, "Outake");
        outake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //DcMotor servo = hardwareMap.dcMotor.get("servo");

        // Reverse the right side motors. This may be wrong for your setup.
        // If your robot moves backwards when commanded to go forwards,
        // reverse the left side instead.
        // See the note about this earlier on this page. BR reversed
        FR.setDirection(DcMotorSimple.Direction.REVERSE);
        FL.setDirection(DcMotorSimple.Direction.REVERSE); //mec mount it reverse
        BR.setDirection(DcMotorSimple.Direction.REVERSE);
        BL.setDirection(DcMotorSimple.Direction.FORWARD);
        intake.setDirection(DcMotorSimple.Direction.REVERSE);




        waitForStart();

        timer.reset(); // Time logic

        state = States.SPINUP; // Statemachine

//        if (isStopRequested()) return;

        while (opModeIsActive()) {
            /*
            double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
            doublegamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;


            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;
            */

            // STATE MACHINE LOGIC

            switch(state){
                case SPINUP:
                    outake.setVelocity(outakeVelocity);
                    if(timer.seconds() > 5){ // Spinup outtake, once PIDF is tuned this be changed
                        state = States.SHOOT;
                        timer.reset();
                    }
                    break;
                case SHOOT:
                    servo.setPower(servoPower);
                    if(timer.seconds() > 5){
                        state = States.SHOOT;
                        timer.reset();
                    }
                    break;
                case DRIVE_TO_POINT_1:
                    //stuff
                    break;
            }

            panelsTelemetry.addData("Motor Velocity", (double) outake.getVelocity()); // Motor velocity logging via panels
            panelsTelemetry.update(telemetry);









            //if(gamepad1.x){frontLeftPower = 1;}
            //else {frontLeftPower = 0;}
            //if(gamepad1.y){frontRightPower = 1;}
            //else {frontRightPower = 0;}
            //if(gamepad1.a){backLeftPower = 1;}
            //else {backLeftPower = 0;}
            //if(gamepad1.b){backRightPower = 1;}
            //else {backRightPower = 0;}
            /*

            FL.setPower(frontLeftPower);
            BL.setPower(backLeftPower);
            FR.setPower(frontRightPower);
            BR.setPower(backRightPower);

             */
        }
    }
}