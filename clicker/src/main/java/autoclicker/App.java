package autoclicker;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.PrintWriter;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

/**
 * Hello world!
 *
 */
public class App implements NativeKeyListener {

	private LoggerManagement LOGGER = LoggerManagement.getInstance();

	private static final Option ARG_HELP = new Option("h", "help", false, "show help");
	private static final Option ARG_TIME = new Option("t", "temporel", true, "give time");

	public static int p = 0;

	private static void printHelp(Options options) {
		HelpFormatter hf = new HelpFormatter();
		PrintWriter pw = new PrintWriter(System.out);
		pw.println("Autoclicker By Eagle.exe");
		pw.println();
		hf.printUsage(pw, 100, "java -jar clicker.jar -t <time in miliseconds>");
		hf.printOptions(pw, 100, options, 2, 5);
		pw.close();
	}

	public static void main(String[] args) {

		CommandLineParser clp = new DefaultParser();
		Options options = new Options();
		options.addOption(ARG_HELP);
		options.addOption(ARG_TIME);
		int time = 1000;
		try {
			CommandLine cl = clp.parse(options, args);
			if (cl.hasOption(ARG_HELP.getLongOpt()) || cl.hasOption(ARG_HELP.getOpt())) {
				printHelp(options);
				System.exit(0);
			}
			if (cl.hasOption(ARG_TIME.getOpt()) || cl.hasOption(ARG_TIME.getLongOpt())) {
				try {
					time = Integer.parseInt(cl.getOptionValue(ARG_TIME.getOpt()));
				} catch (NumberFormatException e) {
					System.out.println("Invalid comments");
					printHelp(options);
					System.exit(1);
				}
			} else {
				printHelp(options);
				System.exit(1);
			}

		} catch (ParseException e) {
			System.out.println("Invalid comments");
			printHelp(options);
			System.exit(1);
		}

		try {
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException e) {
			System.exit(-1);
		}

		GlobalScreen.addNativeKeyListener(new App());

		try {

			Robot robot = new Robot();
			System.out.println("Press y to start and n to stop");
			while (true) {
				System.out.println("work");
				if (p == 1) {
					System.out.println("codition");
					robot.mousePress(InputEvent.BUTTON1_MASK);
					robot.delay(time);
					robot.mouseRelease(InputEvent.BUTTON1_MASK);
				}

			}
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent nativeEvent) {

	}

	@Override
	public void nativeKeyPressed(NativeKeyEvent nativeEvent) {

		if (nativeEvent.getKeyCode() == NativeKeyEvent.VC_Y) {
			p = 1;
		} else if (nativeEvent.getKeyCode() == NativeKeyEvent.VC_N) {
			p = 0;
		}

	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent nativeEvent) {
		// TODO Auto-generated method stub

	}

}
