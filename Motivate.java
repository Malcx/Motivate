//https://superuser.com/questions/615114/how-to-make-a-window-task-run-everytime-i-enter-my-password-unlock-the-computer

import javafx.application.Application;


import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.Random;
import java.util.Properties;

public class Motivate extends Application {

	@Override
	public void start(Stage stage) {
 
		Properties props = new Properties();
	 	String theMessage = "Focus";
		int displayTime = 2000; // 2 second default
 
		try{
 
 
 			InputStream propInput = Motivate.class.getClassLoader().getResourceAsStream("config.props");
			props.load(propInput);
  			displayTime = Integer.parseInt(props.getProperty("displayTime"));
 
			InputStream input = Motivate.class.getClassLoader().getResourceAsStream("messages.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(input));
			ArrayList<String> array = new ArrayList<>();
			String line;
			while((line = br.readLine()) != null)
				array.add(line);
			// variable so that it is not re-seeded every call.
			Random rand = new Random();

			// nextInt is exclusive. Should be good with output for array.
			int randomIndex = rand.nextInt(array.size());

			// Print your random quote... 
			theMessage = array.get(randomIndex);
			System.out.println(array.get(randomIndex));
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}


		// https://stackoverflow.com/questions/32355335/on-javafx-how-to-hide-stage-without-disposing-it-and-closing-the-application
		// stop exit when hide window
		// Platform.setImplicitExit(false);
 
		// StageStyle.UTILITY means no taskbar, but cannot be TRANSPARENT
		Stage initStage = new Stage(StageStyle.UTILITY);
		initStage.setMaximized(true);
		initStage.setFullScreen(true);

		// Disable overlay message
		initStage.setFullScreenExitHint("");

		StackPane layoutPane = new StackPane();
		layoutPane.getStylesheets().add("application.css");
		initStage.setScene(new Scene(layoutPane, 300, 250));

		Label label = new Label(theMessage);
		layoutPane.getChildren().add(label);
		initStage.show();

		try{
			Thread.sleep(displayTime);
		}
		catch(Exception e)
		{}
		initStage.hide();

	}

	public static void main(String[] args) {
		launch(args);
	}

}